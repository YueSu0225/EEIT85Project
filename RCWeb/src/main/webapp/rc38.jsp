<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/iii"	
	user="root"
	password="root"
	/>
<c:set var="sql">SELECT * FROM gift</c:set>
<c:if test="${! empty param.key }">
	<c:set var="sql">SELECT * FROM gift WHERE name LIKE '%${param.key }%' OR addr LIKE '%${param.key }%'</c:set>
</c:if>
	
<sql:query var="rs">
	${sql }
</sql:query>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	共 ${rs.rowCount } 筆資料<hr />
	<hr />
	<c:forEach var="colnames" items="${rs.columnNames }">
		${colnames }<br />
	</c:forEach>
	<hr />
	<form action="">
		Keyword : <input name="key" value="${param.key }" />
		<input type="submit" value="Serch" />
	</form>
	<hr />
	<table border="1" width="100%">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Addr</th>
			<th>Pic</th>
		</tr>
		<c:forEach var="gift" items="${rs.rows }">
			<tr>
				<td>${gift.id }</td>
				<td>${gift.name }</td>
				<td>${gift.addr }</td>
				<td><img src="${gift.picurl }" width="160px" heigh="100px" /></td>
			</tr>
		</c:forEach>
	</table>
	
	</body>
</html>