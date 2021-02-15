<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<!-- 사용자가 자신의 신고내역을 목록으로 확인할 수 있는 페이지입니다. -->
<!-- 접근권한 : 사용자 -->
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/adminMember.css" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
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
					<div class="wrap_title"><h2>회원 관리</h2></div>
					
			
					<div class="tab_content">
						<div id="search_bar">
							<form method="GET" action="${context}/member/adminMember" class="searchForm">
								<input type="text" class="id" name="id" placeholder="검색할 아이디를 입력하세요.">
								<button class="search_btn" type="submit">조회</button>
							</form>
						</div>
					</div>
					
					<div class="table_wrap">
						<table class="tb_list">
							<thead>
								<tr>
									<th>이름</th>
									<th>ID</th>
									<th>E-mail</th>
									<th>휴대폰</th>
									<th>이용 상태</th>
									<th>탈퇴</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="member" items="${memberList}">
									<tr class="table_head">
										<td class="reportNo">
											${member.mbName}
										</td>
										<td class="title">
											${member.mbId}
			                     		</td>
										<td class="writer">
											${member.mbemail}
										</td>
										<td class="date">
											${member.mbtel}
										</td>
										<td class="state">
											<c:choose>
												<c:when test="${member.mbLeaveDate==null}">
													이용중
												</c:when>
												<c:otherwise>
													탈퇴 회원
												</c:otherwise>
											</c:choose>
										</td>
										<td class="state">
											<c:choose>
												<c:when test="${member.mbLeaveDate==null}">
													<button onclick="stopMember('${member.mbId}')">이용 중지</button>
												</c:when>
												<c:otherwise>
													
												</c:otherwise>
											</c:choose>
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
								<a class="page_btn" href="/member/adminMember?page=${firstPage-1}" style="background-color: white; color:black">&lt;</a>
								<c:forEach var="page" items="${pageList}">
								<c:choose>
									<c:when test = "${page==currentPage}">
										<a class="page_btn" href="/member/adminMember?page=${page}" style="background-color: #FF9900">
											${page}
										</a>
									</c:when>
									<c:otherwise>
										<a class="page_btn" href="/member/adminMember?page=${page}">
											${page}
										</a>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								<a class="page_btn" href="/member/adminMember?page=${lastPage+1}" style="background-color: white; color:black">&gt;</a>
							</div>		
						</c:when>
						<c:otherwise>
							<div class="paging">
								<a class="page_btn" href="/member/adminMember?select=${select}&searchText=${searchText}&page=${firstPage-1}" style="background-color: white; color:black">&lt;</a>
								<c:forEach var="page" items="${pageList}">
								<c:choose>
									<c:when test = "${page==currentPage}">
										<a class="page_btn" href="/member/adminMember?select=${select}&searchText=${searchText}&page=${page}" style="background-color: #FF9900">
											${page}
										</a>
									</c:when>
									<c:otherwise>
										<a class="page_btn" href="/member/adminMember?select=${select}&searchText=${searchText}&page=${page}">
											${page}
										</a>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								<a class="page_btn" href="/member/adminMember?select=${select}&searchText=${searchText}&page=${lastPage+1}" style="background-color: white; color:black">&gt;</a>
							</div>
						</c:otherwise>
					</c:choose>
				<!-- 글쓰기 버튼 -->
				<div class="write_btn">
					<button id="write" onclick="report()">신고하기</button>				
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
			let report = () => {
				location.href="/report/form";
			}
			
			let stopMember = (memberId) => {
				location.href="/member/stopMember?memberId="+memberId;
			}
		</script>
	</body>
</html>