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
	<c:catch var="err">
<sql:update>
	UPDATE member SET account = ? WHERE id = ?
	<sql:param>rcok</sql:param>
	<sql:param>4</sql:param>
</sql:update>
</c:catch>
${err }