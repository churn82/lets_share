<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<!-- 사용자가 자신의 신고내역을 목록으로 확인할 수 있는 페이지입니다. -->
<!-- 접근권한 : 사용자 -->
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/report/reportView.css" />
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
						<div class="wrap_title"><h2>내 신고내역</h2></div>
						<ul class="tab_menu">
							<li class="n_menu">
								<a href="/report/reportList?list=all">전체 신고내역</a>
							</li>
							<li class="e_menu">
								<a href="/report/reportList?list=replied">처리된 신고내역</a>
							</li>
						</ul>
				
						<div class="tab_content">
							<div id="search_bar">
								<select id="search_sel" name="select">
									<option value="sel_all">전체  ▼ </option>
									<option value="sel_title">제목</option>
									<option value="sel_content">내용</option>
								</select>
								<input type="text" class="searchText" name="searchText" placeholder="검색어를 입력하세요.">
								<button class="search_btn" onclick="searchList">조회</button>
							</div>
						</div>
						
						<div class="table_wrap">
							<table class="tb_list">
								<thead>
									<tr>
										<th>번호</th>
										<th>제목</th>
										<th>작성자</th>
										<th>작성날짜</th>
										<th>처리상태</th>
									</tr>
								</thead>
								<tbody>
								<!-- <c:forEach var="reportList" items="${reportList}"> -->
										<tr class="table_head">
											<td class="reportNo">
												신고 번호
											</td>
											<td class="title">
				                      		  <a href="/report/reportDetail?reportNo=${reportList.reportNo}">
				                        	  	신고 제목
				                        		</a>
				                     		</td>
											<td class="writer">
												작성자
											</td>
											<td class="date">
												신고 게시일
											</td>
											<td class="state">
												처리상태
											</td>
										</tr>
								<!-- </c:forEach> -->
								</tbody>				
							</table>
						</div>
						<!-- 게시판 페이지 번호 -->
						<div class="paging">
							<a class="page_btn" href="">1</a>
							<a class="page_btn" href="">2</a>
							<a class="page_btn" href="">3</a>
							<a class="page_btn" href="">4</a>
						</div>
					
					<!-- 글쓰기 버튼 -->
					<div class="write_btn">
						<button id="write" onclick="changeWriter()">신고하기</button>				
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