<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<!-- 사용자가 신고를 작성할 수 있는 페이지입니다. -->
<!-- 접근권한 : 사용자 -->
<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/report/reportWrite.css" />
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
					<h2>신고 작성</h2>
					<div class="content_wrap">
						<form id="insertForm" action="/report/write" method="POST">
			        		<div class="form form_title">
			        			<div class="title_wrap">
									<div class="form_space">제목</div>
								</div>
								<input type="text" id="title" name="title" placeholder="제목을 작성해주세요."/>
			          		</div>
							<div class="form form_content">
								<div class="form_space">신고 그룹</div>
								<select id="groupId" name="groupId">
									<c:forEach var="groupId" items="${groupIdList}">
										<option value="${groupId}">${groupId}</option>
									</c:forEach>
								</select>
					            <div class="form_space">내용</div>
					            <textarea id="contents" name="contents" rows="10" placeholder="신고 내용을 적어주세요." maxlength="1000"></textarea>
							</div>
						<!-- submit 버튼 -->
						<div class="submits">
							<button type="submit" class="write_btn">등록</button>
						</div>
						</form>
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