<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int b =  (Integer)application.getAttribute("a11");
	b++;
	application.setAttribute("a11", b);
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	a = ${a11 }
	</body>
</html>