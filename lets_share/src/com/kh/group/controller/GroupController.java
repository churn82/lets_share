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
		group.setAccountInfo(bank+" "+bank_account);
		group.setServiceCode(service);
		group.setShareId(service_id);
		group.setSharePw(service_pw);
		group.setSharePw(service_pw);
		group.setGroupPayDate(groupPayDate);
		
		//groupService.insertGroup(group);
		
		ArrayList<Integer> groupIds = groupService.getGroupId("lee5031207");
		System.out.println(groupIds);

	}

}
