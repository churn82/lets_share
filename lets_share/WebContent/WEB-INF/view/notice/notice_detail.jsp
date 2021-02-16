<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="../../../resources/css/notice/notice_detail.css" />
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
<!-- <div class="wrapper style1"> -->
<div id="main">
	<div class="section">
		<h2>공지사항</h2>
			
			<!-- tab_menu 시작 -->
			<ul class="tab_menu">
				<li class="n_menu">
					<a href="/notice/noticeList">공지사항</a>
				</li>
				<li class="e_menu">
					<a href="/notice/eventList">이벤트</a>
				</li>
			</ul>
			<!-- tab_menu 끝 -->
			
			<!-- 내용 시작 -->
			<div class="content_wrap">
				<div class="content">
					<div class="content_header">
						<h3>제목 : ${requestScope.noticeTitle}</h3>
						<span class="date">작성날짜 : ${requestScope.noticeDate}</span>
					</div>
					<div class="content_body">
						<pre id="text">
							<c:out value="${requestScope.noticeContent}"/>
						</pre>
					</div>
					
					<!-- 목록버튼 -->
					<div class="list_btn">
						<button onclick="goList()">목록</button>
					</div>
					
					<!-- 수정 버튼 -->
            		<c:if test="${sessionScope.user.mbId != null && sessionScope.user.mbId eq 'admin'}">
						<div class="update_btn">
							<span><a href="/notice/beforeUpdate?noticeNo=${requestScope.noticeNo}">수정</a></span>
							<span><a onclick="return confirm('삭제하시겠습니까?')" href="/notice/deleteNotice?noticeNo=${requestScope.noticeNo}">삭제</a></span>
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
			
		<script type="text/javascript">
			function goList(){
				location.href="/notice/noticeList";
				
			}
		
		
		</script>

	</body>
</html>