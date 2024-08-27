<%@page import="java.util.HashSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="rc14.jsp" %>
<%
	String name = request.getParameter("name");
	name.charAt(0);//這段是故意讓網頁出錯
	if (name == null) name = "World";
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	Hello, World<br />
	Hello, <% out.print(name); %><br />
	Hello, <%= (int)(Math.random()*49 + 1) %>
	<hr />
	<%
		HashSet<Integer> set = new HashSet<>();
		while( set.size() < 6) set.add((int)(Math.random()*49 + 1));
		out.print(set);
	%>
	</body>
</html>