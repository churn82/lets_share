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
	
	//�������� mb Ÿ�� �����͸� ������ ����Ʈ ����
	// ������ ����� Attribute �� �ִٸ� �����´�
	ArrayList<Member> list 
	= (ArrayList<Member>)session.getAttribute("list");
	
	if (list == null) {//Attribute �� ������ ��ü ����
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
	session.setAttribute("list",list); //������ ����Ʈ
	response.sendRedirect("admin.jsp");
	
%>
</body>
</html>