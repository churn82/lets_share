package com.kh.group.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.code.ErrorCode;
import com.kh.common.exception.ToAlertException;
import com.kh.group.model.service.GroupService_automatching;

@WebServlet("/auto/*")
public class GroupController_automatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GroupService_automatching groupService_auto = new GroupService_automatching();
    public GroupController_automatching() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
			case "find": groupFind(request, response); break;
			case "form": matchingForm(request,response); break;
			default : response.setStatus(404);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void matchingForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/group/group_automatching.jsp")
		.forward(request, response);
	}
	private void groupFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int addGroup = 0;
		String userSerCode = (String) request.getParameter("service"); //사용자 요청 서비스 코드
		int userPeriod = Integer.parseInt(request.getParameter("user_period")); //사용자 요청 사용기간
		String userId = "test50"; //현재 사용자의 아이디
		
		int groupId = groupService_auto.autoMatching(userSerCode, userPeriod, userId);
	
		if(groupId == 0) {
			throw new ToAlertException(ErrorCode.MR01);
		}else { //매칭 과정이 무사히 끝나면, 해당 그룹의 뷰 페이지로 이동시켜준다.
			request.setAttribute("groupId", groupId);
			request.getRequestDispatcher("/WEB-INF/view/group/group_view.jsp")
			.forward(request, response);
		}
	}
	
	
	
}
