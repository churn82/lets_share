package com.kh.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import com.kh.common.code.ErrorCode;
import com.kh.common.exception.ToAlertException;
import com.kh.common.util.http.HttpUtil;
import com.kh.member.model.service.KakaoMemberService;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@WebServlet("/Kakao/*")
public class KakaoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	HttpUtil httpUtil = new HttpUtil();
	KakaoMemberService kakaoMemberService = new KakaoMemberService();
	MemberService memberService = new MemberService();

    public KakaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case  "oauth" : kakaoReceiveInform(request, response); break;
		case  "joinImpl" : joinImpl(request, response); break;
		default:
			response.setStatus(404);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void kakaoReceiveInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 카카오에서 주는 인가 코드 받기 => 사용자가 카카오 계정으로 로그인 완료시 코드가 발급된다
		String code = request.getParameter("code");
		
		//2. 발급 받은 코드를 body에 넣어 POST 방식으로 AccessToken, refreshToken 발급받는다 
		//   -> 이 토큰으로 사용자 정보에 접근할 수 있다.
		final String TOKENURL = "https://kauth.kakao.com/oauth/token";
		Map<String, String> tHeaders = new HashMap();
		String tBody = "grant_type=authorization_code&client_id=8a92e5d7d3324acd050fd30b648b921b&redirect_uri=http://localhost:9090/Kakao/oauth&code="+code;
		String tokenString = httpUtil.post(TOKENURL, tHeaders, tBody);
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> tokenMap = mapper.readValue(tokenString, Map.class);
		
		String accessToken = tokenMap.get("access_token");
		String refreshToken = tokenMap.get("refresh_token");
		
		
		//3. 발급 받은 AccessToken을 헤더에 넣어 GET 방식으로 사용자 정보를 받는다
		final String USERURL = "https://kapi.kakao.com/v2/user/me";
		Map<String, String> uHeaders = new HashMap();
		uHeaders.put("Authorization", "Bearer "+accessToken);
		String  userString = httpUtil.get(USERURL, uHeaders);
		
		//4. parseUserData()함수로 id, email, nickname만 빼옴
		Map<String, String> kakaoData = parseUserData(userString);		
		
		//5. kakaoID로 MB_ID에 등록된 회원이 있는지 확인
		Member member = memberService.selectMemberById(kakaoData.get("kakaoId"));
		if(member.getMbId()==null) {
			//5-1. Kakao로 로그인한적 없음 kakaoJoin.jsp 로 데이터 넣어서 보내주자
			request.setAttribute("kakaoId", kakaoData.get("kakaoId"));
			request.setAttribute("email", kakaoData.get("email"));
			request.setAttribute("nickname", kakaoData.get("nickname"));
			request.getRequestDispatcher("/WEB-INF/view/member/kakao/kakaoJoin.jsp")
			.forward(request, response);
			
		}else if(member.getMbId().equals(kakaoData.get("kakaoId"))){
			//5-2. Kakao로 로그인한적 있음 세션주자 걔 찾아서
			request.getSession().setAttribute("user", member);
			request.setAttribute("msg", "정상적으로 로그인 되었습니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}
	}
	
	protected void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = new Member();
		member.setMbId(request.getParameter("kakaoId"));
		member.setMbpassword("kakao");
		member.setMbemail(request.getParameter("email"));
		member.setMbName(request.getParameter("name"));
		member.setMbnick(request.getParameter("nickname"));
		member.setMbtel(request.getParameter("tel"));
		
		int res = memberService.insertMember(member);
		
		request.setAttribute("msg", "정상적으로 카카오 회원가입 되었습니다");
		Member user = memberService.selectMemberById(member.getMbId());
		request.getSession().setAttribute("user", user);
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
		
	}
	
	//AccesToken으로 반환받은 사용자 정보에서 Id, email, nickname만 map에 담아 가져오는 함수
	public Map<String, String> parseUserData (String userString){
		
		Long kakaoLId = (long) 0;
		String email = "";
		String nickname = "";
		
		Map<String, String> kakaoData = new HashMap<String, String>();
		
		JSONParser jsonparser = new JSONParser();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JSONObject obj = (JSONObject) jsonparser.parse(userString);
			Map<String, Object> userData = new HashMap<String, Object>();
			userData = mapper.convertValue(obj, Map.class);
			
			//[1] 카카오 회원번호 (MB_ID에 들어갈 놈)
			kakaoLId = (Long) userData.get("id");
			
			//[2] 이메일 
			Map<String, Object> kakaoAccount = new HashMap<String, Object>();
			kakaoAccount = mapper.convertValue(userData.get("kakao_account"), Map.class);
			email = (String) kakaoAccount.get("email");
			
			//[3] 닉네임
			Map<String, Object> profile = new HashMap<String, Object>();
			profile = mapper.convertValue(kakaoAccount.get("profile"),  Map.class);
			nickname = (String) profile.get("nickname");
			
			String kakaoId = "kakao"+Long.toString(kakaoLId);
			
			kakaoData.put("kakaoId", kakaoId);
			kakaoData.put("email", email);
			kakaoData.put("nickname", "kakao"+nickname);
			
		} catch (ParseException e) {
			throw new ToAlertException(ErrorCode.KAKAO01,e);
		}
		return kakaoData;
	}
	
}
