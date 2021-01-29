<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/main.css" />
	<noscript><link rel="stylesheet" href="assets/css/noscript.css" /></noscript>
	<!-- slick 라이브러리  -->
	<link rel="stylesheet" href="/resources/slick/slick.css">	
	<link rel="stylesheet" href="/resources/slick/slick-theme.css">
	<!-- 모달 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
	<!-- groupForm CSS  -->
	<link rel="stylesheet" href="/resources/css/group/form.css">
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
			<form action="">
				<div class="wrapper style1">
					<div class="group_reg">
						
						<div class="btn_box" id="prev"><i class="fas fa-angle-left prev"></i></div>
							<div class="slider">
								<div class="slide">
									<div class="title"><a>Step1 .공유할 계정의 서비스를 선택 하세요</a></div>
									<div class="node_box">
										<div class="node" id="netflix"></div>
										<div class="node" id="watcha"></div>
									</div>
									<br>
									<div class="node_box">
										<div class="node" id="node3"></div>
										<div class="node" id="node4"></div>
									</div>
								</div>
								<div class="slide">
									<div class="title"><a>Step2 .계좌 정보 및 결제일을 선택 하세요</a></div>
									<div class="bank_box">
										<div class="bank_icon"><a href="#ex1" rel="modal:open">은행 선택</a></div>
										<input type="text" class="bank_account" id="bank_account" placeholder=" '-' 빼고 입력해 주세요" style="width: 70%;">
									</div>
									<div class="calendar">
										<div class="line">
											<div class="date" id="1">1</div><div class="date" id="2">2</div><div class="date" id="3">3</div><div class="date" id="4">4</div>
											<div class="date" id="5">5</div><div class="date" id="6">6</div><div class="date" id="7">7</div>
										</div>
										<div class="line">
											<div class="date" id="8">8</div><div class="date" id="9">9</div><div class="date" id="10">10</div><div class="date" id="11">11</div>
											<div class="date" id="12">12</div><div class="date" id="13">13</div><div class="date" id="14">14</div>
										</div>
										<div class="line">
											<div class="date" id="15">15</div><div class="date" id="16">16</div><div class="date" id="17">17</div><div class="date" id="18">18</div>
											<div class="date" id="19">19</div><div class="date" id="20">20</div><div class="date" id="21">21</div>
										</div>
										<div class="line">
											<div class="date" id="22">22</div><div class="date" id="23">23</div><div class="date" id="24">24</div><div class="date" id="25">25</div>
											<div class="date" id="26">26</div><div class="date" id="27">27</div><div class="date" id="28">28</div>
										</div>
										<div class="line">
											<div class="date" id="29">29</div><div class="date" id="30">30</div><div class="date" id="31">31</div>
										</div>
									</div>
								</div>
								<div class="slide">
										<div class="title"><a>Step3 .서비스 아이디 및 패스워드를 입력 하세요</a></div>
										<div class="idpw_box">
											<div class="id_box"><input type="text" class="service_id" id="service_id" name="service_id" placeholder="Service ID"></div>
											<div class="pw_box"><input type="password" class="service_pw" id="service_pw" name="service_pw" placeholder="Service PASSWORD"></div>
											<div class="safe">안전한 비밀번호로 변경 후 등록해 주세요</div>
										</div>
										<input type="text" id="service" name="service">
										<input type="text" id="date" name="date">
										<div class="submit_box"><input type="submit" value="확인"></div>
								</div>
							</div>
						<div class="btn_box" id="next"><i class="fas fa-angle-right next"></i></div>
					</div>
				</div>
			</form>
			<!-- Footer -->
			<div id="footer">
				<div style="text-align: center;">Copyright © 1998-2021 KH Information Educational Institute All Right Reserved</div>
			</div>
			<div id="ex1" class="modal">
				<p>입금 은행을 선택하세요</p>
				<!-- <a href="#" rel="modal:close">Close</a> -->
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
		
		<!-- slick 라이브러리 -->
		<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
		<script src="https://code.jquery.com/jquery-migrate-3.3.2.min.js" integrity="sha256-Ap4KLoCf1rXb52q+i3p0k2vjBsmownyBTE1EqlRiMwA=" crossorigin="anonymous"></script>
		<script type="text/javascript" src="../../../resources/slick/slick.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('.slider').slick({
					dots: false,
					infinite : false,
					arrows : true,
					focusOnSelect : false,
					prevArrow: $('.prev'),
					nextArrow: $('.next'),
					nvariableWidth : true
				});
			});
		</script>

		<!-- 모달 -->
		<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>

		<!-- JS -->
		<script>
			// Step1에 넷플릭스, 왓챠등 원하는 서비스 선택하면, 보이지 않는 input에 서비스를 찍음
			document.querySelectorAll(".node").forEach((node=>{
				node.addEventListener("click", (e)=>{
					document.querySelectorAll(".node").forEach(all=>{
						all.style = "border : 2px solid #beb9b9;"
					})
					e.target.style = "border : 2px solid #ef8376;"
					document.querySelector("#service").value = e.target.id;
				})
			}))

			// Step2에 Calendar 날짜 선택하면, 보이지 않는 input에 날짜를 찍음
			document.querySelectorAll(".date").forEach((node=>{
				node.addEventListener("click", (e)=>{
					document.querySelectorAll(".date").forEach(all=>{
						all.style = "background-color : white;"
					})
					e.target.style = "background-color : #ef8376;"
					document.querySelector("#date").value = e.target.id;
				})
			}))
		</script>
	</body>
</html>