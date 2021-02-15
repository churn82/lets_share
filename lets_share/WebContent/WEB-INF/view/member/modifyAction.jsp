<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/member/modify.css" />
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
								<li><a href="${context}/index">Home</a></li>
								<li><a href="${context}/group/form">구매자 모집</a></li>
								<li><a href="${context}/group/search">구매 참여</a></li>
								<li><a href="${context}/report/listAll">신고 게시판</a></li>
								<li><a href="${context}/notice/noticeList?p=1">공지 사항</a></lI>
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
						<form class="wrap_info">
							<div class="id infobox">
								<span class="name">아이디</span>
								<span class="value">${sessionScope.user.mbId}</span>
							</div>
							<div class="password infobox">
								<span class="name">비밀번호 변경</span>
								<input type="password" placeholder="변경할 비밀번호를 입력하세요." class="value">
							</div>
							<div class="password_confirm infobox">
								<span class="name">비밀번호 확인</span>
								<input type="password" placeholder="변경할 비밀번호를 다시 입력하세요." class="value">
							</div>
							<div class="nickname infobox">
								<span class="name">닉네임</span>
								<input type="text" id="nick" name="nick" placeholder="${sessionScope.user.mbNick}" class="value">
								<button type="button" onclick="nickCheck()">중복확인</button>
								<span class="vaild_info" id="nickCheck"></span>							</div>
							<div class="confirm_msg">
							<span class="name" id="confirm_nick"></span>
							<!-- 중복확인 클릭시에만 텍스트 표시(페이지로드시에는 내용x) -->
							<span class="value"></span>
							</div>
							<div class="email infobox">
								<span class="name">이메일</span>
								<span class="value">${sessionScope.user.mbemail}</span>
							</div>
							<div class="wrap_btn">
								<button id="leave_btn">회원 탈퇴하기</button>
								<button id="cancel_btn">돌아가기</button>
								<button id="save_btn">저장</button>
							</div>
						</form>
					</div>
				</div>
				
			<script type="text/javascript">	
				let nickCheckFlg = false;
		let nickCheck = () => {
		
			let mbnick = nick.value;
			
			if(mbnick){
				fetch("/member/nickcheck?mbnick=" + mbnick,{
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
		</script>
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