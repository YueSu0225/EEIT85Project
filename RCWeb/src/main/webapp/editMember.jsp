<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.mindrot.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%
	request.setCharacterEncoding("UTF-8");
%>    
<c:if test="${empty member.account }">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost/iii"
	user="root"
	password="root"
	/> 	
<c:if test="${!empty param.editid }">
	<sql:query var="rs">
		SELECT * FROM member WHERE id = ?
		<sql:param>${param.editid }</sql:param>
	</sql:query>
	<c:if test="${rs.rowCount == 0 }"><c:redirect url="main.jsp"></c:redirect></c:if>
	
</c:if>

<c:if test="${!empty param.account }">
	<c:if test="${pageContext.request.method != 'POST' }"><c:redirect url="logout.jsp"></c:redirect></c:if>
	<sql:update>
		UPDATE member SET account=?,passwd=?,name=? WHERE id = ?
		<sql:param>${param.account }</sql:param>
		<sql:param>${BCrypt.hashpw(param.passwd, BCrypt.gensalt()) }</sql:param>
		<sql:param>${param.name }</sql:param>
		<sql:param>${param.id }</sql:param>
	</sql:update>
	<c:redirect url="main.jsp"></c:redirect>		
</c:if>	 
	
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	Edit Member Page
	<hr />
	<form method="post">
		<input type="hidden" name="id" value="${rs.rows[0].id }">
		Account: <input name="account" value="${rs.rows[0].account }" /><br />
		Password: <input type="password" name="passwd" /><br />
		Name:<input name="name" value="${rs.rows[0].name }" /><br />
		<input type="submit" value="Update" />
	</form>		
	</body>
</html>