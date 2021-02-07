package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch(uriArr[uriArr.length-1]) {
			case "emailauth" : emailauth(request,response); break;
			case "join" : join(request,response); break;
			case "leave" : leave(request,response); break;
			case "login" : login(request,response); break;
			case "modify" : modify(request,response); break;
			case "rank" : rank(request,response); break;
			case "mypage" : mypage(request,response); break;//김승현 mypage
			case "joinimpl" : joinImpl(request,response); break;
			case "loginimpl" : loginImpl(request,response); break;
			case "idcheck" : confirmId(request,response); break;
			case "nickcheck" : confirmnick(request,response); break;
			case "logout" : logout(request,response); break;
			case "mailauth" : Email(request,response); break;
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
	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbId = request.getParameter("id");
		String mbpassword = request.getParameter("pw");
		String mbnick = request.getParameter("nick");
		String mbtel = request.getParameter("tel");
		String mblevel = request.getParameter("level");
		String mbemail = request.getParameter("email");
		
		Member member = new Member();
		member.setMbId(mbId);
		member.setMbPassword(mbpassword);
		member.setMbNick(mbnick);
		member.setMbtel(mbtel);
	
		member.setMbemail(mbemail);
		
		
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
		System.out.println("mbId : " + mbId);
	}
	private void confirmId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbId = request.getParameter("mbId");
		
		Member member =	memberService.selectMemberById(mbId);


		if(member != null) {
			response.getWriter().print("fail");
		}else {
			
			response.getWriter().print("success");
			
		}
	}
	private void confirmnick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbnick = request.getParameter("mbnick");
		
		Member member =	memberService.selectMemberBynick(mbnick);


		if(member != null) {
			response.getWriter().print("fail");
		}else {
			
			response.getWriter().print("success");
			
		}
	}
	private void loginImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mbId = request.getParameter("id");
		String mbpassword = request.getParameter("pw");
		Member member = memberService.memberAuthenticate(mbId, mbpassword);
		if(member != null) {
			//세션에 회원 정보 저장
			request.getSession().setAttribute("user", member);
			response.sendRedirect("/index");
			
		}else {
			request.getRequestDispatcher("/WEB-INF/view/member/login_fail.jsp")
			.forward(request, response);
			
		}
		
		
		request.getSession().setAttribute("user", member);
		
		System.out.println("id : " + mbId);
		System.out.println("pw : " + mbpassword);
		
		
	}
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
		memberService.Emailsend(member);
		
		
		
		request.setAttribute("msg", "회원가입 완료를 위한 이메일이 발송되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
		
	}
}
