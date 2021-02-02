package com.kh.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.group.model.service.GroupService;
import com.kh.group.model.vo.Group;

@WebServlet("/group/*")
public class GroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GroupService groupService = new GroupService();
    public GroupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "form": goForm(request, response); break;
		case "view": goView(request, response); break;
		case "search": goSearch(request, response); break;
		case "receive" : receiveGroupInform(request, response); break;
		default : 
			response.setStatus(404);
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void goForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/group/group_form.jsp")
		.forward(request, response);
	}
	
	protected void goView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/group/group_view.jsp")
		.forward(request, response);
	}
	
	protected void goSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Group> groupList = null;
		String date = request.getParameter("date");
		String service = request.getParameter("service");
		System.out.println("date : "+date);
		System.out.println("service : "+service);
		
		
//		if(request.getParameter("date")==null && request.getParameter("service")==null){
//			groupList = groupService.getGroupList();
//		}else if(request.getParameter("date")!=null ) {
//			int payDate = Integer.parseInt(date);
//			groupList = groupService.getGroupList(payDate,service);
//		}else if(!date.equals("") && service.equals("")){
//			int payDate = Integer.parseInt(date);
//			groupList = groupService.getGroupList(payDate);
//		}else if(date.equals("") && !service.equals("")) {
//			groupList = groupService.getGroupList(service);
//		}
		groupList = groupService.getGroupList();
		
		request.setAttribute("groupList", groupList);
		request.getRequestDispatcher("/WEB-INF/view/group/group_search.jsp")
		.forward(request, response);
	}
	protected void receiveGroupInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// bank_account, service_id,  service_pw, service, date, bank 가 넘어옴
		String bank = request.getParameter("bank_real");
		String bank_account = request.getParameter("bank_account");
		String service = request.getParameter("service");
		String service_id = request.getParameter("service_id");
		String service_pw = request.getParameter("service_pw");
		String date = request.getParameter("date");
		int groupPayDate = Integer.parseInt(date);
		
		Group group = new Group();
		group.setMemberId("lee5031207"); //[임시] 원래 세션에서 로그인한 회원의 값을 찾아 넣어준다
		group.setAccountInfo(bank+" "+bank_account);
		group.setServiceCode(service);
		group.setShareId(service_id);
		group.setSharePw(service_pw);
		group.setSharePw(service_pw);
		group.setGroupPayDate(groupPayDate);
		
		// 1. SH_GROUP 테이블에 데이터 입력
		// 2. SH_MATCHING 테이블에 데이터 입력
		// 3. 프로시저 PL_UPDATE_MEMBER_CNT(SC_GROUP_ID.CURRVAL) 실행  => 3가지 쿼리 동시에 진행
		groupService.insertGroup(group);
		
		request.setAttribute("msg", "모임을 정상적으로 등록하였습니다.");
		request.setAttribute("url", "/group/search");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
}
