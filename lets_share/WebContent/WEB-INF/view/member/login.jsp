<!-- 02-01 김승현 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/login.css" />
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
				<div class="login">
					<h1 class="login_title">로그인</h1>
					<div class="loginbox">
						<form action="/member/loginimpl" method="post" class="loginform">
							<input type="text" name="id" placeholder="아이디">
							<input type="password" name="pw" id="pw" placeholder="비밀번호">
							<span class="valid_info"></span>
							<button onclick="login()">로그인</button>
							<a  href="javascript:kakaoLogin();">
							<img src="../resources/images/member/kakao_login_medium_narrow.png"/>
							</a>
							<script src="https://developers.kakao.com/sdk/js/kakao.js" type="text/javascript"></script>
							<script type="text/javascript">
							let login = () => {
								
								const url = '/member/loginimpl';
								
								let params = {};
								params.id = id.value;
								params.pw = pw.value;
								
								//post방식으로 진행
								//헤더 설정
								let headerObj = new Headers();
								//form태그의 기본 content 타입인 application/x-www-form-urlencoded 로
								//content-type을 맞춰야 서버에서 편하게 getParameter로 사용 할 수 있다.
								//name=value&name=value
								headerObj.append("content-type","application/x-www-form-urlencoded");
								
								fetch(url,{
									method:"post",
									headers:headerObj,
									body:"data=" + JSON.stringify(params)
								})
								.then(response => {
									//200번대 응답코드라면
									if(response.ok){
										return response.text();
									}else{
										throw new AsyncResponseError(response.text());
									}
								})
								.then(text => {
									if(text == 'fail'){
										document.querySelector('.valid_info').innerHTML = '아이디나 비밀번호를 확인하세요.';
									}else if(text == 'success'){
										location.href = "/index";
									}
								}).catch((error)=>{
									error.alertMessage();
								})		
								
							}
							
							
							
// 02-04 카카오 로그인 정보 미완성							
Kakao.init("c00b90880a6e893af3a5479df4ddfbad");



function kakaoLogin() {
	Kakao.Auth.login({
		scope:'profile , account_email',
		success: function(authObj) {
			console.log(authObj);
			Kakao.API.request({
				url:'/v2/user/me',
				success: function(res){
					console.log(res);
					
					var userID = res.id;
					var userEmail = res.kakao_account.email;
					var userNickName = res.properties.nickname;
					
					console.log(userID);
					console.log(userEmail);
					console.log(userNickName);
					
					
					
					
					
				},
				fail: (err) => {
					console.error(err)	
				}
			});
		}
	
	});

}
</script>
						</form>
						<div class="optionbox">
							<label><span>아이디 저장</span><input type="checkbox"></label>
							<span></span>
							<span id="register_btn">회원가입</span>
						</div>
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