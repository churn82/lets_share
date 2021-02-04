<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>

<body>
	<form action="/auto/preference" method="post">	
		<h1>자동매칭화면</h1>
		<fieldset>
			<legend>당신이 원하는 서비스를 고르세요</legend><br>
			<label>Netflix
				<input type="radio" value="netflix" name="service">
			</label><br><br>
			<label>Watcha
				<input type="radio" value="watcha" name="service">
			</label><br><br>
			<label>Coupang
				<input type="radio" value="coupang" name="service">
			</label><br><br>
		</fieldset>
		
		<h2>원하는 사용 일수를 입력하세요</h2>
		<input type=text value="user_period">
		<button>전송</button>
	</form>



</body>
</html>