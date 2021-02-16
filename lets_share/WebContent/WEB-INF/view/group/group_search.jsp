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
					<div class="title"><a href="/group/search">그룹 서칭</a></div>
					<div class="btn_box">
						<form action="" method="GET">
							<input type="text" id="groupId" name="groupId"/>
							<input type="text" id="service" name="service">
							<input type="number" id="userPeriod" name="userPeriod">
							<!--<button class="btn_wrap"><a href="#ex1" rel="modal:open">그룹번호로 검색</a></button>  -->
							<button class="btn_wrap"><a href="#ex2" rel="modal:open">검색 정보 입력</a></button>
							<button class="btn">검색</button> 
							<!-- 그룹 자동검색 버튼 -->
							<button id="btn_auto" onclick="automatch()">그룹 자동검색</button>
						</form>
						<!-- ex1 : 그룹 번호 검색 -->
						<div id="ex1" class="modal">
							<h1>찾으시는 그룹 번호를 입력하세요</h1>
							<input type="text" id="group_id">
							<a href="#" rel="modal:close" id="btn_confirm">확인</a>
						</div>
						<!-- ex2 : 서비스 선택 & 사용일수 입력 -->	
						<div id="ex2" class="modal">
							<h1>원하는 서비스를 선택하세요.</h1>
							
							<div class="modal2Box">
								<div><img src="../../../resources/images/group/SR01.PNG" alt="" id="SR01" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR02.PNG" alt="" id="SR02" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR03.PNG" alt="" id="SR03" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR04.PNG" alt="" id="SR04" class="serviceSelect"></div>
								<div><img src="../../../resources/images/group/SR05.PNG" alt="" id="SR05" class="serviceSelect"></div>
							</div>
							<div class="modal2Box2">
								<h1>서비스를 사용하고 싶은 일수를 입력하세요.(15일~90일)</h1>
								<input type="number" id="user_period">
								<a href="#" rel="modal:close" id="btn_confirm2">확인</a>
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
					<c:choose>
						<c:when test='${service==""}'>
							<div class="paging">
								<a class="page_btn" href="/group/search?page=${firstPage-1}" style="background-color: white; color:black">&lt;</a>
								<c:forEach var="page" items="${pageList}">
								<c:choose>
									<c:when test = "${page==currentPage}">
										<a class="page_btn" href="/group/search?page=${page}" style="background-color: #FF9900">
											${page}
										</a>
									</c:when>
									<c:otherwise>
										<a class="page_btn" href="/group/search?page=${page}">
											${page}
										</a>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								<a class="page_btn" href="/group/search?page=${lastPage+1}" style="background-color: white; color:black">&gt;</a>
							</div>		
						</c:when>
						<c:otherwise>
							<div class="paging">
								<a class="page_btn" href="/group/search?service=${service}&page=${firstPage-1}" style="background-color: white; color:black">&lt;</a>
								<c:forEach var="page" items="${pageList}">
								<c:choose>
									<c:when test = "${page==currentPage}">
										<a class="page_btn" href="/group/search?service=${service}&page=${page}" style="background-color: #FF9900">
											${page}
										</a>
									</c:when>
									<c:otherwise>
										<a class="page_btn" href="/group/search?service=${service}&page=${page}">
											${page}
										</a>
									</c:otherwise>
								</c:choose>
								</c:forEach>
								<a class="page_btn" href="/group/search?service=${service}&page=${lastPage+1}" style="background-color: white; color:black">&gt;</a>
							</div>
						</c:otherwise>
					</c:choose>
					
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
				/* '검색'버튼을 제외한 각 버튼에 있는 기본이벤트 해제 */
				document.querySelector("#btn_auto").addEventListener("click",(e)=>{
					e.preventDefault();
				})
				document.querySelectorAll(".btn_wrap").forEach((b)=>{
					b.addEventListener("click",(e)=>{
						e.preventDefault();
					})
				});
				/* 모달1의 확인버튼 */
				document.querySelector("#btn_confirm").addEventListener("click",(e)=>{
					document.querySelector("#groupId").value = 
						document.querySelector("#group_id").value;
					
					document.querySelector("#userPeriod").value = 
						document.querySelector("#user_period").value;
					console.log(document.querySelector("#userPeriod").value);
					console.log(document.querySelector("#user_period").value);
				})
				/* 모달2의 확인버튼 */
				document.querySelector("#btn_confirm2").addEventListener("click",(e)=>{
					document.querySelector("#userPeriod").value = 
						document.querySelector("#user_period").value;
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
					location.href="/group/register?groupId="+groupId;
				}
				
				/* 자동검색 버튼의 동작 */
				let automatch = ()=>{
					
					let serviceCode = document.querySelector("#service").value;
					let userPeriod = document.querySelector("#userPeriod").value;
					
					if (!serviceCode){
						alert("원하는 서비스를 선택하세요.");
					}else if (!userPeriod){
						alert("서비스 사용을 원하는 일수를 입력하세요.");
					}else if(userPeriod < 15 || userPeriod > 90){
						alert("한번에 서비스를 사용가능한 기간은 15일에서 90일 사이입니다.")
					}else{
						location.href = "/auto?service=" + serviceCode + "&user_period=" + userPeriod;
					}
				}
			</script>
	</body>
</html>