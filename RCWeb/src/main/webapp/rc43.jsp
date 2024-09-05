<%@page import="tw.rc.apis.MyTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Object obj = session.getAttribute("obj2");
	if( obj == null){
		response.sendRedirect("rc42.jsp");
		//response.sendError(HttpServletResponse.SC_FORBIDDEN, "WTF....");
	}
	//將obj物件強制轉型為MyTest物件
	//MyTest lottery = (MyTest)obj;
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	I am rc43 <hr />
	Lottery: ${obj2.lottery }<br />
	
	<br />	
	<a href="rc44.jsp">Logout</a>
	
	</body>
</html>