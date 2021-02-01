package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] uriArr = request.getRequestURI().split("/");
		switch(uriArr[uriArr.length-1]) {
		case "noticeList" :
			goNoticeList(request,response);
			break;
		case "eventList" :
			goEventList(request,response);
			break;
		case "noticeDetail" :
			goNoticeDetail(request,response);
			break;
		case "eventDetail" :
			goEventDetail(request,response);
			break;
		case "noticeWriter" :
			goNoticeWriter(request,response);
			break;
		case "eventWriter" :
			goEventWriter(request,response);
			break;
		default : response.setStatus(404);
			break;
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void goNoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		.forward(request, response);
	}
	
	private void goEventList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/event_list.jsp")
		.forward(request, response);
	}
	
	private void goNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_detail.jsp")
		.forward(request, response);
	}
	
	private void goEventDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/event_detail.jsp")
		.forward(request, response);
	}
	
	private void goNoticeWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_writer.jsp")
		.forward(request, response);
	}
	
	private void goEventWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/notice/event_writer.jsp")
		.forward(request, response);
	}
	
	
	
	
	
	
	

}
