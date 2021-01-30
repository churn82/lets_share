<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/rank.css" />
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
					<div class="left_menu">
						<div><i class="fas fa-trophy"></i>  회원 랭킹</div>
						<div><i class="fas fa-edit"></i>  회원 정보 수정</div>
					</div>
					<div class="wrap_rank">
						<h1 class="rank_title">회원 순위</h1>
						<div class="rank_content">
							<div class="wrap_myrank">
								<div class="myrank_name">
									<span>내 등급</span>
									<span>내 점수</span>
									<span>내 순위</span>
								</div>
								<div class="myrank_value">
									<span>[내 등급]아이콘</span>
									<span>123123(내점수)</span>
									<span>12312(내순위)</span>
								</div>
							</div>
							<table class="ranking_table">
								<tr><th>순위</th><th>아이디</th><th>등급</th><th>누적점수</th></tr>
								<tr><td>1</td><td>1</td><td>1</td><td>1</td></tr>
								<tr><td>2</td><td>2</td><td>2</td><td>2</td></tr>
								<tr><td>3</td><td>3</td><td>3</td><td>3</td></tr>
								<tr><td>4</td><td>4</td><td>4</td><td>4</td></tr>
								<tr><td>5</td><td>5</td><td>5</td><td>5</td></tr>
							</table>
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