package com.kh.group.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.sms.SMS;
import com.kh.group.model.service.GroupService;
import com.kh.group.model.vo.Group;
import com.kh.group.model.vo.GroupMatching;
import com.kh.group.model.vo.GroupStandBy;
import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

@WebServlet("/group/*")
public class GroupController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private GroupService groupService = new GroupService();
    private MemberService memberService = new MemberService();
    
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
		case "IdPwConfirm" : IdPwConfirm(request, response); break;
		case "PwChange" : PwChange(request, response); break;
		case "out" : outGroup(request, response); break;
		case "close" : closeGroup(request, response); break;
		case "viewlist" : goViewList(request, response); break;
		case "autoMatching" : autoMatching(request, response); break;
		
		default : 
			response.setStatus(404);
			break;
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void goForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/group/group_form.jsp")
		.forward(request, response);
	}
	
	// group_view_list에서 클릭한 groupId의 그룹뷰를 보여준다
	protected void goView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		System.out.println(groupId);
		
		//3. SH_STAND_BY 그룹에 대기중인 인원의 데이터를 Arraylist로 가져온다 ()
		ArrayList<GroupStandBy> standByList = groupService.getStandByList(groupId);
		request.setAttribute("standByList", standByList);
		
		//4. SH_MATCHING 그룹원 데이터를 Arraylist로 가져온다
		ArrayList<GroupMatching> matchingList = groupService.getMatching(groupId);
		request.setAttribute("matchingList", matchingList);
		
		//5. SH_GROUP 데이터를 가져온다
		Group group = groupService.getGroup(groupId);
		request.setAttribute("group", group);
		System.out.println(group.getAutoDate());
		
		//6. SH_SER_CODE 정보를 가져온다 
		int servicePerDay = groupService.getServicePerDay(group.getServiceCode());
		request.setAttribute("servicePerDay", servicePerDay);
		
		
		request.getRequestDispatcher("/WEB-INF/view/group/group_view.jsp")
		.forward(request, response);
		
	}
	
	// group_search.jsp 페이지 로드
	protected void goSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

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
		int allGroupCnt = 0 , allPageCnt = 0;
		String service = "";
		
		//3. 전체 게시글 개수 and 전체 페이지 개수 구한다.
		if(request.getParameter("service")==null || request.getParameter("service").equals("")) { 
			allGroupCnt = groupService.getGroupCnt(); //검색하지 않은 경우
		}else {
			service = request.getParameter("service"); //검색한 경우 검색한 서비스를 변수에 넣어주고 검색한 서비스 그룹의 개수를 가져온다.
			allGroupCnt = groupService.getGroupCnt(service);
		}
		
		if(allGroupCnt%6==0) {
			allPageCnt = allGroupCnt/6; 
		}else {
			allPageCnt = (allGroupCnt/6)+1;
		}
		
		//4. 사용자가 접근한 페이지가 전체페이지보다 많거나 1보다 작다면 -> 그냥 1페이지 보여줌
		ArrayList<Group> groupList = null;
		if(page>allPageCnt || page<1) {
			System.out.println("잘못된 접근입니다");
			page = 1;
		}
		
		start = 1+(page-1)*6;
		end = page*6;
		if(request.getParameter("service")==null || request.getParameter("service").equals("")) {
			//검색하지 않은 경우
			groupList = groupService.getGroupList(start, end);
		}else {
			//검색한 경우
			groupList = groupService.getGroupList(service, start, end);
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
		
		request.setAttribute("service", service);
		request.setAttribute("currentPage", page);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("firstPage", firstPage);
		request.setAttribute("pageList", pageList);
		request.setAttribute("groupList", groupList);
		
		request.getRequestDispatcher("/WEB-INF/view/group/group_search.jsp")
		.forward(request, response);
	}
	
	//그룹 만들기 
	protected void receiveGroupInform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// bank_account, service_id,  service_pw, service, date, bank 가 넘어옴
		String bank = request.getParameter("bank_real");
		String bank_account = request.getParameter("bank_account");
		String service = request.getParameter("service");
		String service_id = request.getParameter("service_id");
		String service_pw = request.getParameter("service_pw");
		int memberCntWish = Integer.parseInt(request.getParameter("groupMemberCnt"));
		
		Member member = (Member) request.getSession().getAttribute("user");
		Group group = new Group();
		group.setMemberId(member.getMbId()); //세션에서 아이디값 넣어준다
		group.setAccountInfo(bank+" "+bank_account);
		group.setServiceCode(service);
		group.setShareId(service_id);
		group.setSharePw(service_pw);
		group.setSharePw(service_pw);
		group.setMemberCntWish(memberCntWish);

		
		String memeberName = member.getMbName();
		
		// 1. SH_GROUP 테이블에 데이터 입력
		// 2. SH_MATCHING 테이블에 데이터 입력
		// 3. 프로시저 PL_UPDATE_MEMBER_CNT(SC_GROUP_ID.CURRVAL) 실행  => 3가지 쿼리 동시에 진행
		groupService.insertGroup(group, memeberName);
		
		request.setAttribute("msg", "모임을 정상적으로 등록하였습니다.");
		request.setAttribute("url", "/group/viewlist");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
	}
	
	//그룹 가입 신청
	protected void registerGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 그룹 아이디를 Get방식으로 받고 session에서 아이디 꺼내기
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		
		Member member = (Member) request.getSession().getAttribute("user");
		String userId = member.getMbId();
		
		//2. 그룹 대기 테이블에 넣어줌
		int res = groupService.insertStandBy(groupId, userId);
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
		Member member = memberService.selectMemberById(memberId);
		String memberName = member.getMbName();
		Group group = groupService.getGroup(groupId);
		if(group.getMemberCnt()<group.getMemberCntWish()) { 
			//그룹장이 지정한 수보다 멤버수가 적다면 
			// 1. 그룹 매칭 테이블에 정보 넣어주기
			// 2. 그룹 대기 테이블에서 정보 삭제해주기
			// 3. PL_UPDATE_MEMBER_CNT 프로시저 호출  => 프로시저를 하나 만들어주자(PL_APPROVAL_GROUP)
			groupService.approval(groupId, memberId, memberName);
			request.setAttribute("msg", "가입 승인이 완료되었습니다.");
			request.setAttribute("url", "/group/view?groupId="+groupId);
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			
		}else if(group.getMemberCnt()>=group.getMemberCntWish()){
			//그룹장이 지정한 수보다 멤버수가 많다면
			request.setAttribute("msg", "그룹에 더 이상 자리가 없습니다, 관리자에게 문의 주세요");
			request.setAttribute("url", "/group/view?groupId="+groupId);
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
			request.setAttribute("url", "/group/view?groupId="+groupId);
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
			.forward(request, response);
		}
	}

	//입금 신청(sms주석처리)
	protected void payMoney(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String payDateS = request.getParameter("payDate");
		String servicePerDayS = request.getParameter("servicePerDay");
		String groupIdS = request.getParameter("groupId");
		
		Member member = (Member) request.getSession().getAttribute("user");
		String memberId = member.getMbId();
		
		int payDate = Integer.parseInt(payDateS);
		int servicePerDay = Integer.parseInt(servicePerDayS);
		int groupId = Integer.parseInt(groupIdS);
		
		//1. SH_MATCHING 테이블에 PAY_DATE Update해준다
		int res = groupService.updatePayDate(memberId, groupId, payDate);
		
		if(res==1) {
			//2-1. member에서 전화번호 가져온 후 (servicePerDay * payDate)원 계좌정보 입금 요망 sms 
			int sum = payDate * servicePerDay;
			//Member member = memberService.selectMemberById(memberId);
			Group group = groupService.getGroup(groupId);
			String accountInfo = group.getAccountInfo();
			
			String to = member.getMbtel(); // 저나번호 가져와야함
			String content = "Let's Share 입니다. \n["+accountInfo+"] \n결제금액 : "+sum+"원";
			
			SMS sms = new SMS();
			sms.sendSMS(to, content);
		}
		request.setAttribute("msg", "정상적으로 입금신청 되었습니다.");
		request.setAttribute("url", "/group/view?groupId="+groupId);
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
		
	}

	//입금 확인(sms주석처리)
	protected void payConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("userId");
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		
		//1. SH_MATCHING의 ST_DATE가 null이라면 sysdate로 바꿔준다.
		GroupMatching groupMatching = groupService.getMatching(groupId, memberId);
		if(groupMatching.getStDate() == null) {
			groupService.updateStDate(groupId, memberId); //ST_DATE를 sysdate로 바꿈	
		}
		
		//2. 만기일이 존재한다면 PL_SET_EXDATE_FROM_EXDATE || 존재하지 않는다면 PL_SET_EXDATE_FROM_STDATE
		if(groupMatching.getExDate()==null) {
			groupService.execProcedureSEFS(groupId, memberId);
		}else {
			groupService.execProcedureSEFE(groupId, memberId);
		}
		
		//3. 마지막으로 PAY_DATE를 0으로 바꿔주자
		groupService.updatePayDate(memberId, groupId, 0);
		
		//4. ID,PW 보내주자(sms)
		Member member = memberService.selectMemberById(memberId);
		Group group = groupService.getGroup(groupId);
		String shareID = group.getShareId();
		String sharePW = group.getSharePw();
		String to = member.getMbtel(); // 전송할 전화번호
		String content = "Let's Share 입니다.\n입금이 확인되어 ID,PW 안내드립니다.\n아이디 : "+shareID+"\n비밀번호 :"+sharePW; 
		SMS sms = new SMS();
		sms.sendSMS(to, content);
		
		//5.view페이지 리로드
		request.setAttribute("msg", "정상적으로 입금 확인되었습니다.");
		request.setAttribute("url", "/group/view?groupId="+groupId);
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp")
		.forward(request, response);
		
	}

	//ID, PW 확인(?)(sms주석처리)
	protected void IdPwConfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. groupId를 받고 session에서 Id 가져온다.
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		Member member = (Member) request.getSession().getAttribute("user");
		String userId = member.getMbId();
		
		//2. SH_MATCHING 테이블에서 EX_DATE를 가져온다
		GroupMatching groupMatching = groupService.getMatching(groupId, userId);
		Date exDate = groupMatching.getExDate();		
		Date today = new Date(System.currentTimeMillis());
		
		//3. 만기일 비교해서 문자 처리해주자
		if(exDate == null) {
			//3-1.만기일이 없음 
			System.out.println("만기일 없음");
			request.setAttribute("msg", "서비스 ID,PW를 열람할 권한이 없습니다.\n결제를 진행하십시오.");
			request.setAttribute("url", "/group/view?groupId="+groupId);
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}else {
			if(today.after(exDate)) {
				//3-2. 만기일 지났음
				request.setAttribute("msg", "서비스 ID,PW를 열람할 권한이 없습니다.\n결제를 진행하십시오.");
				request.setAttribute("url", "/group/view?groupId="+groupId);
				request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			}else {
				//3-3. 만기일 안지남 문자 보내자
				//Member member = memberService.selectMemberById(memberId);
				Group group = groupService.getGroup(groupId);
				String shareID = group.getShareId();
				String sharePW = group.getSharePw();
				String to = member.getMbtel(); // 전송할 전화번호
				String content = "Let's Share 입니다.\nID,PW 안내드립니다.\n아이디 : "+shareID+"\n비밀번호 :"+sharePW; 
				SMS sms = new SMS();
				sms.sendSMS(to, content);
				request.setAttribute("msg", "회원님의 전화번호로 ID,PW를 보내드렸습니다.");
				request.setAttribute("url", "/group/view?groupId="+groupId);
				request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
			}
		}
		
	}

	//서비스 PW 변경 (sms주석처리)
	protected void PwChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		String servicePw = request.getParameter("servicePw");
		
		//1. DB에 비밀번호를 변경해주자
		groupService.updateServicePw(groupId, servicePw);
		
		//2. 변경되었다고 문자보내주자
		//2-1. Arraylist형태로 SH_MATCHING 테이블에서 가져오자 group_id가 같은것들을 
		ArrayList<GroupMatching> groupMatchings = groupService.getGroupMember(groupId);
		
		//2-2. 세션에서 아이디를 가져오자 (그룹장의 아이디) [임시]
		HttpSession session = request.getSession();
		Member groupLeader =  (Member)session.getAttribute("user");
		String groupLeaderId = groupLeader.getMbId();
		
		//2-3. 반복문을 돌며 문자 보내주자 단! 그룹장에게는 문자보내지 않도록 && Ex date가 지나지 않은 사람들만
		Date today = new Date(System.currentTimeMillis());
		for (GroupMatching groupMatching : groupMatchings) {
			if(groupMatching.getExDate() != null) { //null이 아니고
				if(!today.after(groupMatching.getExDate())) { //지나지 않았다면
					if(!groupMatching.getMemberId().equals(groupLeaderId)) {
						SMS sms = new SMS();
						Member member = memberService.selectMemberById(groupMatching.getMemberId());
						Group group = groupService.getGroup(groupId);
						String sharePW = group.getSharePw();
						String to = member.getMbtel(); // 전송할 전화번호
						String content = "Let's Share 입니다.\n서비스 비밀 번호가 변경되어 알림 드립니다.\n비밀번호 : "+sharePW;	
						sms.sendSMS(to, content);	
					}			
				}
			}
		}
		
		//3. 다시 view 페이지로 리로드
		request.setAttribute("msg", "정상적으로 변경되었습니다.");
		request.setAttribute("url", "/group/view?groupId="+groupId);
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
	}

	//그룹 탈퇴(sms 해야댐)
	protected void outGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		String memberId = request.getParameter("memberId");
		
		//1. 매칭테이블에서 삭제해주고, 그룹원수 다시 설정하는 프로시저 실행
		groupService.execProcedureOG(groupId, memberId);
		
		//2. index 페이지로
		request.setAttribute("msg", "그룹 탈퇴 되었습니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
	}

	//그룹 해지
	protected void closeGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String groupSId = request.getParameter("groupId");
		int groupId = Integer.parseInt(groupSId);
		String closeSDate = request.getParameter("closeDate");
		Date closeDate = Date.valueOf(closeSDate);
		
		//1. 입력한 날짜가 그룹원들의 MAX(EX_DATE) 지났는지 확인해야함
		Date maxExDate = groupService.getMaxExDate(groupId);
		if(maxExDate == null || closeDate.after(maxExDate)) {
			//2.  MAX(EX_DATE) 지남 => 해지 설정 해주자
			groupService.updateCloseDate(groupId, closeDate);
			request.setAttribute("msg", "정상적으로 그룹 해지 신청되었습니다.");
			request.setAttribute("url", "/group/view?groupId="+groupId);
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}else {
			//3.  MAX(EX_DATE) 지나지 않음 
			request.setAttribute("msg", "그룹원 서비스 이용 만기일이 모두 지나야 해지 하실 수 있습니다.       그룹원 최종 만기일 : "+maxExDate);
			request.setAttribute("url", "/group/view?groupId="+groupId);
			request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);
		}
	}
	
	//속한 모임 리스트 보여줌
	protected void goViewList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1.세션에서 userId를 가져온다
		Member member = (Member) request.getSession().getAttribute("user");
		String userId = member.getMbId();
		
		//2.userId가 속해있는 그룹정보 를 arraylist로 가져온다.
		ArrayList<Integer> groupIdList = groupService.getgroupIdList(userId);
		
		//3.가져온 ID리스트로 groupList로 만들어줌 
		ArrayList<Group> groupList = new ArrayList<Group>();
		if(groupIdList.isEmpty()) {
			System.out.println("속한 그룹이 없습니다");
		}else {
			for (Integer groupId : groupIdList) {
				groupList.add(groupService.getGroup(groupId));
			}
		}
		
		//4. gropuList 보내줌
		request.setAttribute("groupList", groupList);
		request.getRequestDispatcher("/WEB-INF/view/group/group_view_list.jsp")
		.forward(request, response);
		
	}

	//자동 매칭 등록
	protected void autoMatching(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int groupId = Integer.parseInt(request.getParameter("groupId"));
		int res = 0;
		
		if(request.getParameter("reg").equals("1")) {
			res = groupService.updateAutoDate(groupId, "sysdate");
			
		}else if(request.getParameter("reg").equals("0")) {
			res = groupService.updateAutoDate(groupId, "null");
		}
		
		request.setAttribute("msg", "정상적으로 변경되었습니다.");
		request.setAttribute("url", "/group/view?groupId="+groupId);
		request.getRequestDispatcher("/WEB-INF/view/common/result.jsp").forward(request, response);

	}
}
