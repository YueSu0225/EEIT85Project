<%@page import="java.util.Base64"%>
<%@page import="tw.rc.apis.Member"%>
<%@page import="javax.servlet.jsp.jstl.sql.Result"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.mindrot.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    

<c:if test="${empty param.account }">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/iii"	
	user="root"
	password="root"
	/>
<sql:query var="rs" >
	SELECT * FROM member WHERE account = ?
	<sql:param>${param.account }</sql:param>
</sql:query>
<c:if test="${rs.rowCount == 0}">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>
<c:choose>
	<c:when test="${BCrypt.checkpw(param.passwd, rs.rows[0].passwd) }">
		<%
			Result rs1 = (Result)pageContext.getAttribute("rs");
			long id = (Long)rs1.getRows()[0].get("id");
			String account = (String)rs1.getRows()[0].get("account");
			String passwd = (String)rs1.getRows()[0].get("passwd");
			String name = (String)rs1.getRows()[0].get("name");
			byte[] icon = (byte[])rs1.getRows()[0].get("icon");
			String iconBase64 = Base64.getEncoder().encodeToString(icon);
			
			Member member = new Member();
			member.setId((int)id);
			member.setAccount(account);
			member.setPasswd(passwd);
			member.setName(name);
			member.setIcon(iconBase64);			
			
			session.setAttribute("member", member);
		%>
		<c:redirect url="main.jsp"></c:redirect>
	</c:when>
	<c:otherwise>
		<c:redirect url="login.jsp"></c:redirect>
	</c:otherwise>
</c:choose>

    