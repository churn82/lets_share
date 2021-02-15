<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/main.css" type="text/css"/>
	<noscript><link rel="stylesheet" href="assets/css/noscript.css" type="text/css"/></noscript>
	<!-- slick 라이브러리  -->
	<link rel="stylesheet" href="/resources/slick/slick.css" type="text/css" />	
	<link rel="stylesheet" href="/resources/slick/slick-theme.css" type="text/css" />
	<!-- 모달 -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css"  type="text/css"/>
	<!-- groupForm CSS  -->
	<link rel="stylesheet" href="/resources/css/group/form.css" type="text/css"/>
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
			<form action="/group/receive" method="POST" id="form_group">	
				<div class="wrapper style1">
					<div class="group_reg">
						<div class="btn_box" id="prev"><i class="fas fa-angle-left prev"></i></div>
							<div class="slider">
								<div class="slide">
									<div class="title"><a>Step1 .공유할 계정의 서비스를 선택 하세요</a></div>
									<div class="node_box">
										<div class="node" id="SR01">
											<div class="service_logo"><img src="../../../resources/images/group/netflix.png" alt=""></div>
										</div>
										<div class="node" id="SR02">
											<div class="service_logo"><img src="../../../resources/images/group/watcha.png" alt=""></div>
										</div>
										<div class="node" id="SR03">
											<div class="service_logo"><img src="../../../resources/images/group/coupang.png" alt=""></div>
										</div>
									</div>
									<br>
									<div class="node_box">
										<div class="node" id="SR04">
											<div class="service_logo"><img src="../../../resources/images/group/wavve.png" alt=""></div>
										</div>
										<div class="node" id="SR05">
											<div class="service_logo"><img src="../../../resources/images/group/tving.png" alt=""></div>
										</div>
									</div>
								</div>
								<div class="slide">
									<div class="title"><a>Step2 .계좌 정보 및 원하는 그룹원 수를 선택하세요</a></div>
									<div class="bank_box">
										<div class="bank_icon"><a href="#ex1" rel="modal:open" id="bank_btn">은행 선택</a></div>
										<input type="text" class="bank_account" id="bank_account" name="bank_account" 
										placeholder=" 예금주명 and 계좌 '-' 빼고 입력해 주세요" style="width: 70%;">
									</div>
									<div class="bank_box">
										<div class="bank_icon">그룹원 수</div>
										<select id="groupMemberCnt" name="groupMemberCnt">
											<option value="4">4▼</option>
											<option value="3">3</option>
											<option value="2">2</option>
											<option value="1">1</option>
										</select>
									</div>
									<div class="warningBox">
										<div> *계좌 정보는 정확히 입력해주세요</div>
										<div> *원하시는 그룹원 수는 본인 포함 최대 4명까지 등록 가능합니다.</div>
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
										<input type="text" id="bank_real" name="bank_real">
										<div class="submit_box"><input type="submit" value="확인"></div>
								</div>
							</div>
						<div class="btn_box" id="next"><i class="fas fa-angle-right next"></i></div>
					</div>
				</div>
				<div id="ex1" class="modal">
					<h1>입금 은행을 선택 하세요</h1>
					<div class="content">
						<div class="bank" id="농협 은행">농협 은행</div>
						<div class="bank" id="국민 은행">국민 은행</div>
						<div class="bank" id="신한 은행">신한 은행</div>
						<div class="bank" id="우리 은행">우리 은행</div>
						<div class="bank" id="KEB 하나 은행">KEB 하나 은행</div>
						<div class="bank" id="기업 은행">기업 은행</div>
						<div class="bank" id="대구 은행">대구 은행</div>
						<div class="bank" id="부산 은행">부산 은행</div>
						<div class="bank" id="우체국">우체국</div>
						<div class="bank" id="경남 은행">경남 은행</div>
						<div class="bank" id="카카오 뱅크">카카오 뱅크</div>
						<div class="bank" id="SC 제일 은행">SC 제일 은행</div>
						<div class="bank" id="수협 은행">수협 은행</div>
						<div class="bank" id="씨티 은행">씨티 은행</div>
						<input type="text" placeholder="직접 입력" id="bank" name="bank">
						<button id="confirm_btn"><a href="#" rel="modal:close">확인</a></button>
					</div>
					<!-- <a href="#" rel="modal:close">Close</a> -->
				</div>
				
			</form>
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
			
			// Step2에 은행 선택하면 input에 은행들어감
			document.querySelectorAll(".bank").forEach((node)=>{
				node.addEventListener("click", (e)=>{
					document.querySelector("#bank").value = e.target.id;
					document.querySelector("#bank_real").value = e.target.id;
				})
			})
			document.querySelector("#confirm_btn").addEventListener("click",(e)=>{
				document.querySelector("#bank_btn").innerHTML = document.querySelector("#bank").value;
				
			})
		</script>
	</body>
</html>