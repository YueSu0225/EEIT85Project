<%@page import="tw.rc.apis.MyTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	
	MyTest obj = new MyTest();

	int lottery = obj.getLottery();
	session.setAttribute("obj2", obj);
	
	
	//設定10S後session直接摧毀
	session.setMaxInactiveInterval(10);
	
	
	
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	I am rc42 <hr />
	Lottery: <%= lottery %><br />
	Lottery: ${obj2.lottery }<br />
	<a href="rc43.jsp">Next : rc43.jsp</a>
	</body>
</html>
<%
	obj.setLottery(1000);
%>

