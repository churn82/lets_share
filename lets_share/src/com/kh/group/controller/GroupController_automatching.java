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
import com.kh.member.model.vo.Member;

@WebServlet("/auto/*")
public class GroupController_automatching extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GroupService_automatching groupService_auto = new GroupService_automatching();
    public GroupController_automatching() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if((request.getParameter("service") == null) || (request.getParameter("user_period") == null)) {
			throw new ToAlertException(ErrorCode.AUTH01);
		}else {
			groupFind(request,response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void groupFind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int addGroup = 0;
		String userSerCode = (String) request.getParameter("service"); //사용자 요청 서비스 코드
		int userPeriod = Integer.parseInt(request.getParameter("user_period")); //사용자 요청 사용기간
		Member user = (Member) request.getSession().getAttribute("user"); //세션에서 아이디를 가져오기
		String userId = user.getMbId();  //현재 사용자의 아이디
		String userName = user.getMbName();
		int groupId = 0;
		
		groupId = groupService_auto.autoMatching(userSerCode, userPeriod, userId, userName);
		if(groupId != 0) {
			request.setAttribute("groupFound", "found");
			response.sendRedirect("/group/view?groupId=" + groupId);
		}else {
			throw new ToAlertException(ErrorCode.MR04);
		}

	}

	
	
}
