<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="../../../resources/css/notice/event_detail.css" />
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
<!-- <div class="wrapper style1"> -->
<div id="main">
	<div class="section">
		<h2>이벤트</h2>
			
			<!-- tab_menu 시작 -->
			<ul class="tab_menu">
				<li class="n_menu">
					<a href="">공지사항</a>
				</li>
				<li class="e_menu">
					<a href="">이벤트</a>
				</li>
			</ul>
			<!-- tab_menu 끝 -->
			
			<!-- 내용 시작 -->
			<div class="content_wrap">
				<div class="content">
					<div class="content_header">
						<h3>제목: ${requestScope.noticeTitle}</h3>
						<span class="date">작성날짜: ${requestScope.noticeDate}</span>
					</div>
					<div class="content_body">
						<pre id="text">
							<c:out value="${requestScope.noticeContent}"/>
						</pre>
					</div>
					
				<%-- 
					<div class="prevNext">
						<div class="prev_next">
							<span class="next_sel">이전글</span>
							<h4 onclick="Nextmove">공지사항입니다.</h4>
							<span class="date_bottom">2021-01-31</span>
						</div>
						
						<div class="prev_next">
							<span class="next_sel">다음글</span>
							<h4 onclick="Nextmove">등급 관련 안내</h4>
							<span class="date_bottom">2021-01-31</span>
						</div>
					</div>
				--%>
					<!-- 목록버튼 -->
					<div class="list_btn">
						<button onclick="goList()">목록</button>
					</div>
					
					<!-- 수정 버튼 -->
                  <c:if test="${sessionScope.user.mbId != null && sessionScope.user.mbId eq 'admin'}">
                  <div class="update_btn">
                           <button><a href="/notice/update?noticeNo=${requestScope.noticeNo}">수정</button>
                           <input type="submit"><a href="">삭제</a></input>
                        </div>   
               	</c:if>
					
					  
				
				</div>
		
		</div>			
	</div>
</div>
		
<!-- </div> -->

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