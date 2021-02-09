package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			//case "kakaologin" : kakaologin(request,response); break;
			default : System.out.println("오류");
		}
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void emailauth(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/emailauth.jsp")
		.forward(request, response);
	}
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/member/join.jsp")
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
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mbId = request.getParameter("id");
		String mbpassword = request.getParameter("pw");
		String mbnick = request.getParameter("nick");
		String mbtel = request.getParameter("tel");
		String mbemail = request.getParameter("email");
		
		
		Member member = new Member();
		member.setMbId(mbId);
		member.setMbPassword(mbpassword);
		member.setMbNick(mbnick);
		member.setMbtel(mbtel);
		member.setMbemail(mbemail);
		
		int res = memberService.insertMember(member);
		//request.getSession().removeAttribute("persistUser");
		request.getRequestDispatcher("/WEB-INF/view/member/login.jsp")
		.forward(request, response);
		
	}
	
	//ID중복확인
	private void confirmId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbId = request.getParameter("mbId");
		Member member =	memberService.selectMemberById(mbId);
		if(member.getMbId() == null) {
			response.getWriter().print("success");
		}else {
			response.getWriter().print("fail");
		}
	}
	
	//닉네임 중복 확인
	private void confirmnick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbnick = request.getParameter("mbnick");
		Member member =	memberService.selectMemberBynick(mbnick);
		if(member.getMbNick() == null) {
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
		String pw = member.getMbPassword();
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
		
		String mbId = request.getParameter("id");
		String mbpassword = request.getParameter("pw");
		String mbnick = request.getParameter("nick");
		String mbtel = request.getParameter("tel");
		String mbemail = request.getParameter("email");
		
		Member member = new Member();
		member.setMbId(mbId);
		member.setMbPassword(mbpassword);
		member.setMbNick(mbnick);
		member.setMbtel(mbtel);
		member.setMbemail(mbemail);
		
		int res = memberService.insertMember(member);
		
		//request.getSession().setAttribute("persistUser", member);
		memberService.Emailsend(member);
		
		
		
		request.setAttribute("msg", "회원가입 완료를 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
		
	}
}
