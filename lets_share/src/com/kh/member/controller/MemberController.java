package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.sms.SMS;
import com.kh.group.model.service.GroupService;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MemberService memberService = new MemberService();
	GroupService groupService = new GroupService();
    
    public MemberController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch(uriArr[uriArr.length-1]) {
			case "emailauth" : emailauth(request,response); break;
			case "join" : join(request,response); break;
			case "leave" : leave(request,response); break;
			case "login" : login(request,response); break;
			case "modify" : modify(request,response); break;
			case "modifyimpl" : modifyImpl(request,response); break;
			case "rank" : rank(request,response); break;
			case "mypage" : mypage(request,response); break;//김승현 mypage
			case "joinimpl" : joinImpl(request,response); break;
			case "loginimpl" : loginImpl(request,response); break;
			case "idcheck" : confirmId(request,response); break;
			case "nickcheck" : confirmnick(request,response); break;
			case "logout" : logout(request,response); break;
			case "mailauth" : Email(request,response); break; 
			case "withdrawal" : withdrawal(request, response); break;
			//case "joinModify" : joinModify(request, response); break;
			case "sendAuthCode" : sendAuthCode(request, response); break;
			case "confirmAuthCode" : confirmAuthCode(request, response); break;
			//case "kakaologin" : kakaologin(request,response); break;
			default : System.out.println("오류");
		}
	} 


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void emailauth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/emailauth.jsp")
		.forward(request, response);
	}
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/joinv2.jsp")
		.forward(request, response);
	}
	private void leave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/leave.jsp")
		.forward(request, response);
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp")
		.forward(request, response);
	}
	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/modify.jsp")
		.forward(request, response);
	}
	private void rank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/rank.jsp")
		.forward(request, response);
	}
	
	//마이페이지
	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.session에서 회원등급 코드 꺼낸다
		Member member = (Member) request.getSession().getAttribute("user");
		String memberRank = member.getMblevel();
		
		//2.회원등급 코드에 맞는 코드명 가져온다
		String gradeName = memberService.getGradeName(memberRank);
		
		//3. gradeName 설정해준다
		request.setAttribute("gradeName", gradeName);
		
		request.getRequestDispatcher("/WEB-INF/view/member/mypage.jsp")
	    .forward(request, response);
		
	}

	//회원가입 
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//=========================================Email로직===================================================
//		//1. 세션에서 저장해둔 persistUser Member 꺼낸다.
//		Member member = (Member) request.getSession().getAttribute("persistUser"); 
//		
//		//2. 꺼낸 Member를 SH_MEMBER에 넣어준다.
//		int res = memberService.insertMember(member); 
//	
//		//3. 세션에서 persistUser Member를 지워준다.
//		request.getSession().removeAttribute("persistUser"); 
//		
//		//4. login페이지로 보내주어 로그인하라고 한다.
//		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp")
//		.forward(request, response); 그냥 주석처리 해줄게요
		
		
		//=========================================폰 번호로 이미 인증 받은 로직===================================================
		//1. 데이터들을 받아온다
		String Id = request.getParameter("Id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		
		Member member = new Member();
		member.setMbId(Id);
		member.setMbpassword(password);
		member.setMbemail(email);
		member.setMbName(name);
		member.setMbnick(nickname);
		member.setMbtel(phone);
		
		//2. SH_MEMBER에 member 를 넣어준다
		int res = memberService.insertMember(member);
		
		//3. login페이지로 보내주어 로그인하라고 한다.
		request.setAttribute("msg", "정상적으로 회원가입 하셨습니다.");
		request.setAttribute("url", "/member/login"); //여기에 
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response); 
	
	}
	
	//ID중복확인 
	private void confirmId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 아이디를 받음
		String mbId = request.getParameter("mbId");
		
		//2. 아이디값에 해당하는 member가 DB에 존재하는 지확인
		Member member =	memberService.selectMemberById(mbId);
		
		//3. null이면 DB에 없는거니까 성공, fail이면 DB에 있는거니까 실패
		if(member.getMbId() == null) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}
	}
	
	//닉네임 중복 확인
	private void confirmnick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 닉네임 받아옴
		String mbnick = request.getParameter("mbnick");
		
		//2. DB에 해당 닉네임을 가진 Member가 있는지 가져옴
		Member member =	memberService.selectMemberBynick(mbnick);
		
		//3. null이면 DB에 없는거니까 성공, fail이면 DB에 있는거니까 실패 
		if(member.getMbnick() == null) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}
	}
	
	//로그인
	private void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbId = request.getParameter("id");
		String mbpassword = request.getParameter("pw");
		Member member = memberService.memberAuthenticate(mbId, mbpassword);

		if(member.getMbId() != null) {
			//세션에 회원 정보 저장
			request.getSession().setAttribute("user", member);
			request.setAttribute("msg", "정상적으로 로그인 되었습니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
			
		}else {
			request.setAttribute("msg", "아이디나 비밀번호를 확인하세요.");
			request.setAttribute("url", "/member/login");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}
	}
	
	//회원정보 수정
	private void modifyImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String updatePw = request.getParameter("pw");
		String updateNickName = request.getParameter("nick");
		
		//1.session에서 회원아이디 코드 꺼낸다
		Member member = (Member) request.getSession().getAttribute("user");
		String memberId = member.getMbId();
		
		memberService.updatePwNick(memberId, updatePw, updateNickName);
		
		request.setAttribute("msg", "정상적으로 수정되었습니다");
		request.setAttribute("url", "/member/mypage");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	//회원 탈퇴(탈퇴 처리 전에 그룹소속되었는지 확인)
	private void withdrawal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wpw = request.getParameter("wpw");
		Member member = (Member) request.getSession().getAttribute("user");
		String pw = member.getMbpassword();
		String memberId = member.getMbId();
		boolean flag = groupService.checkGroup(memberId);
		
		if(pw.equals(wpw) && flag) {
			//탈퇴처리
			memberService.updateLeaveDate(memberId);
			request.getSession().removeAttribute("user");
			request.setAttribute("msg", "탈퇴 처리되었습니다");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}else {
			//탈퇴 x
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다");
			request.setAttribute("url", "/member/mypage");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}

	}
	

	/*
private void kakaologin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbkakaoId = request.getParameter("userID");
		
		Member member = memberService.memberAuthenticatekakao(mbkakaoId);
		
		if(member != null) {
			//세션에 회원 정보 저장
			request.getSession().setAttribute("user", member);
			request.setAttribute("msg", "정상적으로 로그인 되었습니다.");
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
			
		}else {
			request.setAttribute("msg", "아이디나 비밀번호를 확인하세요.");
			request.setAttribute("url", "/member/login");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}
		
		
	}*/
	
private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("/index");
	}
	
	
	private void Email(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 입력폼에서 데이터 받은애 Member에 넣어줌
		String mbId = request.getParameter("id");
		String mbpassword = request.getParameter("pw");
		String mbnick = request.getParameter("nick");
		String mbtel = request.getParameter("tel");
		String mbemail = request.getParameter("email");
		
		Member member = new Member();
		member.setMbId(mbId);
		member.setMbpassword(mbpassword);
		member.setMbnick(mbnick);
		member.setMbtel(mbtel);
		member.setMbemail(mbemail);
		
		//2. 이메일 보내줌 
		memberService.Emailsend(member);
		
		//3. 세션에 입력받은 Member 저장해둠
		request.getSession().setAttribute("persistUser", member); 

		
		//4. Index페이지로 다시 보내줌 
		request.setAttribute("msg", "회원가입 완료를 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/index"); //여기에 
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	private void joinModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/joinModify.jsp")
		.forward(request, response);
	}
	
	//인증 번호 전송
	private void sendAuthCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1. 폰번호를 받고, 인증코드만들어 문자메시지 내용을 만들어주자  //01030299967
		String phoneNum = request.getParameter("phoneNum");
		int authNum = (int) Math.round(Math.random()*1000000);
		String authCode = Integer.toString(authNum); //인증 코드 완성
		String content = "Let's Share 입니다.\n인증 번호["+authCode+"]를 입력하세요";
		//System.out.println(authCode);
		
		//2. 문자보내주자 ->  //to는 누구에게 보낼건지. content는 어떤 내용보낼건지 
		new SMS().sendSMS(phoneNum, content);  
		
		//3. 세션에 authCode 넣어주자
		request.getSession().setAttribute("authCode", authCode); // authCode라는 이름으로 문자간 숫자6개를 저장 
		
		//4. 비동기 통신이니 응답해주자 success 
		response.getWriter().print("success");
	}
	
	//인증 번호 확인
	private void confirmAuthCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 받아온 인증번호를 확인하자 -> 잘온다 
		String authCode = request.getParameter("authCode");
		
		//2. 이제 Session에 저장해둔 인증코드와 사용자가 입력한 인증코드를 비교 한다
		String sessionAuthCode = (String) request.getSession().getAttribute("authCode"); 
		
		//3. 비교해서 성공, 실패 보내주자
		if(authCode.equals(sessionAuthCode)) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}
	}
}
