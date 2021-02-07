<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>
<head>
		<title>Let's Share</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="/resources/css/main.css" />
		<link rel="stylesheet" href="../../../resources/css/notice/writer.css" />
		<noscript><link rel="stylesheet" href="/resources/css/noscript.css" /></noscript>
</head>
<body class="no-sidebar is-preload">
	
<div id="page-wrapper">
	
	<div id="header">

		<div class="inner">
			<header>
				<h1><a href="index.html" id="logo">Let's Share</a></h1>
			</header>
		</div>

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

<div id="main"> <%-- <div class="wrapper style1"> --%>
	<div class="section">
		<h2>게시글 작성</h2>
		
	<div class="content_wrap">
		<div class="content">
		
			<form id="insertForm" name="boardWriteForm" action="/notice/writeImpl" method="post">
        		<div class="form form_title">
        			<div class="title_wrap">
						<div class="form_space">제목</div>
						<!-- 파일선택 -->
						<div class="form_space file_sel">
							<input type="file" name="files">
						</div>
					</div>
					<input type="text" name="title" required="required" placeholder="제목을 입력하세요."/>
          		</div>
				<div class="form form_content">
		            <div class="form_space">내용</div>
		            <textarea rows="10" name="contents" required="required"></textarea>
				</div>
	    	
					
			
			<!-- submit 버튼 -->
			<div class="submits">
				<!-- 카테고리 -->
				<div class="cate_sel">
					<label for="cate">카테고리: </label>
					<select name="writer_cate" class="wrap_option">
						<option value="notice">공지사항</option>
						<option value="event">이벤트</option>
					</select>
				</div>
				<button class="write_btn" onclick="writeFn()">작성</button>
		        <button type="submit" class="save_btn">임시저장</button>
			</div>
			
			<!-- 목록버튼 -->
			<div class="list_btn">
				<button>목록</button>
			</div>
			</form>
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

	<script type="text/javascript">
	/* 	function boardWriteCheck(){
			let form = document.boardWriteForm;
			
			if(form.title.value == ''){
				alert('제목을 입력하세요.');
				
				form.title.focus();
				return false;
			}
			
			if(form.contents.value==''){
				alert('내용을 입력하세요.');
			} */
			
			//카테고리 선택
			
		
	 	
		
		//목록을 눌렀을 때 안 됨
		function listClick(){
			location.href="notice/noticeList"
		} 
	
		
		function writeFn(){
			location.href="noticeListImpl";
		}
	
		let noticeValue = document.querySelector(".cate_sel")
		
		
	</script>







	</body>
</html>