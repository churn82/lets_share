<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/member/rank.css" />
	<link rel="stylesheet" href="/resources/css/main.css" />
	<noscript><link rel="stylesheet" href="/resources/css/noscript.css" /></noscript>
	<c:set var="context" value="${pageContext.request.contextPath}"/>
</head>
	<body class="no-sidebar is-preload">
		<div id="page-wrapper">
			<!-- Header -->
			<div id="header">
				<!-- Inner -->
				<div class="inner">
					<header>
						<h1><a href="${context}/index" id="logo">Let's Share</a></h1>
					</header>
				</div>
				<!-- Nav -->
				<nav id="nav">
					<ul>
						<li><a href="${context}/index">Home</a></li>
						<li><a href="${context}/group/form">구매자 모집</a></li>
						<li><a href="${context}/group/search">구매 참여</a></li>
						<li><a href="${context}/report/listAll">신고 게시판</a></li>
						<li><a href="${context}/notice/noticeList?p=1">공지 사항</a></li>
					</ul>
				</nav>
			</div>

			<!-- Main -->
			<div class="wrapper style1">
				<div class="left_menu">
					<div><a href="${context}/member/mypage"><i class="fas fa-user-alt"></i>마이페이지</a></div>
					<div><a href="${context}/member/rank"><i class="fas fa-trophy"></i>회원 랭킹</a></div>
					<div><a href="${context}/member/modify"><i class="fas fa-edit"></i>회원 정보 수정</a></div>
					<c:if test="${sessionScope.user.mblevel=='MB10'}">
						<div><a href="/member/adminMember"><i class="fas fa-edit"></i>  회원 관리[관리자전용]</a></div>
					</c:if>
					
				</div>
				<div class="wrap_rank">
					<h1 class="rank_title">내 정보</h1>
					<div class="rank_content">
						<div class="wrap_myrank">
						
						</div>
						<table class="ranking_table">
							<tr>
								<th>아이디</th>
								<th>닉네임</th>
								<th>전화번호</th>
								<th>회원등급</th>
								<th>가입일자</th>
								<th>이메일</th>
							</tr>
							<tr>
								<td>${sessionScope.user.mbId}</td>
								<td>${sessionScope.user.mbnick}</td>
								<td>${sessionScope.user.mbtel}</td>
								<td>${gradeName}</td>
								<td>${sessionScope.user.mbRegisterDate}</td>
								<td>${sessionScope.user.mbemail}</td>
							</tr>
						</table>
					</div>
					<div class="rank_content">
						<a class="link_mygroup" href="${context}/group/viewlist">내가 가입한 그룹 보기 >></a>
					</div>
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
	</body>
</html>