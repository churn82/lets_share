<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@page import="com.kh.member.model.dao.MemberDao"%>
<%@page import="com.kh.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title></title>
</head>
<body>


<%
	request.setCharacterEncoding("UTF-8");
	
	//여러개의 mb 타입 데이터를 저장할 리스트 생성
	// 기존에 저장된 Attribute 가 있다면 가져온다
	ArrayList<Member> list 
	= (ArrayList<Member>)session.getAttribute("list");
	
	if (list == null) {//Attribute 값 없으면 객체 생성
		list = new ArrayList<>();
	}
	
	String mbId = request.getParameter("MbId");
	String mbnick = request.getParameter("Mbnick");
	String mbemail = request.getParameter("Mbemail");
	String mbtel = request.getParameter("Mbtel");
	
	Member member = new Member();
	
	member.setMbId(mbId);
	member.setMbnick(mbnick);
	member.setMbemail(mbemail);
	member.setMbtel(mbtel);
	
	list.add(member);
	session.setAttribute("list",list); //데이터 리스트
	response.sendRedirect("admin.jsp");
	
%>
</body>
</html>