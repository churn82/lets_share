<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<%@page import="com.kh.member.model.dao.MemberDao"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>



<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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


	

		<!-- 정수를 저장하는 Arraylist를 생성하고 데이터 추가 -->

	<%

	ArrayList<Member> list
	= (ArrayList<Member>)session.getAttribute("list");

%>

<h2>회원 리스트</h2>
		<table>
	<tr>
		<td>아이디</td>
		<td>닉네임</td>
		<td>이메일</td>	
		<td>전화번호</td>
	</tr>
	<%
		if(list != null){
			for(int i=0; i<list.size(); i++){
	%>
	<tr>
		<td><%=list.get(i).getMbId() %></td>
		<td><%=list.get(i).getMbnick() %></td>
		<td><%=list.get(i).getMbemail() %></td>
		<td><%=list.get(i).getMbtel() %></td>
		
	</tr>	
<%
	}
}else{
	 %>
	<tr><td colspan="5"> 가입된 회원 없음. </td></tr>
<%} %>		


	</table>

	
					

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