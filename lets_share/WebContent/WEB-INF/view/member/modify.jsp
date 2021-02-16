<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<%@page import="com.kh.member.model.dao.MemberDao"%>
<%@page import="java.util.ArrayList"%>



<head>
	<title>Let's Share</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<link rel="stylesheet" href="/resources/css/member/modify.css" />
	<link rel="stylesheet" href="/resources/css/main.css" />
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
						<h1><a href="${context}/index" id="logo">Let's Share</a></h1>
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
				<div class="left_menu">
					<div><i class="fas fa-trophy"></i>  회원 랭킹</div>
					<div><i class="fas fa-edit"></i>  회원 정보 수정</div>
				</div>
				<h1 class="modify_title">회원정보 수정</h1>
				<div class="pw_secure contents">
					<span>보안을 위해 비밀번호를 입력해주세요.</span>
					<form class="pw_secure_auth">
						<span>비밀번호</span>
						<input type="password">
						<button>확인</button>
					</form>
				</div>
				<div class="modify_info contents">
					<form class="wrap_info" method="POST" id="form_modify" name="form">
						<div class="id infobox">
							<span class="name">아이디</span>
							<span class="value">${sessionScope.user.mbId}</span>
						</div>
						<div class="password infobox">
							<span class="name" >비밀번호 변경</span>
							<input type="password" placeholder="변경할 비밀번호를 입력하세요." class="value" id="pw" name="pw">
						</div>
						<div class="password_confirm infobox">
							<span class="name">비밀번호 확인</span>
							<input type="password" placeholder="변경할 비밀번호를 다시 입력하세요." class="value" id="pw2">
						</div>
						<div class="nickname infobox">
							<span class="name">닉네임</span>
							<input type="text" id="nick" name="nick" placeholder="${sessionScope.user.mbnick}" class="value">
							<button type="button" onclick="nickCheck()">중복확인</button>
							<span class="vaild_info" id="nickCheck"></span>							
						</div>
						<div class="confirm_msg">
							<span class="name" id="confirm_nick"></span>
							<!-- 중복확인 클릭시에만 텍스트 표시(페이지로드시에는 내용x) -->
							<span class="value"></span>
						</div>
						<div class="email infobox">
							<span class="name">이메일</span>
							<span class="value">${sessionScope.user.mbemail}</span>
						</div>	
						<!--  관리자 회원관리 -->
						<div class="wrap_btn">
							<button id="save_btn" onclick = "javascript:form.action='${context}/member/modifyimpl';" type="submit">저장</button>
					</form>
							<button id="leave_btn"><a href="#ex1" rel="modal:open">회원 탈퇴하기</a></button>
							<button id="cancel_btn">돌아가기</button>
						</div>
				</div>
			</div>
			<!-- Footer -->
			<div id="footer">
				<div style="text-align: center;">Copyright © 1998-2021 KH Information Educational Institute All Right Reserved</div>
			</div>

			<div id="ex1" class="modal">
				<h5>회원 탈퇴</h5>
				<form action="/member/withdrawal" method="POST" id="form_withdrawal">
					<input type="password" name="wpw" id="wpw" placeholder="비밀번호 입력">
					<input type="password" name="wpw2" id="wpw2" placeholder="비밀번호 확인">
					<div>탈퇴 후 모든 정보는 완전히 삭제되며 더 이상 복구 할 수 없게됩니다.</div>
					<div>소속한 그룹이 있으면 탈퇴가 진행이 불가능 합니다. 그룹 탈퇴를 먼저 진행 해주세요</div>
					<div class="">
						<input type="submit" value="탈퇴">
					</div>
				</form>
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

		<script type="text/javascript">	

			//닉네임 체크 
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

			//수정하기 버튼 클릭시 
			document.querySelector("#form_modify").addEventListener("submit",(e)=>{

				let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;

				//-> 비밀번호 2개가 동일한지 확인
				if(document.querySelector("#pw").value != document.querySelector("#pw2").value){
					e.preventDefault();
					alert("비밀번호가 일치하지 않습니다.");
					document.querySelector("#pw").value = '';
					document.querySelector("#pw2").value = '';
					document.querySelector("#pw").focus();
				}

				//-> 비밀번호가 정규표현식 만족하는지 확인
				if(!(regExp.test(document.querySelector("#pw").value))){
					e.preventDefault();
					alert("비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다.");
					document.querySelector("#pw").value = '';
					document.querySelector("#pw2").value = '';
				}
			})


			//탈퇴 클릭시 
			document.querySelector("#form_withdrawal").addEventListener("submit",(e)=>{
				//-> 비밀번호 2개가 동일한지 확인
				if(document.querySelector("#wpw").value != document.querySelector("#wpw2").value){
					e.preventDefault();
					alert("비밀번호가 일치하지 않습니다.");
					document.querySelector("#wpw").value = '';
					document.querySelector("#wpw2").value = '';
					document.querySelector("#wpw").focus();
				}
			})


		</script>
	</body>
</html>