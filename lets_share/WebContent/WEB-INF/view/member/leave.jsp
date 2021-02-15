<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/leave.css"/>
		<link rel="stylesheet" href="/resources/css/main.css"/>
		<noscript><link rel="stylesheet" href="/resources/css/noscript.css" /></noscript>
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
								<li><a href="${context}/index">Home</a></li>
								<li><a href="${context}/group/form">구매자 모집</a></li>
								<li><a href="${context}/group/search">구매 참여</a></li>
								<li><a href="${context}/report/listAll">신고 게시판</a></li>
								<li><a href="${context}/notice/noticeList?p=1">공지 사항</a></lI>
							</ul>
						</nav>

				</div>

			<!-- Main -->
				<div class="wrapper style1">
					<div class="left_menu">
						<div><i class="fas fa-trophy"></i>  회원 랭킹</div>
						<div><i class="fas fa-edit"></i>  회원 정보 수정</div>
					</div>
					<h1 class="modify_title">회원 탈퇴</h1>
					<div class="pw_secure contents">
						<span>회원탈퇴를 하시면 서비스를 이용하실 수 없습니다.</span>
						<span>정말 탈퇴하시려면 비밀번호를 입력해주세요.</span>
						<form class="pw_secure_auth">
							<span>비밀번호</span>
							<input type="password">
							<button>탈퇴</button>
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

	</body>
</html>