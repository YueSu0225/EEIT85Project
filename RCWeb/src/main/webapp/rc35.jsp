<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
	String[] name = {"RC", "Andy", "Tony", "Mark"};
	pageContext.setAttribute("names", name);

%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	
	<table border="1" width="100%">
		<tr>
			<th>id</th>
			<th>count</th>
			<th>Name</th>
			<th>First</th>
			<th>Last</th>
		</tr>
		<!-- items:要尋訪目標 var:尋訪後要被呼叫的變數名稱 -->
		<!-- varStatus:本身是物件,可以.index(尋訪目標的屬性) -->
		<!-- .count(累計次數).first(是否為尋訪物件裡第一).last(是否為最後一個) -->
		<c:forEach var="x" items="${names }" varStatus="status">
			<tr>
				<td>${status.index }</td>
				<td>${status.count }</td>
				<td>${x }</td>
				<td>${status.first }</td>
				<td>${status.last }</td>
			</tr>
		</c:forEach>
	</table>
	
	</body>
</html>