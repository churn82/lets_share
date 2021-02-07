<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/head.jsp" %>

<body>
	<form action="/auto/find" method="post">
		<h1>자동매칭화면</h1>
		<fieldset>
			<legend>당신이 원하는 서비스를 고르세요</legend><br>
			<label>Netflix
				<input type="radio" value="SR01" name="service">
			</label><br><br>
			<label>Watcha
				<input type="radio" value="SR02" name="service">
			</label><br><br>
			<label>Coupang
				<input type="radio" value="SR03" name="service">
			</label><br><br>
		</fieldset>
		
		<h2>원하는 사용 일수를 입력하세요</h2>
		<input type="Number" name="user_period">
		<button id="btn_matching">전송</button>
	</form>
		
	<!-- 검색 조건에 맞는 그룹이 없는 경우, 검색에 성공할 때까지 기다리는 비동기 동작 추가 -->
	<!-- 전송버튼 누를 시부터 시간카운트 동작 추가 -->
<!-- 	<script type="text/javascript">
		let match = ()=>{
			let serviceCode = ; //service 중 선택된 것의 value 가져오기
			let userPeriod = 'qwe'; //user_period의 값
			let url = ${context} + "/auto/find?service="+serviceCode+"&user_period"+userPeriod;
			
			let response = fetch(url,{"method":"get"});
			
			let groupFound = response.groupFound;
			if(groupFound == 'notFound'){
				setTimeout(() => {
					match();
				}, 1500);
			}
		}
		document.querySelector('#btn_matching').addEventListener('click',()=>{
			alert("????");
			match();
		});
	</script> -->
</body>
</html>