<%@page import="tw.rc.apis.Bike"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = (String)request.getAttribute("name");
	Bike bike = (Bike)request.getAttribute("bike");
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	I am RC22<hr />
	Welcome, <%= name %><br>
	Bike speed = <%= bike.getSpeed()%>
	</body>
</html>