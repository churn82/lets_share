<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import = "com.kh.common.code.Code"
    %>
     <%@ include file="/WEB-INF/view/include/head.jsp" %>

<body>
<h1>회원가입을 완료해주세요!</h1>
<h1>반갑습니다. ${param.userId} 님.</h1>
<h1>아래의 링크를 클릭해 회원가입을 완료해주세요.</h1>
<a href="<%= Code.DOMAIN %>/member/joinimpl">회원가입완료</a><br>
<img src="https://images.unsplash.com/photo-1528329140527-75853b1e1650?ixid=MXwxMjA3fDB8MHxzZWFyY2h8NHx8bWFpbHxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"/>



</body>
</html>