<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="/resources/css/group/group_search.css" />
		<noscript><link rel="stylesheet" href="/resources/css/noscript.css" /></noscript>
		<!-- 모달 -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
</head>
	<body class="no-sidebar is-preload">
		<div id="page-wrapper">
			<!-- Header -->
			<div id="header">
				<!-- Inner -->
				<div class="inner">
					<header>
						<h1><a href="/index" id="logo">Let's Share</a></h1>
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
				<div class="title"><a href="/group/search">마이 그룹 페이지</a></div>
				<div class="nodeBox">
					<c:forEach var="group" items="${groupList}">
                        <div class="node" onclick="goView(${group.getGroupId()})">
                            <div class="leftBox">
                                <div class="imgBox">
                                    <img src="../../../resources/images/group/${group.getMemberCnt()}.PNG" alt="">
                                </div>
                            </div>
                            <div class="rightBox">
                                <div class="line1">
                                    <div class="logoBox">
                                        <img src="../../../resources/images/group/${group.getServiceCode()}.PNG" alt="">
                                    </div>
                                    <div class="nameBox">
                                        <h5>
											<c:choose>
											<c:when test="${group.getServiceCode() eq 'SR01'}">
												넷플릭스 No.${group.getGroupId()}
											</c:when>
											<c:when test="${group.getServiceCode() eq 'SR02'}">
												왓챠No.${group.getGroupId()}
											</c:when>
											<c:when test="${group.getServiceCode() eq 'SR03'}">
												쿠팡플레이 No.${group.getGroupId()}
											</c:when>
											<c:when test="${group.getServiceCode() eq 'SR04'}">
												웨이브 No.${group.getGroupId()}
											</c:when>
											<c:when test="${group.getServiceCode() eq 'SR05'}">
												티빙 No.${group.getGroupId()}
											</c:when> 
											</c:choose>
										</h5>
                                    </div>
                                </div>
                                <div class="line2">
                                    <h1>현재 인원 : ${group.getMemberCnt()}/4 </h1>
                                </div>
<!--                                 <div class="line3">
                                    <button onclick="register(${group.getGroupId()})">가입</button>
                                </div> -->
                            </div>
                        </div>
                       </c:forEach>                       
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
				<!-- 모달 -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	
			<script>
			let goView = (groupId) =>{
				location.href="/group/view?groupId="+groupId;
			}
			</script>
	</body>
</html>