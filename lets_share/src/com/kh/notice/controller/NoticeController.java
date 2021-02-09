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
		case "writerImpl" :
			writeImpl(request, response); break;
		case "beforeUpdate" :
			beforeUpdate(request, response); break;
		case "updateRequest" :
			updateRequest(request, response); break;	
		case "delete" :
			delete(request, response); break;	
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
	//공지리스트
	protected void goNoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Notice> noticeList = null;
		noticeList = noticeService.selectNoticeList();
		
		request.setAttribute("noticeList", noticeList);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		.forward(request, response);
		
	}
	
	//이벤트 목록
	protected void goEventList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ArrayList<Notice> noticeList = null;
		noticeList = noticeService.selectEventList();
		
		request.setAttribute("noticeList", noticeList);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/event_list.jsp")
		.forward(request, response);
	}
	
	
	//공지 상세페이지
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
		request.setAttribute("noticeNo", noticeNo);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_detail.jsp")
		.forward(request, response);
	}
	
	
	//이벤트 상세페이지
	protected void goEventDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = new Notice();
		notice = noticeService.selectEventDetail(noticeNo);
		request.setAttribute("notice", notice);
		
		String noticeTitle = notice.getNoticeTitle();
		String noticeContent = notice.getNoticeContent();
		Date noticeDate = notice.getNoticeDate();
		
		request.setAttribute("noticeTitle", noticeTitle);
		request.setAttribute("noticeContent", noticeContent);
		request.setAttribute("noticeDate", noticeDate);
		request.setAttribute("noticeNo", noticeNo);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/event_detail.jsp")
		.forward(request, response);
	}
	
	protected void goWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.getRequestDispatcher("/WEB-INF/view/notice/writer.jsp")
		  .forward(request, response);
		
	}
	//작성페이지
	protected void writeImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//view에서 사용자 값 가져오기
		String title = request.getParameter("title"); 
		String contents = request.getParameter("contents");
		
		//db에서 쓸 값을 VO에 저장하기
		Notice notice = new Notice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(contents);
		
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.insertNoticeBoard(notice);
		
		//request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		//.forward(request, response);	
		
		response.sendRedirect("noticeList");
	}
	
	
	//수정전 
	protected void beforeUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		Notice notice = new Notice();
		notice = noticeService.beforeUpdate(noticeNo);
		request.setAttribute("notice",notice);
		
		String noticeTitle = notice.getNoticeTitle();
		String noticeContent = notice.getNoticeContent();
		Date noticeDate = notice.getNoticeDate();
		
		request.setAttribute("noticeTitle", noticeTitle);
		request.setAttribute("noticeContent", noticeContent);
		request.setAttribute("noticeDate", noticeDate);
		request.setAttribute("noticeNo", noticeNo);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/update.jsp")
		.forward(request, response);	
	}
	
	
	//수정요청
	protected void updateRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		//view에서 사용자 값 가져오기
		String title = request.getParameter("title"); 
		String contents = request.getParameter("contents");
		
		
		//수정된 값을 VO에 저장하기
		Notice notice = new Notice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(contents);
		notice.setNoticeNo(noticeNo);
		
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.updateRequest(notice);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/update.jsp")
		.forward(request, response);	
	}

	//삭제
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			
		
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.deleteEventBoard(noticeNo);
		
		response.sendRedirect("eventList");
		
		
	}
	
	
	
	
	
}
