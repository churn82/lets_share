
<!-- 김승현 02-01 join수정 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>

<head>
<c:set var="context" value="${pageContext.request.contextPath}"/>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/join.css" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="/resources/css/member/KakaoJoin.css" />
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
				<div class="contetnBox">
					<div class="titleBox">
						<a href="">KaKao 회원가입</a>
					</div>
					<div class="formBox">
						<form action="">
							<div class="line1">
								<div class="label"><div>ID : </div></div>
								<div class="input"><input type="text" name="" id="" style="padding: 10px;  font-size: 0.8em;" value="${kakaoId}" readonly></div>
							</div>
							<div class="line1">
								<div class="label"><div>E-mail : </div></div>
								<div class="input"><input type="text" name="" id="" style="padding: 10px;  font-size: 0.8em;" value="${email}" readonly></div>
							</div>
							<div class="line1">
								<div class="label"><div>이름 : </div></div>
								<div class="input"><input type="text" name="" id="" style="padding: 10px; font-size: 0.8em;" placeholder="본인의 이름을 입력해주세요"></div>
							</div>
							<div class="line1">
								<div class="label"><div>닉네임 : </div></div>
								<div class="input"><input type="text" name="" id="" style="padding: 10px; font-size: 0.8em;" value="${nickname}" readonly ></div>
							</div>
							<div class="line1">
								<div class="label"><div>전화 번호 : </div></div>
								<div class="input"><input type="text" name="" id="" style="padding: 10px;  font-size: 0.8em;" placeholder="전화번호"></div>
							</div>
							<div class="line1">
								* 본인의 이름을 입력해야 결제시 입금 확인이 가능합니다
							</div>
							<div class="buttonBox">
								<input type="submit" name="" id="" value="가입하기">
							</div>
						</form>
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

		<script>
			//아이디 중복 검사 함수 (수정완료)
			let idCheckFlg = false;
			let idCheck = () => {
				let mbId = ida.value;
				if(mbId){
					fetch("/member/idcheck?mbId="+mbId,{
						method:"GET"
					}).then(response =>response.text())
					.then(text =>{
						if(text == 'success'){
							idCheckFlg = true;
							confirm_id.innerHTML = '사용 가능한 아이디 입니다.';
						}else{
							idCheckFlg = false;
							confirm_id.innerHTML = '사용 불가능한 아이디 입니다.';
							console.dir(text);
							ida.value="";
						}
					})
				}else{
					alert("아이디를 입력하지 않으셨습니다.");
				}
			}
		
			//닉네임 체크 (수정완료)
			let nickCheckFlg = false;
			let nickCheck = () => {
				let mbnick = nick.value;
				if(mbnick){
					fetch("/member/nickcheck?mbnick="+mbnick,{
						method:"GET"
					}).then(response =>response.text())
					.then(text =>{
						if(text == 'success'){
							nickCheckFlg = true;
							confirm_nick.innerHTML = '사용 가능한 닉네임 입니다.';
						}else{
							nickCheckFlg = false;
							confirm_nick.innerHTML = '사용 불가능한 닉네임 입니다.';
							nick.value="";
						}
					})		
				}else{
					alert("닉네임을 입력하지 않으셨습니다.");
				}
			}
		
		
			//가입하기 버튼 클릭시  (수정완료)
			document.querySelector('#form_join').addEventListener('submit',(e)=>{
				let password = pw.value;
				let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;

				//-> 중복검사했는지 확인
				if(!idCheckFlg){
					e.preventDefault();
					alert("아이디 중복검사를 하지 않으셨습니다.");
					id.focus();
				}
				
				//-> 비밀번호 2개가 동일한지 확인
				if(document.querySelector("#pw").value != document.querySelector("#pw2").value){
					e.preventDefault();
					alert("비밀번호가 일치하지 않습니다.");
					document.querySelector("#pw").value = '';
					document.querySelector("#pw2").value = '';
					document.querySelector("#pw").focus();
				}
		
				//-> 비밀번호 양식에 맞는지 확인
				if(!(regExp.test(password))){
					//form의 데이터 전송을 막음
					e.preventDefault();
					confirm_pw.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다.';
					document.querySelector("#pw").value = '';
					document.querySelector("#pw2").value = '';
				}
			});
		</script>
	</body>
</html>