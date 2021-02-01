<!DOCTYPE HTML>
<!--
	Helios by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Let's Share</title>
		<style type="text/css">
			.searchForm{
				text-align :center;
				position : relative;
				top : 30px;
				display : inline;
			}
			
			.button{
			width : 100%;
			
			}
			
			.btng{
				position: absolute;
				right : 5%;
				background-color: skyblue;
			}
			.btnm{
					position: absolute;
				right : 15%;
				background-color: skyblue;
			}
			.imgs{
				width : 40%;
				margin : 10px auto;
				border: 1px solid #000000;
			}
			.imgs img{
				width: 100%;
				vertical-align: middle;
			}
			.img-text{
			padding : 5px 10px;
			background-color: white;
			text-align: center;
			}
			.page_btn{
				font-size : 10px;
				padding : 10px 10px;
			}
			.btn{
			max-width : 100%;
			height: auto;
			width : 450px;
			
			
			}
		
			
		
		</style>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="../../../resources/css/htmlMain.css" />
		<noscript><link rel="stylesheet" href="../../../resources/css/noscript.css" /></noscript>
	</head>
	<body class="no-sidebar is-preload">
	<!-- 페이지 넘버
	int pageNumber = 1;
	if (request.getParameter("pageNumber") != null) {
		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
	}
	
	 -->
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
			
			<div class="imgs">
			<p><a href="/WEB-INF/view/group/groupform.jsp">	<img src="../../../resources/images/arrow.jpg" align="center" width="100px" height="100px"/> </a></p>
				<div class="img-text">
				<p style="text-align : center;">가이드 페이지로 이동</p>
				</div>
			</div>	

			<!-- Main -->
			<div class="wrapper style1">
					<div class="wrap">
					<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
			
				
					<thead>
					<tr>
						
						<th style="background-color: #eeeeee; text-align: center;">번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">작성자</th>
						<th style="background-color: #eeeeee; text-align: center;">작성일</th>
					</tr>
				</thead>
					<tbody>
					
					
					<tr>
						<td><span>1 <!-- ${data.reportboard.bdIdx} --></span></td>
						<td>제목</td>
						<td>작성자</td>
						<td>2021-01-29</td>
					</tr>
					</tbody>
				</table>
				
	  
          <button onclick="submitData('list')"><span>목록</span></button>
       	  <button>수정</button>
       	  <button>삭제</button>
		  <a href="template.jsp" class="btng">글쓰기</a> <!-- 글쓰기.jsp 페이지로 이동 -->
		 
		  	<div class="btn" style="margin:0px 550px;">
		  		<button class="page_btnl">이전</button>
		  		<button class="page_btn">1</button>
		  		<button class="page_btn">2</button>
		  		<button class="page_btn">3</button>
		  		<button class="page_btn">4</button>
		  		<button class="page_btn">5</button>
		  		<button class="page_btnr">다음</button>
		  	</div>
		  
		  	
		
	 <div id="searchForm" class ="searchForm" >
        <form>
            <select name="option" style= "width : 150px; height : 40px; font-size: small; margin-left:535px;">
         		
                <option value="0">제목</option>
                <option value="1">내용</option>
                <option value="2">제목+내용</option>
                <option value="3">작성자</option>
               	<div>
               <input type="search" size ="40" value="검색"/> 
               <button>검색</button>
              </div> 
                		
               
            </select>
       		     
            
        </form>    
    </div>
		  
      	 
	
						</div>
					</div>
				</div>
		
		
		
		
		
	


		
	 



			<!-- Footer -->
				
				
					<div id="footer">
					<div style="text-align: center;">Copyright © 1998-2021 KH Information Educational Institute All Right Reserved</div>
				</div>

		<!-- Scripts -->
			<script src="../../../resources/js/jquery.min.js"></script>
			<script src="../../../resources/js/jquery.dropotron.min.js"></script>
			<script src="../../../resources/js/jquery.scrolly.min.js"></script>
			<script src="../../../resources/js/jquery.scrollex.min.js"></script>
			<script src="../../../resources/js/browser.min.js"></script>
			<script src="../../../resources/js/breakpoints.min.js"></script>
			<script src="../../../resources/js/util.js"></script>
			<script src="../../../resources/js/main.js"></script>

	</body>
</html>