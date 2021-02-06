<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/main.css" />
	<link rel="stylesheet" href="/resources/css/group/group_view.css" />
	<noscript><link rel="stylesheet" href="/resources/css/noscript.css" /></noscript>
	<!-- 모달 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
	<!-- fullCalendar 사용 -->
	<link href='../../../resources/css/cal_lib/main.css' rel='stylesheet' />
    <script src='../../../resources/css/cal_lib/main.js'></script>
    <script>
    	document.addEventListener('DOMContentLoaded', function() {
    		/* 날짜 지정  */
    		var today = new Date(); 
    		today.setDate(1); //달력은 이번달 초부터 다음달 말까지 표시해준다.
    		var endday = new Date(today.getFullYear(),today.getMonth()+2,0);
    		endday.setDate(endday.getDate()+1);
    		/* 결제일, 입금일 지정 !!(el사용하여 입력) */
    		var payDay = '2021-02-17';
    		var prepayDay = '2021-02-14';
    		/* 사용 가능한 날짜 지정 !!(el사용하여 입력) */
    		var accessableDay = '2021-03-14';
    		/* 캘린더 생성 */
    		var calendarEl = document.getElementById('calendar');
    		var calendar = new FullCalendar.Calendar(calendarEl, {
    			initialView: 'dayGridMonth',
    			validRange: {
       	        	start: today,
       	        	end: endday
       	    	},
       	    	events: [
       	    		{
       	    			id: 'payDate',
       	    			title: '결제일',
       	    			start: payDay,
       	    			color:'red'
       	    		},
       	    		{
       	    			id: 'prepayDay',
       	    			title: '입금일',
       	    			start: prepayDay,
       	    			color: 'red'
       	    		},
       	    		{
       	    			id: 'accessableDay',
       	    			start: prepayDay,
       	    			end: accessableDay,
       	    			backgroundColor:'#00fff2',
       	    			overlap:false,
       	    			display:'background'
       	    		}
       	    	],
    			
			});
    	/* 캘린더 크기 지정 */
    	calendar.setOption('contentHeight','auto');
    	/* 캘린더 언어 지정, 캘린더 출력  */
        calendar.setOption('locale','kr');
        calendar.render();
      });
    </script>
</head>
	<body class="no-sidebar is-preload">
		<div id="page-wrapper">
			<!-- Header -->
			<div id="header">
				<!-- Inner -->
				<div class="inner">
					<header>
						<h1><a href="index.html" id="logo">Let's Share</a></h1>
					</header>
				</div>
				<!-- Nav -->
				<nav id="nav">
					<ul>
						<li><a href="index.html">Home</a></li>
						<li><a href="#">구매자 모집</a></li>
						<li><a href="left-sidebar.html">구매 참여</a></li>
						<li><a href="right-sidebar.html">신고 게시판</a></li>
						<li><a href="no-sidebar.html">공지 사항</a></li>
					</ul>
				</nav>
			</div>
			<!-- Main -->
			<div class="wrapper style1">
				<div class="top">
					<div class="logo_box"><img src="../../../resources/images/group/${group.getServiceCode()}.PNG" alt=""></div>
					<div class="title_box">
						<a href="">
							<c:choose>
								<c:when test="${group.getServiceCode() eq 'SR01'}">
									넷플릭스 서비스 (No.${group.getGroupId()}) 
								</c:when>
								<c:when test="${group.getServiceCode() eq 'SR02'}">
									왓챠 서비스 (No.${group.getGroupId()})
								</c:when>
								<c:when test="${group.getServiceCode() eq 'SR03'}">
									쿠팡플레이 서비스 (No.${group.getGroupId()})
								</c:when>
								<c:when test="${group.getServiceCode() eq 'SR04'}">
									웨이브 서비스 (No.${group.getGroupId()})
								</c:when>
								<c:when test="${group.getServiceCode() eq 'SR05'}">
									티빙 서비스 (No.${group.getGroupId()})
								</c:when> 
							</c:choose>
						</a>
					</div>
				</div>
				<!-- 캘린더 호출 -->
				<div class="calendar_box">
					<div id="calendar"></div>
				</div>
				<div class="calendar_box">
					<div id="tip_msg"><div id="color-box"></div>는 서비스를 이용 가능한 기간입니다.</div>
				</div>
				
				<!-- 그룹원 테이블 -->
				<c:if test="${!matchingList.isEmpty()}"> 
					<div class="memberTitle">
						<div>그룹원</div>
					</div>
					<div class="memberBox">
						<table class="groupMemTable">
							<thead>
								<tr align="center" bgcolor="white">
									<th>ID</th>
									<th>그룹 가입일</th>
									<th>서비스 이용 시작</th>
									<th>서비스 이용 마감</th>
									<th>입금 확인 버튼</th>
								</tr>
							</thead>
							<tbody class="tbody">
								<c:forEach var="groupMember" items="${matchingList}">
									<tr align="center">
										<td>${groupMember.getMemberId()}</td>
										<td>${groupMember.getRegDate()}</td>
										<td>${groupMember.getStDate()}</td>
										<td>${groupMember.getExDate()}</td>
										<td>
											<c:if test="${group.getMemberId()==sessionScope.userId}">
												<button onclick="payConfirm('${groupMember.getMemberId()}', ${groupMember.getGroupId()})">입금 확인</button>
											</c:if>
											<c:if test="${group.getMemberId()!=sessionScope.userId}">
												<i class="fas fa-user-slash"></i>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:if>
				
				<!-- 버튼  -->
				<div class="bottom">
					<div class="">
						<button>ID/PW 확인</button>
					</div>
					<c:if test="${group.getMemberId()!=sessionScope.userId}">
					<div class="">
						<button><a href="#ex1" rel="modal:open">서비스 결제</a></button>
					</div>
					</c:if>
					<div class="">
						<button>모임 탈퇴</button>
					</div>
				</div>
				<!-- 그룹 대기 테이블 -->
				<c:if test="${!standByList.isEmpty()}">
					<c:if test="${group.getMemberId()==sessionScope.userId}"> <!--그룹장만 그룹대기 리스트 확인가능-->
						<div class="memberTitle">
							<div>그룹 대기 리스트</div>
						</div>
						<div class="memberBox">
							<table class="groupMemTable">
								<thead>
									<tr align="center" bgcolor="white">
										<th>ID</th>
										<th>승인</th>
										<th>거절</th>
									</tr>
								</thead>
								<tbody class="tbody">
									<c:forEach var="standBy" items="${standByList}">
										<tr align="center">
											<td>${standBy.getMemberId()}</td>
											<td>
												<button class="btn_approval" onclick="approval('${standBy.getMemberId()}',${standBy.getGroupId()})">승인</button>
											</td>
											<td><button class="btn_refuse" onclick="refuse('${standBy.getMemberId()}',${standBy.getGroupId()})">거절</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</c:if>
				</c:if>
			</div>
			<!-- 결제 모달 -->
			<div id="ex1" class="modal">
				<h5>결제 정보</h5>
				<div>1. 본 서비스의 하루 이용 가격은 약 <a>[${servicePerDay}]원</a> 입니다.</div>
				<div>2. <a>[본인이 입력한 일 수] x [${servicePerDay}원]</a> 이 입금 금액 입니다.</div>
				<div>3. 입금 계좌는 <a>[${group.getAccountInfo()}]</a> 입니다.</div>
				<div>4. 최소 15일 최대 90일 결제 가능합니다.</div>
				<div>5. 환불은 불가능하고 그룹장이 입금확인 시점 부터 입력한 일 수    만큼 ID,PW 열람이 가능합니다.</div>
				<div class="payBox">
					<form action="/group/pay" method="POST">
						<input type="number" name="payDate" id="payDate" min="15" max="90">
						<input type="number" name="servicePerDay" id="servicePerDay" value="${servicePerDay}">
						<input type="text" name="memberId" id="memberId" value="${sessionScope.userId}">
						<input type="text" name="groupId" id="groupId" value="${group.getGroupId()}">
						<input type="submit" value="입금">
					</form>
				</div>
			</div>
			<!-- Footer -->
			<div id="footer">
				<div style="text-align: center;">Copyright © 1998-2021 KH Information Educational Institute All Right Reserved</div>
			</div>
		</div>
		<!-- Scripts -->
		<script src="/resources/js/jquery.min.js"></script>
		<script src="/resources/js/jquery.dropotron.min.js"></script>
		<script src="/resources/js/jquery.scrolly.min.js"></script>
		<script src="/resources/js/jquery.scrollex.min.js"></script>
		<script src="/resources/js/browser.min.js"></script>
		<script src="/resources/js/breakpoints.min.js"></script>
		<script src="/resources/js/util.js"></script>
		<script src="/resources/js/main.js"></script>
		<!-- 모달 -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
		<!-- 스크립트 -->
		<script type="text/javascript">
			let approval = (memberId, groupId) => {
				location.href="/group/approval?userId="+memberId+"&groupId="+groupId;
			}
			let refuse = (memberId, groupId) => {
				location.href="/group/refuse?userId="+memberId+"&groupId="+groupId;
			}
			let payConfirm = (memberId, groupId) => {
				location.href="/group/payConfirm?userId="+memberId+"&groupId="+groupId;
			}
		</script>
	</body>
</html>