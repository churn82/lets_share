<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/join.css" />
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
					<div class="agreement">
						<h1 class="agreement_title">Let's Share에 오신 것을 환영합니다.</h1>
						<textarea class="agreement_content" readonly>
							같이 힘있다 이것이다 인류의 역사를 꾸며 내려온 동력은 바로 이것이다 이성은 투명하되 얼음과 같으며
							지혜는 날카로우나 갑 속에 든 칼이다 청춘의 끓는 피가 아니더면 인간이 얼마나 쓸쓸하랴? 얼음에 싸인 만
							물은 얼음이 있을 뿐이다 그들에게 생명을 불어 넣는 것은 따뜻한 봄바람이다 풀밭에 속잎나고 가지에 싹
							이 트고 꽃 피고 새 우는 봄날의 천지는 얼마나 기쁘며 아름다우냐? 이것을 얼음 속에서 불러 내는
							것이 따뜻한 봄바람이다 인생에 따뜻한 봄바람을 불어 보내는 것은 청춘의 끓는 피다 청춘의 피가 뜨거운
							지라 인간의 동산에는 사랑의 풀이 돋고 이상의 꽃이 피고 희망의 놀이 뜨고 열락의 새가
							불러 내는 것이 따뜻한 봄바람이다 인생에 따뜻한 봄바람을 불어 보내는 것은 청춘의 끓는 피다 청춘의 피
						</textarea>
						<label class="agreement_check"><span>약관에 동의합니다.</span><input type="checkbox" style="width:20px;height:20px;background-color:lightgray"></label>
						<button class="agreement_next">다음</button>
					</div>
					<div class="member_info">
						<h1 class="member_info_title">회원 정보 입력</h1>
						<form class="member_info_form">
							<div class="wrap_line3">
								<span>아이디</span>
								<input type="text" placeholder="아이디를 입력하세요">
								<button>중복확인</button>
							</div>
							<div class="wrap_line2">
								<span></span>
								<span class="confirm_msg" id="confirm_id">중복된 아이디입니다.</span>
							</div>
							<div class="wrap_line2">
								<span>비밀번호</span> 
								<input type="password" placeholder="비밀번호를 입력하세요">
							</div>
							<div class="wrap_line2">
								<span>비밀번호 확인</span>
								<input type="password" placeholder="비밀번호를 다시 입력하세요">
							</div>
							<div class="wrap_line2">
								<span></span>
								<span class="confirm_msg" id="confirm_pw">같은 비밀번호를 입력해주세요.</span>
							</div>
							<div class="wrap_line3">
								<span>닉네임</span>
								<input type="text" placeholder="닉네임을 입력하세요">
								<button>중복확인</button>
							</div>
							<div class="wrap_line2">
								<span></span>
								<span class="confirm_msg" id="confirm_nick">중복된 닉네임입니다.</span>
							</div>
							<div class="wrap_line2">
								<span>이메일</span>
								<input type="text" placeholder="이메일을 입력하세요">
							</div>
							<div class="wrap_register">
								<button id="register_btn">가입하기</button>
							</div>
						</form>
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