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
	<c:if test='${back}'>
		<script>
			alert("신고 내용은 작성자와 관리자만 확인가능합니다.");
			window.history.back();
		</script>
	</c:if>
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
						<div class="wrap_title"><h2>${report.title}</h2></div>	
						<!-- 내용 시작 -->
						<div class="content_wrap">
							<div class="content_header">
								<h3>신고 그룹 : ${report.groupId}</h3>
								<span class="date">작성날짜 : ${report.regdate}</span>
							</div>
							<div class="content_body">
								<pre id="text">
									${report.content}
								</pre>
							</div>
							<div class="reply_header">
								<h3>관리자 답변</h3>
								<span class="state">상태 : 
									<c:if test='${report.clear==1}'>
										처리 완료
									</c:if>
									<c:if test='${report.clear==0}'>
										처리 중
									</c:if>
								</span>
							</div>
							<div class="reply_body">
								<pre>
									${report.reply}
								</pre>
							</div>
							
							<div class="wrap_btn">
								<!-- 목록버튼 -->
								<button onclick="goback()">목록으로</button>
							</div>
							<c:if test='${sessionScope.user.mblevel == "MB10"}'>
							<div>
								<form action="/report/reply" method="POST" >
									<textarea rows="" cols="" id="reply" name="reply"></textarea>
									<input type="text" value="${report.reportIdx}" style="display:none" name="reportIdx" id="reportIdx">
									<div class="wrap_btn">
										<select id="clear_sel" name="select">
											<option value="0">처리 중 ▼</option>
											<option value="1">처리 완료</option>
										</select>
										<button type="submit">답변</button>
									</div>
								</form>
							</div>
							</c:if>
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
		<script type="text/javascript">
			let goback = () => {
				window.history.back();
			}
		</script>
	</body>
</html>