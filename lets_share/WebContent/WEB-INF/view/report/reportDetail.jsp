<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<!-- 사용자 또는 관리자가 신고의 내용, 처리 상태등을 확인할 수 있는 페이지입니다 -->
<!-- 접근권한 : 작성자가 본인인 사용자 -->
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/report/reportDetail.css" />
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
					<div class="section">
						<div class="wrap_title"><h2>신고 내용</h2></div>	
						<!-- 내용 시작 -->
						<div class="content_wrap">
							<div class="content_header">
								<h3>신고 제목</h3>
								<span class="date">작성날짜 : 신고 날짜</span>
							</div>
							<div class="content_body">
								<pre id="text">
									신고의 내용입니다. id test34의 그룹에 가입했는데빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									입금을 한지 이틀이 지났는데도 입금확인을 안했습니다.
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
									빨리처리부탁드립니다@@@@@@@@@@@@@@@@@@@@@@@@@
								</pre>
							</div>
							<div class="reply_header">
								<h3>관리자 답변</h3>
								<span class="state">상태 : 처리중</span>
							</div>
							<div class="reply_body">
								<pre>
									접수 완료되었습니다. 관리자의 처리를 기다려주세요.관리자의 처리를 기다려주세요.관리자의 처리를 기다려주세요.관리자의 처리를 기다려주세요.
									관리자의 처리를 기다려주세요.
									관리자의 처리를 기다려주세요.
									관리자의 처리를 기다려주세요.
									관리자의 처리를 기다려주세요.
									관리자의 처리를 기다려주세요.
									관리자의 처리를 기다려주세요.
								</pre>
							</div>
							<div class="wrap_btn">
								<!-- 목록버튼 -->
								<button onclick="goList()">목록으로</button>
								<!-- 관리자일 경우에만 표시되는 답변 버튼 -->
								<button>답변하기</button>
							</div>
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