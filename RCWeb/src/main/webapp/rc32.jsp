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
	<!-- c:set沒指定scope的話,預設為pageScope -->
	<c:set var="x" value="124" />
	<c:set var="x" value="3211" scope="request" />
	<c:set var="y">777</c:set>
	x = ${x }<br />
	x = ${pageScope.x }<br />
	x = ${requestScope.x }<br />
	y = ${y }<br />
	<hr />
	Hello, World<br />
	<c:out value="Hello, World" /><br />
	<!-- 標籤裡可以在EL參數為null時候,顯示default="字串" -->
	<c:out value="${x }" /><br />
	param.x = <c:out value="${param.x }" default="no param" /><br />
	param.x = ${param.x }<br />
	<hr />
	<!-- c:set target意思是 set的目標:名稱為member的物件(class="tw.rc.apis.Member") -->
	<!-- 前提是class="tw.rc.apis.Member"有get set方法 -->
	<jsp:useBean id="member" class="tw.rc.apis.Member"></jsp:useBean>
	<c:set target="${member }" property="id">1</c:set>
	<c:set target="${member }" property="account">rc</c:set>
	<c:set target="${member }" property="name">RC</c:set>
	${member.id } : ${member.account } : ${member.name }<br />
	${member }
	<hr />
	<!-- remove就是刪除 -->
	<c:remove var="member" />
	${member }
	
	
	</body>
</html>