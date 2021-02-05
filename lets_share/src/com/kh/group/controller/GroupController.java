package com.kh.group.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.group.model.service.GroupService;
import com.kh.group.model.vo.Group;
import com.kh.group.model.vo.GroupMatching;
import com.kh.group.model.vo.GroupStandBy;

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
		case "register" : registerGroup(request, response); break;
		case "approval" : approvalGroup(request, response); break;
		case "refuse" : refuseGroup(request, response); break;
		case "pay" : payMoney(request, response); break;
		case "payConfirm" : payConfirm(request, response); break;
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
	
	// Session에서 회원정보를 가지고 회원이 소속된 그룹의 Group_view를 그려준다
	protected void goView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.[임시]세션에서 userId를 가져온다
		HttpSession session = request.getSession();
		session.setAttribute("userId", "test1");
		String userId = (String) session.getAttribute("userId");
		
		//2. userId가 속한 그룹의 grouoId를 가져온다
		int groupId = 0;
		groupId = groupService.getGroupId(userId);
		
		if(groupId == 0) {
			System.out.println("속한 그룹이 없습니다");
		}else {
			//3. SH_STAND_BY 그룹에 대기중인 인원의 데이터를 Arraylist로 가져온다 ()
			ArrayList<GroupStandBy> standByList = groupService.getStandByList(groupId);
			request.setAttribute("standByList", standByList);
			
			//4. SH_MATCHING 그룹원 데이터를 Arraylist로 가져온다
			ArrayList<GroupMatching> matchingList = groupService.getMatching(groupId);
			request.setAttribute("matchingList", matchingList);
			
			//5. SH_GROUP 데이터를 가져온다
			Group group = groupService.getGroup(groupId);
			request.setAttribute("group", group);
			
			//6. SH_SER_CODE 정보를 가져온다 
			int servicePerDay = groupService.getServicePerDay(group.getServiceCode());
			request.setAttribute("servicePerDay", servicePerDay);
			
		}
		
		request.getRequestDispatcher("/WEB-INF/view/group/group_view.jsp")
		.forward(request, response);
	}
	
	protected void goSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Group> groupList = null;
		String groupId = request.getParameter("groupId");
		String service = request.getParameter("service");
		
		if((groupId==null && service==null) || (groupId.equals("") && service.equals(""))) {
			groupList = groupService.getGroupList();
		}else if(groupId.equals("")&&service!=null) {
			//서비스만 검색
			groupList = groupService.getGroupListService(service);
		}else if(groupId!=null&&service.equals("")) {
			//그룹 아이디만 검색
			groupList = groupService.getGroupListId(groupId);
		}else if(groupId!=null&&service!=null) {
			groupList = groupService.getGroupList(groupId, service);
		}

		request.setAttribute("groupList", groupList);
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
		group.setMemberId("lee5031207"); //[임시] 원래 세션에서 로그인한 회원의 값을 찾아 넣어준다
		group.setAccountInfo(bank+" "+bank_account);
		group.setServiceCode(service);
		group.setShareId(service_id);
		group.setSharePw(service_pw);
		group.setSharePw(service_pw);
		group.setGroupPayDate(groupPayDate);
		
		// 1. SH_GROUP 테이블에 데이터 입력
		// 2. SH_MATCHING 테이블에 데이터 입력
		// 3. 프로시저 PL_UPDATE_MEMBER_CNT(SC_GROUP_ID.CURRVAL) 실행  => 3가지 쿼리 동시에 진행
		groupService.insertGroup(group);
		
		request.setAttribute("msg", "모임을 정상적으로 등록하였습니다.");
		request.setAttribute("url", "/group/search");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	//그룹 가입 신청
	protected void registerGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupSId = request.getParameter("groupId");
		String userId = request.getParameter("userId");
		int groupId = Integer.parseInt(groupSId);
		int res = groupService.insertStandBy(groupId, userId);
		System.out.println(res);
		
		request.setAttribute("msg", "가입 신청을 정상적으로 완료하였습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}

	//그룹 가입 승인
	protected void approvalGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		String memberId = request.getParameter("userId");
		Group group = groupService.getGroup(groupId);
		if(group.getMemberCnt()<group.getMemberCntWish()) { 
			//그룹장이 지정한 수보다 멤버수가 적다면 
			// 1. 그룹 매칭 테이블에 정보 넣어주기
			// 2. 그룹 대기 테이블에서 정보 삭제해주기
			// 3. PL_UPDATE_MEMBER_CNT 프로시저 호출  => 프로시저를 하나 만들어주자(PL_APPROVAL_GROUP)
			groupService.approval(groupId, memberId);
			request.setAttribute("msg", "가입 승인이 완료되었습니다.");
			request.setAttribute("url", "/group/view");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			
		}else if(group.getMemberCnt()>=group.getMemberCntWish()){
			//그룹장이 지정한 수보다 멤버수가 많다면
			request.setAttribute("msg", "그룹에 더 이상 자리가 없습니다, 관리자에게 문의 주세요");
			request.setAttribute("url", "/group/view");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}
	}
	
	//그룹 가입 거절
	protected void refuseGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		String memberId = request.getParameter("userId");
		int res = groupService.refuse(groupId, memberId);
		if(res==1) {
			request.setAttribute("msg", "정상적으로 거절되었습니다");
			request.setAttribute("url", "/group/view");
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}
	}

	//입금 신청(sms 해야댐)
	protected void payMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payDateS = request.getParameter("payDate");
		String servicePerDayS = request.getParameter("servicePerDay");
		String groupIdS = request.getParameter("groupId");
		String memberId = request.getParameter("memberId");
		
		int payDate = Integer.parseInt(payDateS);
		int servicePerDay = Integer.parseInt(servicePerDayS);
		int groupId = Integer.parseInt(groupIdS);
		
		//1. SH_MATCHING 테이블에 PAY_DATE Update해준다
		int res = groupService.updatePayDate(memberId, groupId, payDate);
		
		if(res==1) {
			//2. 계좌 번호 등등을 문자로 보낸다.
			
		}
		request.setAttribute("msg", "정상적으로 입금신청 되었습니다.");
		request.setAttribute("url", "/group/view");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}

	//입금 확인
	protected void payConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("userId");
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		
		//1. SH_MATCHING의 ST_DATE가 null이라면 sysdate로 바꿔준다.
		
		
		//3. SH_MATCHING의 PAY_DATE를 0으로 바꿔준다
		//groupService.updatePayDate(memberId, groupId, 0);
		
		
	}
}
