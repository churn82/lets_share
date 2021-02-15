package com.kh.report.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.exception.ToAlertException;
import com.kh.group.model.service.GroupService;
import com.kh.member.model.vo.Member;
import com.kh.report.model.service.ReportService;
import com.kh.report.model.vo.Report;


@WebServlet("/report/*")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ReportService reportService = new ReportService();
	GroupService groupService = new GroupService();
	
    public ReportController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "form": reportForm(request,response); break;
		case "write" : writeReport(request,response); break;
		case "formM": reportFormM(request,response);
		break;
		case "detail": goDetail(request,response); break;
		case "listAll" : listAll(request,response); break;
		case "reply" : replyReport(request, response); break;
		case "listClear" : listClear(request,response); break;
		case "viewM" : reportViewM(request,response);
		break;
		default : response.setStatus(404);
		break;
		}
	
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void reportForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = (Member) request.getSession().getAttribute("user");
		ArrayList<Integer> groupIdList = groupService.getgroupIdList(member.getMbId());
		
		request.setAttribute("groupIdList", groupIdList);
		request.getRequestDispatcher("/WEB-INF/view/report/reportWrite.jsp")
		.forward(request, response);
	}
	
	private void writeReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String groupSId = request.getParameter("groupId");
		String contents = request.getParameter("contents");
		int groupId = Integer.parseInt(groupSId);
		Member member = (Member) request.getSession().getAttribute("user");
		String memberId = member.getMbId();
				
		int res = reportService.writeReport(memberId, title, groupId, contents);
		
		response.sendRedirect("/report/listAll");
	}
	
	private void reportFormM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/report/reportWrite_m.jsp")
		.forward(request, response);
	}
	
	private void goDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int reportIdx = Integer.parseInt(request.getParameter("reportIdx"));
		Report report = reportService.getReport(reportIdx);
		Member member = (Member) request.getSession().getAttribute("user");
		
		if(report.getMemberId().equals(member.getMbId()) || member.getMblevel().equals("MB10")) {
			//접근 권한이 있다면
			request.setAttribute("report", report);
			request.getRequestDispatcher("/WEB-INF/view/report/reportDetail.jsp")
			.forward(request, response);
		}else {
			//접근 권한이 없다면
			request.setAttribute("back", true);
			request.getRequestDispatcher("/WEB-INF/view/report/reportDetail.jsp")
			.forward(request, response);
		}
	}
	
	private void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자가 클릭한 페이지를 가져온다 but, 처음 신고페이지 왔을시 1로 초기화
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
		int allReportCnt = 0 , allPageCnt = 0;
		String select = "", searchText = "";
		
		
		//3. 우리 신고글의 전체 페이지 개수를 구한다. ex. 신고글이 36개이면 총 4개의 페이지를 보여줄 수 있다.
		if(request.getParameter("searchText") == null) {
			allReportCnt = reportService.getreportCnt();
		}else {
			select = request.getParameter("select");
			searchText = request.getParameter("searchText");
			allReportCnt = reportService.getreportCnt(select, "%"+searchText+"%");
		}
		
		
		if(allReportCnt%10==0) {
			allPageCnt = allReportCnt/10; 
		}else {
			allPageCnt = (allReportCnt/10)+1;
		}
		
		
		//4. 사용자가 접근한 페이지가 전체페이지보다 많거나 1보다 작다면 -> 예외처리
		ArrayList<Report> reportList = null;
		if(page>allPageCnt || page<1) {
			System.out.println("잘못된 접근입니다");
		}else {
			// 3-1. 사용자가 클릭한 페이지의 NoticeList를 가져온다 
			//      ex) 1페이지 : 가장최신글 10개 , 2페이지 : 가장최신글 10개를 뺀 10개
			start = 1+(page-1)*10;
			end = page*10;
			if(request.getParameter("searchText") == null) {
				reportList = reportService.getReportList(start, end);
			}else {
				reportList = reportService.getReportList(start, end, select, "%"+searchText+"%");
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
		request.setAttribute("reportList", reportList);		
		
		request.getRequestDispatcher("/WEB-INF/view/report/reportListAll.jsp")
		.forward(request, response);
	}
	
	// 관리자가 신고글에 처리내용 댓글다는 메서드
	private void replyReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reply = request.getParameter("reply");
		int clear = Integer.parseInt(request.getParameter("select"));
		int reportIdx = Integer.parseInt(request.getParameter("reportIdx"));
		
		int res = reportService.replyReport(reply, clear, reportIdx);
		
		response.sendRedirect("/report/detail?reportIdx="+reportIdx);
	}
	

	// 처리 완료된 리스트
	private void listClear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 사용자가 클릭한 페이지를 가져온다 but, 처음 신고페이지 왔을시 1로 초기화
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
		int allReportCnt = 0 , allPageCnt = 0;
		String select = "", searchText = "";
		
		
		//3. 우리 신고글의 전체 페이지 개수를 구한다. ex. 신고글이 36개이면 총 4개의 페이지를 보여줄 수 있다.
		if(request.getParameter("searchText") == null) {
			allReportCnt = reportService.getreportCnt(1);
		}else {
			select = request.getParameter("select");
			searchText = request.getParameter("searchText");
			allReportCnt = reportService.getreportCnt(1, select, "%"+searchText+"%");
		}
		
		
		if(allReportCnt%10==0) {
			allPageCnt = allReportCnt/10; 
		}else {
			allPageCnt = (allReportCnt/10)+1;
		}
		
		
		//4. 사용자가 접근한 페이지가 전체페이지보다 많거나 1보다 작다면 -> 예외처리
		ArrayList<Report> reportList = null;
		if(page>allPageCnt || page<1) {
			System.out.println("잘못된 접근입니다");
		}else {
			// 3-1. 사용자가 클릭한 페이지의 NoticeList를 가져온다 
			//      ex) 1페이지 : 가장최신글 10개 , 2페이지 : 가장최신글 10개를 뺀 10개
			start = 1+(page-1)*10;
			end = page*10;
			if(request.getParameter("searchText") == null) {
				reportList = reportService.getReportList(start, end, 1);
			}else {
				reportList = reportService.getReportList(start, end, 1, select, "%"+searchText+"%");
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
		request.setAttribute("reportList", reportList);		
		
		request.getRequestDispatcher("/WEB-INF/view/report/reportListClear.jsp")
		.forward(request, response);
	}
	
	
	private void reportViewM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/report/reportView_m.jsp")
		.forward(request, response);
	}
}
