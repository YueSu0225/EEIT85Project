<%@page import="java.util.HashMap"%>
<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String[] names1 = {"RC", "Mark", "Kevin", "John"};
	pageContext.setAttribute("names11", names1);
	
	LinkedList<String> list = new LinkedList<>();
	pageContext.setAttribute("list11", list);
	list.add("RC1");
	list.add("Mark1");
	list.add("Kevin1");
	list.add("John1");
	
	HashMap<String,Object> map = new HashMap<>();
	pageContext.setAttribute("map11", map);
	map.put("name", "HAO1");
	map.put("gender", "Male");
	map.put("age", 18);
	
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		names1[2] = ${names11[2] }<br />
		list[1] = ${list11[1] }<br />
		map11 = ${map11.name } : ${map11.gender } : ${map11.age }<br />
	</body>
</html>