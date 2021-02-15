<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<!-- 관리자가 사용자의 신고에 답변을 작성하는 페이지입니다. -->
<!-- 접근권한 : 관리자 -->
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/report/managerWrite.css" />
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
					<div class="section">
						<h2>신고 답변</h2>
						<div class="content_wrap">
							<div class="content_header">
								<h3>신고 제목</h3>
								<span class="date">작성날짜 : 신고 날짜</span>
								<div class="content_writer">
									<span>작성자 : 작성자 이름(아이디)</span>
								</div>
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
							<form id="insert_form">
				        		<div class="form form_title">
				        			<div class="title_wrap">
										<div class="form_space">관리자 답변</div>
									</div>
				          		</div>
								<div class="form form_content">
						            <textarea name="contents" rows="10" placeholder="답변 내용을 입력해주세요." maxlength="1000"></textarea>
								</div>
					    	</form>
							<!-- submit 버튼 -->
							<div class="submits">
								<button type="submit" class="write_btn">등록</button>
							</div>
							<!-- 목록버튼 -->
							<div class="list_btn">
								<button>목록</button>
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