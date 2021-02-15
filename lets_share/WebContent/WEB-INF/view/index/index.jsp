<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../../../resources/css/index_jiyoung.css" />
		<noscript><link rel="stylesheet" href="../../../resources/css/noscript.css" /></noscript>
	</head>
	<body class="hompage is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<div id="header">

					<!-- Inner -->
						<div class="inner">
							<header>
								<h1><a href="${context}/index" id="logo">Let's Share</a></h1>
								<hr />
								<p>and pay less</p>
							</header>
							<footer>
								<c:choose>
									<c:when test="${empty sessionScope.user}">
									<a href="${context}/member/join" class="button circled scrolly"><span>Register</span></a>
									<a href="${context}/member/login" class="button circled scrolly"><span>login</span></a>
									</c:when>
									<c:otherwise>
									<a href="${context}/member/mypage" class="button circled scrolly"><span>mypage</span></a>
									<a href="${context}/member/logout" class="button circled scrolly"><span>logout</span></a>
									</c:otherwise>
								</c:choose>
								
							</footer>
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
			
			<!-- Banner -->
				<section id="banner">
					<header>
						<h2>Hi. We're looking for <strong>You</strong>.</h2>
						<p>
							넷플릭스, 왓챠, 웨이브, 티빙 등의 사용자가 원하는 OTT서비스 계정을 공유하여 최대 월 요금 1/4까지<br>
							저렴하게 사용하길 원하는 이용자들을 찾고있습니다.<br>
						</p>
					</header>
				</section>
			
			<!-- Carousel -->
				<section class="carousel">
					<div class="reel">

						<article>
							<a href="#" class="image featured"><img src="../../../resources/images/netflix.png" alt="" /></a>
							<header>
								<h3><a href="https://www.netflix.com/kr/">Netflix</a></h3>
							</header>
							<p>4K+HDR을 누릴 수 있는 금액<br>
							14,500원 -> 3,625원</p>
						</article>

						<article>
							<a href="https://watcha.com/" class="image featured"><img src="../../../resources/images/watcha.png" alt="" /></a>
							<header>
								<h3><a href="#">Watcha</a></h3>
							</header>
							<p>최상위 화질을 누릴 수 있는 금액<br>
							13,900원 -> 3,475원</p>
						</article>

						<article>
							<a href="https://www.coupang.com/" class="image featured"><img src="../../../resources/images/coupangplay.png" alt="" /></a>
							<header>
								<h3><a href="#">Coupang Play</a></h3>
							</header>
							<p>드라마 영화 다큐 + 로켓와우 회원권<br>
							2,900원 -> 600원</p>
						</article>

						<article>
							<a href="https://www.wavve.com/" class="image featured"><img src="../../../resources/images/wavve.png" alt="" /></a>
							<header>
								<h3><a href="#">Wavve</a></h3>
							</header>
							<p>최상위 화질을 누릴 수 있는 가격
							13,900원 -> 3,475원</p>
						</article>

						<article>
							<a href="https://www.tving.com/main.do" class="image featured"><img src="../../../resources/images/tiving.png" alt="" /></a>
							<header>
								<h3><a href="#">Tiving</a></h3>
							</header>
							<p>1080pFHD+4K 화질<br>
							13,900원 ->3,475원
							</p>
						</article>

					</div>
				</section>
				
				
			<!-- Main -->
				<div class="wrapper style2">

					<article id="main guideline" class="container special">
						<a href="#" class="image featured"><img src="images/pic06.jpg" alt="" /></a>
						<header>
							<h2>Let's share 이용안내</h2><br><br>
							<p>
								Step 1 register 버튼을 클릭하여 회원가입을 한다.<br><br>
								Step 2 구매 참여 버튼을 클릭하여 원하는 서비스를 공유하는 모임을 선택한다.<br><br>
								Step 3 표시된 계좌번호에 해당 금액을 입금한 후 입금 완료 버튼을 누른다.<br><br>
								Step 4 모임장이 입금을 확인하면 사용자의 email로 공유계정의 ID와 password가 전송된다.
								
							</p>
						</header>
						<footer>
							<a href="#" class="button">Register</a>
						</footer>
					</article>

				</div>

			<!-- Footer -->
				<div id="footer">
					<div style="text-align: center;">Copyright © 1998-2021 KH Information Educational Institute All Right Reserved</div>
				</div>

		</div>

		<!-- Scripts -->
			<script src="../../../resources/js/jquery.min.js"></script>
			<script src="../../../resources/js/jquery.dropotron.min.js"></script>
			<script src="../../../resources/js/jquery.scrolly.min.js"></script>
			<script src="../../../resources/js/jquery.scrollex.min.js"></script>
			<script src="../../../resources/js/browser.min.js"></script>
			<script src="../../../resources/js/breakpoints.min.js"></script>
			<script src="../../../resources/js/util.js"></script>
			<script src="../../../resources/js/main.js"></script>

	</body>
</html>