<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/emailauth.css" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<noscript><link rel="stylesheet" href="/resources/css/noscript.css" /></noscript>
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
								<li><a href="${context}/notice/noticeList?p=1">공지 사항</a></lI>
							</ul>
						</nav>

				</div>

			<!-- Main -->
			<!-- 가입시 인증, 비밀번호 찾기 공통으로 사용//상황에 따라 다른 텍스트 -->
				<div class="wrapper style1">
					<div class="emailauth1">
						<h1 class="emailauth_title">인증 이메일이 발송되었습니다.</h1>
						<div class="emailauth_content">60분 이내에 발신된 메일의 링크를 클릭하세요.</div>
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