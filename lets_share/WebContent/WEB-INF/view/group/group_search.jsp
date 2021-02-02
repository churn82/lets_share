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
							<input type="text" id="date" name="date"/>
							<input type="text" id="service" name="service">
							<button><a href="#ex1" rel="modal:open">결제 날짜 선택</a></button>
							<button><a href="#ex2" rel="modal:open">서비스 선택</a></button>
							<input type="submit" class=".btn" value="검색">
						</form>
						<!-- ex1 : 결제 날짜 선택 모달  -->
						<div id="ex1" class="modal">
							<h1>원하는 결제 날짜를 선택하세요</h1>
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
							
							<!-- <input type="submit" value="확인" class="btn"> -->
							
							
							<!-- <a href="#" rel="modal:close">Close</a> -->
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
	                                        <h1>
												<c:choose>
												<c:when test="${group.getServiceCode() eq 'SR01'}">
													넷플릭스
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR02'}">
													왓챠
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR03'}">
													쿠팡 플레이
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR04'}">
													웨이브
												</c:when>
												<c:when test="${group.getServiceCode() eq 'SR05'}">
													티빙
												</c:when> 
												</c:choose>
											</h1>
	                                    </div>
	                                </div>
	                                <div class="line2">
	                                    <h1>현재 인원 : ${group.getMemberCnt()}/4</h1>
	                                </div>
	                                <div class="line3">
	                                    <h1>결제 예정일 : ${group.getGroupPayDate()} 일</h1>
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
				document.querySelectorAll(".date").forEach((node=>{
					node.addEventListener("click", (e)=>{
						document.querySelectorAll(".date").forEach(all=>{
							all.style = "background-color : white;"
						})
						e.target.style = "background-color : #ef8376;";
						document.querySelector("#date").value = e.target.id;
					})
				}))


				document.querySelectorAll(".serviceSelect").forEach((node)=>{
					node.addEventListener("click", (e)=>{
						document.querySelectorAll(".serviceSelect").forEach(all=>{
							all.style = " ";
						})
						e.target.style = "border : 2px solid #ef8376";
						document.querySelector("#service").value = e.target.id;
					})
				})


			</script>
	</body>
</html>