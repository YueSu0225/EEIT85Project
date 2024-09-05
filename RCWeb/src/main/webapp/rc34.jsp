<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.rc.apis.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<c:set var="score">${RCUtils.createScore() }</c:set>
	Score : ${score }<br />
	<!-- c:if 只能用在true,多分支(2隻以上)不要使用 -->
	<c:if test="${score >= 60 }">Pass</c:if>
	<c:if test="${score < 60 }">Down</c:if>
	<hr />
	<!-- choose結構像switch,但是使用比較像else if -->
	<!-- 使用網頁條件成立時,可以呈現怎樣 -->
	<c:choose>
		<c:when test="${score >= 90 }">A</c:when>
		<c:when test="${score >= 80 }">B</c:when>
		<c:when test="${score >= 70 }">C</c:when>
		<c:when test="${score >= 60 }">D</c:when>
		<c:otherwise>E</c:otherwise>
	</c:choose>
	
	
	</body>
</html>