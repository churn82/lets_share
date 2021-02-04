package com.kh.group.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.group.model.service.GroupService;
import com.kh.group.model.service.GroupService_automatching;
import com.kh.group.model.vo.Group;

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
		case "preference": userPreference(request, response); break;
		default : 
			response.setStatus(404);
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void userPreference(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userSerCode = request.getParameter("service");
		int userPeriod = Integer.parseInt(request.getParameter("user_period"));
		switch (userSerCode) {
		case "netflix": userSerCode = "SR01"; break;
		case "watcha": userSerCode = "SR02"; break;
		case "coupang": userSerCode = "SR01"; break;
		default:
			break;
		}
		
		groupService_auto.findGroup(userSerCode, userPeriod);
		
	}
	
	
	
}
