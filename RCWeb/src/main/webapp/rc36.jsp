
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Properties"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<c:catch var="err">
	<%	//使用java連線mysql方式來連接
		Class.forName("com.mysql.cj.jdbc.Driver");//jsp載入mysql驅動
		Properties prop = new Properties();
		prop.put("user", "root");prop.put("password", "root");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iii", prop);
		String sql = "INSERT INTO member (account, passwd,name) VALUES (?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "ispan");pstmt.setString(2, "123");pstmt.setString(3, "iSpan");
		int n = pstmt.executeUpdate();
		pageContext.setAttribute("n1", n);   //產生EL => n
	%>
	</c:catch>
	Hello, RC36<hr />
	<c:if test="${!empty err }">Server Busy:${err }</c:if>
	<c:choose>
		<c:when test="${ n1 > 0 }">Success</c:when>
		<c:otherwise>Failure</c:otherwise>
	</c:choose>
	
	</body>
</html>