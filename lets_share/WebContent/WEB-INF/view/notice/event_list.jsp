<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.notice.controller.NoticeController" %>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="../../../resources/css/notice/event_list.css"/>
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
<!-- <div class="wrapper style1"> -->
	<div id="main">
		<div class="section">
		<h2>이벤트</h2>
			
		<ul class="tab_menu">
			<li class="n_menu">
				<a href="/notice/noticeList">공지사항</a>
			</li>
			<li class="e_menu">
				<a href="/notice/eventList">이벤트</a>
			</li>
		</ul>
		
		<div class="tab_content">
			<form id="search_bar">
				<select id="search_sel" name="select">
					<option value="NOTICE_TITLE">제목</option>
					<option value="NOTICE_CONTENT">내용</option>
				</select>			
				<input type="text" class="searchText" name="searchText" placeholder="검색어를 입력하세요.">
				<button class="search_btn" onclick="searchList">조회</button>		
			</form>
	
			<div class="table_wrap">
				<table class="tb_list">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성날짜</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
					
					<c:forEach var="noticeList" items="${noticeList}">
					
						<tr>
							<td class="num">
								${noticeList.noticeNo}
							</td>
							<td class="title" >
								<a href="/notice/eventDetail?noticeNo=${noticeList.noticeNo}">
                        	  	${noticeList.noticeTitle}
                        		</a>
							</td>
							<td class="writer">
								관리자
							</td>
							<td class="date">
								${noticeList.noticeDate}
							</td>
							<td class="hit">
								${noticeList.noticeView}
							</td>
						</tr>
					</c:forEach>
						
					
					</tbody>				
				</table>
			
			</div>	
				<!-- 게시판 페이지 번호 -->
					<c:choose>
						<c:when test='${select==""}'>
							<div class="paging">
								<a class="page_btn" href="/notice/eventList?page=${firstPage-1}" style="background-color: white; color:black">&lt;</a>
								<c:forEach var="page" items="${pageList}">
								<c:choose>
									<c:when test = "${page==currentPage}">
										<a class="page_btn" href="/notice/eventList?page=${page}" style="background-color: #FF9900">
											${page}
										</a>
									</c:when>
									<c:otherwise>
										<a class="page_btn" href="/notice/eventList?page=${page}">
											${page}
										</a>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								<a class="page_btn" href="/notice/eventList?page=${lastPage+1}" style="background-color: white; color:black">&gt;</a>
							</div>		
						</c:when>
						<c:otherwise>
							<div class="paging">
								<a class="page_btn" href="/notice/eventList?select=${select}&searchText=${searchText}&page=${firstPage-1}" style="background-color: white; color:black">&lt;</a>
								<c:forEach var="page" items="${pageList}">
								<c:choose>
									<c:when test = "${page==currentPage}">
										<a class="page_btn" href="/notice/eventList?select=${select}&searchText=${searchText}&page=${page}" style="background-color: #FF9900">
											${page}
										</a>
									</c:when>
									<c:otherwise>
										<a class="page_btn" href="/notice/eventList?select=${select}&searchText=${searchText}&page=${page}">
											${page}
										</a>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								<a class="page_btn" href="/notice/eventList?select=${select}&searchText=${searchText}&page=${lastPage+1}" style="background-color: white; color:black">&gt;</a>
							</div>
						</c:otherwise>
					</c:choose>
				
				
				
				
				<!-- 글쓰기 버튼 -->
				<c:if test="${sessionScope.user.mbId != null && sessionScope.user.mbId eq 'admin'}">
					<div class="write_btn">
						<button id="write" onclick="changeWriter()">글쓰기</button>				
					</div>	
				</c:if>
					
					<script type="text/javascript">
						function changeWriter(){
							location.href="eventWriter";
						}
					</script>
					
					
										
			
	
				
				
			</div>
		</div>
		
	</div>
<!-- </div> -->

<!-- Footer -->
	<div id="footer">
		<div style="text-align: center;">
			Copyright © 1998-2021 KH Information Educational Institute All Right Reserved
		</div>
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
	
	<!-- Javascript  -->
	
	
	
	
	
	
	
	
	
	
	

	</body>
</html>