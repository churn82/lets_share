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
		
		int foundGroupId = groupService_auto.findGroup(userSerCode, userPeriod, userId);//조건에 맞는 그룹 탐색
		if(foundGroupId != 0) {
			addGroup = groupService_auto.addGroup(userId, foundGroupId, userPeriod);
		}else {
			//해당 조건을 만족하는 그룹을 찾지 못하였습니다.
			throw new ToAlertException(ErrorCode.MR04);
		}
		if(addGroup != 0) {
			//그룹의 인원수를 갱신하고, 그룹이 가득찼다면 대기열에서 뺀다
			if(groupService_auto.isMax(foundGroupId)) {
				groupService_auto.exitQueue(foundGroupId);
			}
		}else {
			//매칭된 그룹에 등록하는 도중 에러가 발생하였습니다.
			throw new ToAlertException(ErrorCode.MR02);
		}
		
		//그룹 매칭, 그룹원 등록, 그룹인원 갱신까지의 작업이 모두 성공했다면,
		//사용자에게 등록된 그룹의 뷰로 이동시킨다.
		request.setAttribute("groupId", foundGroupId);
		request.getRequestDispatcher("/WEB-INF/view/group/group_view.jsp")
		.forward(request, response);
	}
	
	
	
}
