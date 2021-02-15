<!-- 02-01 김승현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/member/login.css" />
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
						<li><a href="${context}/notice/noticeList?p=1">공지 사항</a></li>
					</ul>
				</nav>
			</div>
			<!-- Main -->
			<div class="wrapper style1">
				<div class="contetnBox">
					<div class="titleBox">
						<a href="">Let's Share 로그인</a>
					</div>
					<form action="/member/loginimpl" method="post", class="loginform">
						<div class="formBox">
                            <div class="inputBox" id="loginInp">
                                <input type="text" name="id" id="id" placeholder="아이디">
                            </div>
                            <div class="inputBox" id="pwInp">
                                <input type="password" name="pw" id="pw" placeholder="비밀번호">
                            </div>
                            <div class="inputBox">
                                <button onclick="login()">로그인</button>
                            </div>
                            <div class="inputBox">
                                <a href="https://kauth.kakao.com/oauth/authorize?client_id=8a92e5d7d3324acd050fd30b648b921b&redirect_uri=http://localhost:9090/Kakao/oauth&response_type=code">
									<img src="../resources/images/member/kakao_login_medium_wide.png" alt="">
								</a>
                            </div>
						</div>
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
			<script src="https://developers.kakao.com/sdk/js/kakao.js" type="text/javascript"></script>
			<script type="text/javascript"></script>
	</body>
</html>