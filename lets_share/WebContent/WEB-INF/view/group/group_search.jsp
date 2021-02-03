<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="/resources/css/group/group_search.css" />
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
					<div class="title"><a href="">그룹 서칭</a></div>
					<div class="btn_box">
						<form action="" method="GET">
							<input type="text" id="groupId" name="groupId"/>
							<input type="text" id="service" name="service">
							<button><a href="#ex1" rel="modal:open">그룹 번호 입력</a></button>
							<button><a href="#ex2" rel="modal:open">서비스 선택</a></button>
							<input type="submit" class=".btn" value="검색">
						</form>
						<!-- ex1 : 결제 날짜 선택 모달  -->
						<div id="ex1" class="modal">
							<h1>찾으시는 그룹 번호를 입력하세요</h1>
							<input type="text" id="group_id">
							<a href="#" rel="modal:close" id="btn_confirm">확인</a>
						</div>	
						<div id="ex2" class="modal">
							<h1>원하는 서비스를 선택하세요</h1>
							
							<div class="modal2Box">
								<div><img src="../../../resources/images/group/SR01.PNG" alt="" id="SR01" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR02.PNG" alt="" id="SR02" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR03.PNG" alt="" id="SR03" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR04.PNG" alt="" id="SR04" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR05.PNG" alt="" id="SR05" class="serviceSelect"></div>
							</div>
							
							<!-- <input type="submit" value="확인" class="btn"> -->
							<!-- <button><a href="#" onclick="submit()">확인</a></button> -->
						</div>
						
						
					</div>
					<div class="nodeBox">
						<c:forEach var="group" items="${groupList}">
	                        <div class="node">
	                            <div class="leftBox">
	                                <div class="imgBox">
	                                    <img src="../../../resources/images/group/${group.getMemberCnt()}.PNG" alt="">
	                                </div>
	                            </div>
	                            <div class="rightBox">
	                                <div class="line1">
	                                    <div class="logoBox">
	                                        <img src="../../../resources/images/group/${group.getServiceCode()}.PNG" alt="">
	                                    </div>
	                                    <div class="nameBox">
	                                        <h5>
												<c:choose>
												<c:when test="${group.getServiceCode() eq 'SR01'}">
													넷플릭스 No.${group.getGroupId()}
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR02'}">
													왓챠No.${group.getGroupId()}
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR03'}">
													쿠팡플레이 No.${group.getGroupId()}
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR04'}">
													웨이브 No.${group.getGroupId()}
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR05'}">
													티빙 No.${group.getGroupId()}
												</c:when> 
												</c:choose>
											</h5>
	                                    </div>
	                                </div>
	                                <div class="line2">
	                                    <h1>현재 인원 : ${group.getMemberCnt()}/4 </h1>
	                                </div>
	                                <div class="line3">
	                                    <button onclick="register(${group.getGroupId()})">가입</button>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>                       
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
				<!-- 모달 -->
			<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	
			<script>
				document.querySelector("#btn_confirm").addEventListener("click",(e)=>{
					document.querySelector("#groupId").value = 
						document.querySelector("#group_id").value;
				})
				document.querySelectorAll(".serviceSelect").forEach((node)=>{
					node.addEventListener("click", (e)=>{
						document.querySelectorAll(".serviceSelect").forEach(all=>{
							all.style = " ";
						})
						e.target.style = "border : 2px solid #ef8376";
						document.querySelector("#service").value = e.target.id;
					})
				})
				
				let register = (groupId) => {
					if(sessionStorage.getItem("userId")==null){
						alert("로그인을 하고 가입해주세요");
					}
					sessionStorage.setItem("userId","test49");
					let userId = sessionStorage.getItem("userId");
					location.href="/group/register?userId="+userId+"&groupId="+groupId;
				}


			</script>
	</body>
</html>