package com.kh.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.kh.report.model.vo.Report;

@WebServlet("/notice/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NoticeService noticeService = new NoticeService();
    
	
    public NoticeController() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		case "eventWriter" :
			eventWriter(request,response); break;
		case "eventWriterImpl" :
			eventWriterImpl(request, response); break;
		case "beforeUpdate" :
			beforeUpdate(request, response); break;
		case "updateRequest" :
			updateRequest(request, response); break;	
		case "deleteEvent" :
			deleteEvent(request, response); break;
		case "deleteNotice" :
			deleteNotice(request, response); break;
		case "mypage" :
			mypage(request, response); break;
		default : response.setStatus(404); break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//공지리스트
	protected void goNoticeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Notice notice = new Notice();
		notice = noticeService.getTotalPosts(notice);
		//////////////////////////////////////
		
		//1. 사용자가 클릭한 페이지를 가져온다 but, 처음 공지페이지 왔을시 1로 초기화
		int page = 0;
		if(request.getParameter("page") == "" || request.getParameter("page") == null) {
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		//2. 사용할 변수들 초기화
		int start = 0, end = 0;
		int firstPage = 0, lastPage = 0;
		ArrayList<Integer> pageList = null;
		int allNoticeCnt = 0 , allPageCnt = 0;
		String select = "", searchText = "";
		
		
		//3. 전체 페이지 개수를 구한다. ex. 게시글이 36개이면 총 4개의 페이지를 보여줄 수 있다.
		if(request.getParameter("searchText") == null) {
			allNoticeCnt = notice.getNoticeTotalPosts();
		}else {
			select = request.getParameter("select");
			searchText = request.getParameter("searchText");
			allNoticeCnt = noticeService.getNoticeCnt(select, "%"+searchText+"%");
		}
		
		if(allNoticeCnt%10==0) {
			allPageCnt = allNoticeCnt/10; 
		}else {
			allPageCnt = (allNoticeCnt/10)+1;
		}
		
		
		//4. 사용자가 접근한 페이지가 전체페이지보다 많거나 1보다 작다면 -> 예외처리
		ArrayList<Notice> noticeList = null;
		if(page>allPageCnt || page<1) {
			System.out.println("잘못된 접근입니다");
		}else {
			// 3-1. 사용자가 클릭한 페이지의 NoticeList를 가져온다 
			//      ex) 1페이지 : 가장최신글 10개 , 2페이지 : 가장최신글 10개를 뺀 10개
			start = 1+(page-1)*10;
			end = page*10;
			if(request.getParameter("searchText") == null) {
				noticeList = noticeService.getNoticeList(start, end);
			}else {
				noticeList = noticeService.getNoticeList(start, end, select, "%"+searchText+"%");
			}
		
			// 3-2. 사용자가 만약 2페이지를 클릭하고 있다면 1~5를 보여주고 7페이지를 클릭하고 있다면 6~10을 보여줘여함
			// 3-2-1. 사용자가 볼 수 있는 첫번째 페이지, 마지막 페이지를 지정
			for(int i=page; i<= allPageCnt; i++) {
				if(i%5==0) {
					lastPage = i;
					firstPage = i-4;
					break;
				}else {
					lastPage = allPageCnt;
					firstPage = lastPage - ((lastPage%5)-1); 
				}
			}
			//3-2-2. 구한 페이지를 pageList에 넣어줌
			pageList = new ArrayList<Integer>();
			for(int i=firstPage; i<=lastPage; i++) {
				pageList.add(i);
			}
		}
		request.setAttribute("select", select);
		request.setAttribute("searchText", searchText);
		request.setAttribute("currentPage", page);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("pageList", pageList);
		request.setAttribute("noticeList", noticeList);	
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		.forward(request, response);
	}
	
	//이벤트 목록
	protected void goEventList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이벤트 목록
		ArrayList<Notice> noticeList = null;
		noticeList = noticeService.selectEventList();
		request.setAttribute("noticeList", noticeList);
		
		///////////////////////////////////////////////////
		//2. 사용할 변수들 초기화
		int start = 0, end = 0;
		int firstPage = 0, lastPage = 0;
		ArrayList<Integer> pageList = null;
		int allNoticeCnt = 0 , allPageCnt = 0;
		String select = "", searchText = "";
		
		select = request.getParameter("select");
		searchText = request.getParameter("searchText");
		
		allNoticeCnt = noticeService.getNoticeCnt(select, searchText);
	
		request.getRequestDispatcher("/WEB-INF/view/notice/event_list.jsp")
		.forward(request, response);
	}

	
	
	//공지 상세페이지
	protected void goNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));//게시글을 클릭하면 notice_no가 넘어옴
		
		Notice notice = new Notice();
		notice = noticeService.selectNoticeDetail(noticeNo);
		noticeService.hitCounter(noticeNo);
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
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		.forward(request, response);	
		
		
	}
	
	//이벤트 작성
	protected void eventWriter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.getRequestDispatcher("/WEB-INF/view/notice/event_writer.jsp")
		  .forward(request, response);
	}
	
	//이벤트 작성페이지
	protected void eventWriterImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//view에서 사용자 값 가져오기
		String title = request.getParameter("title"); 
		String contents = request.getParameter("contents");
		
		//db에서 쓸 값을 VO에 저장하기
		Notice notice = new Notice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(contents);
		
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.insertEventBoard(notice);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/event_list.jsp")
		.forward(request, response);	
		
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
		System.out.println("controller : "+noticeNo);
		//view에서 사용자 값 가져오기
		String title = request.getParameter("title"); 
		String contents = request.getParameter("contents");
		System.out.println(title);
		
		//수정된 값을 VO에 저장하기
		Notice notice = new Notice();
		notice.setNoticeTitle(title);
		notice.setNoticeContent(contents);
		notice.setNoticeNo(noticeNo);
		
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.updateRequest(notice);
		
		//request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		//.forward(request, response);	
	}

	//이벤트게시글 삭제
	protected void deleteEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.deleteEventBoard(noticeNo);
		
		request.getRequestDispatcher("/WEB-INF/view/notice/event_list.jsp")
		  .forward(request, response);
		
	}
	
	//공지 게시글 삭제
	protected void deleteNotice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			
		//저장한 vo를 service단을 통해 dao로 전달
		noticeService.deleteNoticeBoard(noticeNo);//notice로 바꿔야 함
		
		request.getRequestDispatcher("/WEB-INF/view/notice/notice_list.jsp")
		  .forward(request, response);
	}
	
	
	protected void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 request.getRequestDispatcher("/WEB-INF/view/notice/event_writer.jsp")
		  .forward(request, response);
	}
	
	
	
}
