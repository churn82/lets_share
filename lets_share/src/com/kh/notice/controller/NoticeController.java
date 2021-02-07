package com.kh.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeController
 */
@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticeService noticeService = new NoticeService();
	
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
		Notice notice = new Notice();
		
		
		String[] uriArr = request.getRequestURI().split("/");
		switch(uriArr[uriArr.length-1]) {
		case "noticeList" :
			goNoticeList(request,response); break;
		case "noticeDetail" :
			goNoticeDetail(request,response); break;
		case "eventList" :
			goEventList(request,response); break;	
		case "eventDetail" :
			goEventDetail(request,response); break;
		case "writer" :
			goWriter(request,response); break;
		case "writeImpl" :
			writeImpl(request, response); break;
		default : response.setStatus(404); break;
		}
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void goNoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		.forward(request, response);
		
		ArrayList<Notice> noticeList = null;
		noticeList = noticeService.selectNoticeList();
		
		request.setAttribute("noticeList", noticeList);
		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		.forward(request, response);
		
		
	}
	
	protected void goEventList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Notice> noticeList = null;
		noticeList = noticeService.selectEventList();
		
		request.setAttribute("noticeList", noticeList);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/event_list.jsp")
		.forward(request, response);
	}
	
	
	//공지상세페이지
	protected void goNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));//게시글을 클릭하면 notice_no가 넘어옴
		
		Notice notice = new Notice();
		
		notice = noticeService.selectNoticeDetail(noticeNo);
		request.setAttribute("notice",notice);
		
		String noticeTitle = notice.getNoticeTitle();
		String noticeContent = notice.getNoticeContent();
		Date noticeDate = notice.getNoticeDate();
		
		request.setAttribute("noticeTitle", noticeTitle);
		request.setAttribute("noticeContent", noticeContent);
		request.setAttribute("noticeDate", noticeDate);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_detail.jsp")
		.forward(request, response);
	}
	
	//이벤트 상세페이지
	protected void goEventDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher("/WEB-INF/view/notice/event_detail.jsp")
		.forward(request, response);
	}
	
	protected void goWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.getRequestDispatcher("/WEB-INF/view/notice/writer.jsp")
		  .forward(request, response);
		
	}
	
	protected void writeImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title"); 
		String contents = request.getParameter("contents");
	
		Notice notice = new Notice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(contents);
		
		noticeService.insertNoticeBoard(notice);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/noticeList.jsp")
		.forward(request, response);	
	}
	
	
	
	
	

}
