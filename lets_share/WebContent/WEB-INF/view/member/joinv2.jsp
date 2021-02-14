<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>

<head>
<c:set var="context" value="${pageContext.request.contextPath}"/>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/joinv2.css" />
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
				<div class="contetnBox">
					<div class="titleBox">
						<a href="">Let's Share 회원가입</a>
					</div>
					<form action="${context}/member/joinimpl" method="POST" id="form_join" class="member_info_form"> 
						<div class="formBox">
							<div class="line1">
								<div class="label"><div>ID : </div></div>
								<div class="input"><input type="text" name="Id" id="Id" style="padding: 10px;  font-size: 0.8em;" placeholder="아이디를 입력하세요"></div>
							</div>
							<div class="btnBox">
								<button type="button" onclick="idCheck()">중복확인</button> 
							</div>
							<div class="line2" id="confirm_id">
							
							</div>
							<div class="line1">
								<div class="label"><div>비밀 번호 : </div></div>
								<div class="input"><input type="password" name="password" id="password" style="padding: 10px;  font-size: 0.8em;" placeholder="숫자,영문자,특수문자 조합의 8글자 이상"></div>
							</div>
							<div class="btnBox">
							</div>
							<div class="line1">
								<div class="label"><div>비밀 번호 확인 : </div></div>
								<div class="input"><input type="password" name="passwordConfirm" id="passwordConfirm" style="padding: 10px;  font-size: 0.8em;" placeholder="비밀번호 확인"></div>
							</div>
							<div class="btnBox">

							</div>
							<div class="line1">
								<div class="label"><div>E-mail : </div></div>
								<div class="input"><input type="text" name="email" id="email" style="padding: 10px;  font-size: 0.8em;" placeholder="E-mail 주소를 입력 해주세요"></div>
							</div>
							<div class="btnBox">

							</div>
							<div class="line1">
								<div class="label"><div>이름 : </div></div>
								<div class="input"><input type="text" name="name" id="name" style="padding: 10px; font-size: 0.8em;" placeholder="본인의 이름을 입력 해주세요"></div>
							</div>
							<div class="btnBox">

							</div>
							<div class="line1">
								<div class="label"><div>닉네임 : </div></div>
								<div class="input"><input type="text" name="nickname" id="nickname" style="padding: 10px; font-size: 0.8em;" placeholder="그룹에서 사용할 닉네임을 입력 해주세요"></div>
							</div>
							<div class="btnBox">
								<button type="button" onclick="nickCheck()">중복확인</button>
							</div>
							<div class="line2" id="confirm_nick">
							
							</div>

							<div class="line1">
								<div class="label"><div>휴대폰 번호 : </div></div>
								<div class="input"><input type="text" name="phone" id="phone" style="padding: 10px;  font-size: 0.8em;" placeholder="휴대 전화 번호를 입력 해주세요"></div>
							</div>
							<div class="btnBox">
								<button type="button" onclick="sendAuthCode()">인증</button>
							</div>
							<div class="line2" id="confirm_phone">
							
							</div>

							<div class="line1">
								<div class="label"><div>인증 번호 : </div></div>
								<div class="input"><input type="text" name="authCode" id="authCode" style="padding: 10px;  font-size: 0.8em;" placeholder="인증 번호를 입력 해주세요"></div>
							</div>
							<div class="btnBox">
								<button type="button" onclick="confirmAuthCode()">확인</button>
							</div>
							<div class="line2" id="confirm_authCode">
							
							</div>
							<div class="line1">
								* 본인의 이름을 입력해야 결제시 입금 확인이 가능합니다
							</div>
							<div class="btnBox">

							</div>
							<div class="buttonBox">
								<input type="submit" name="" id="" value="가입하기">
							</div>
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

		<script>
			//아이디 중복 검사 함수 (수정완료) -> 
			let idCheckFlg = false;
			let idCheck = () => {
				//1. 아이디값 받아온다
 				let mbId = document.querySelector("#Id").value;
				
				//2-1. 아이디값이 있으면 비동기 통신 시작! ->success가오면 '사용가능' 다른게 오면 불가능 
				if(mbId){
					fetch("/member/idcheck?mbId="+mbId,{
						method:"GET"
					}).then(response =>response.text())
					.then(text =>{
						if(text == 'success'){
							//3-1. Success라면 idCheckFlg를 true로 바꿔주고 사용가능하다고 띄어줌
							idCheckFlg = true;
							document.querySelector("#confirm_id").innerHTML = '사용 가능한 아이디 입니다.';
						}else{
							//3-2. fail이라면 idCheckFlg를 false로 바꿔줌 사용불가능 공지
							idCheckFlg = false;
							document.querySelector("#confirm_id").innerHTML = '사용 불가능한 아이디 입니다.';
							console.dir(text);
							ida.value="";
						}
					})
				}else{ //2-2. 아이디입력하지 않았다 null이다!
					alert("아이디를 입력하지 않으셨습니다.");
				} 
			}
			
		
			//닉네임 체크 (수정완료)
			let nickCheckFlg = false;
			let nickCheck = () => {
				//1. 닉네임값 받아온다
				let mbnick = document.querySelector("#nickname").value;
				
				//2-1. 닉네임이 있으면 비동기 통신 시작! ->success가오면 '사용가능' 다른게 오면 불가능 
				if(mbnick){
					fetch("/member/nickcheck?mbnick="+mbnick,{
						method:"GET"
					}).then(response =>response.text())
					.then(text =>{
						if(text == 'success'){
							//3-1. Success라면 nickCheckFlg를 true로 바꿔주고 사용가능하다고 띄어줌
							nickCheckFlg = true;
							confirm_nick.innerHTML = '사용 가능한 닉네임 입니다.';
						}else{
							//3-2. fail이라면 nickCheckFlg를 false로 바꿔줌 사용불가능 공지
							nickCheckFlg = false;
							confirm_nick.innerHTML = '사용 불가능한 닉네임 입니다.';
							nick.value="";
						}
					})		
				}else{
					alert("닉네임을 입력하지 않으셨습니다.");
				}
			}
			
			//휴대폰으로 sms인증 보내기
			let sendAuthCode = () => {
				let phoneRegExp = /^[0-9]*$/;  
				//1. 휴대폰 번호 받아온다.  
				let phoneNum = document.querySelector("#phone").value;

				//2. 폰번호가 입력되었다면 
				if(phoneNum){
					if(!(phoneRegExp.test(phoneNum))){
						//3-1. 정규표현식을 만족하는지 확인
						document.querySelector("#confirm_phone").innerHTML = '정확한 휴대전화 번호를 입력하세요.';
					}else{
						//3-2. 비동기 통신진행 
						fetch("/member/sendAuthCode?phoneNum="+phoneNum,{
							method:"GET"
						}).then(response =>response.text())
						.then(text =>{
							if(text == 'success'){
								document.querySelector("#confirm_phone").innerHTML = "정상적으로 인증번호를 전송했습니다.";
							}else{
								alert("인증번호 전송 중 에러가 발생하였습니다.");
							}
						})		
					}
				}else{
					alert("휴대전화 번호를 입력하지 않으셨습니다.");
				}
			}


			//인증 번호 확인하는 함수
			let phoneCheckFlg = false;
			let confirmAuthCode = () => {
				//1. 인증번호를 받아오자
				let authCode = document.querySelector("#authCode").value;
				
				//2. 받아온 인증번호를 세션에있는 인증번호랑 비교하자 어디서 ? 자바단에서 (서블릿) -> 비동기로 똑같이 Success 받아올게요 자이제 잘 넘어가는지 확인할게요
				if(authCode){
					fetch("/member/confirmAuthCode?authCode="+authCode,{ 
						method:"GET"
					}).then(response =>response.text())
					.then(text =>{
						if(text == 'success'){
							phoneCheckFlg = true;  
							document.querySelector("#confirm_authCode").innerHTML = "인증에 성공 하셨습니다.";
						}else{
							phoneCheckFlg = false;
							document.querySelector("#confirm_authCode").innerHTML = "잘못된 인증 번호를 입력하셨습니다.";
						}
					})		
				}else{  
					alert("인증 번호를 입력하지 않으셨습니다.");
				}
			}
		
		
			//가입하기 버튼 클릭시  
			document.querySelector('#form_join').addEventListener('submit',(e)=>{
				let password = document.querySelector("#password").value;
				let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;

				//-> 중복검사했는지 확인 
				if(!idCheckFlg){
					e.preventDefault();
					alert("아이디 중복검사를 하지 않으셨습니다.");
					document.querySelector("#Id").focus();
				}
				
				//-> 비밀번호 2개가 동일한지 확인 
				if(document.querySelector("#password").value != document.querySelector("#passwordConfirm").value){
					e.preventDefault();
					alert("비밀번호가 일치하지 않습니다.");
					document.querySelector("#password").value = '';
					document.querySelector("#passwordConfirm").value = '';
					document.querySelector("#password").focus();
				}
		
				//-> 비밀번호 양식에 맞는지 확인
				if(!(regExp.test(password))){
					//form의 데이터 전송을 막음
					e.preventDefault();
					confirm_pw.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다.';
					document.querySelector("#password").value = '';
					document.querySelector("#passwordConfirm").value = '';
				}

				//->닉네임 중복 검사했는지 확인
				if(!nickCheckFlg){
					e.preventDefault();
					alert("닉네임 중복검사를 하지 않으셨습니다.");
					document.querySelector("#nickname").focus();
				}

				//-> 휴대폰 인증했는지 확인
				if(!phoneCheckFlg){
					e.preventDefault();
					alert("휴대폰 인증을 하지 않으셨습니다.");
					document.querySelector("#phone").focus();
				}		
			});
		</script>
	</body>
</html>