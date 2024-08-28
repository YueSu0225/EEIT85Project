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
<c:if test="${!empty param.account }">
	<c:if test="${pageContext.request.method != 'POST' }"><c:redirect url="login.jsp"></c:redirect></c:if>
	<sql:setDataSource
		driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/iii"	
		user="root"
		password="root"
		/>
	<sql:update>
		INSERT INTO member (account,passwd,name) VALUES (?,?,?)
		<sql:param>${param.account }</sql:param>
		<sql:param>${BCrypt.hashpw(param.passwd, BCrypt.gensalt()) }</sql:param>
		<sql:param>${param.name }</sql:param>
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
	Add Member Page
	<hr />
	<form action="RC21" method='post' enctype="multipart/form-data">
		Account:  <input name='account' /><br />
		Password: <input type="password" name ="passwd"><br />
		Name: <input name='name' /><br />
		Icon: <input type="file" name="icon" /><br />
		<input type='submit' value='Add' />
	</form>
	</body>
</html>