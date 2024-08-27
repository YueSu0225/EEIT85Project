<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 這頁面是rc27.html執行的方法 -->     
<%	
	System.out.println(request.getMethod());
	//JAVA方式:只接收POST信息,如果不是跳轉ERROR405頁面 
	//if (!request.getMethod().equals("POST")) response.sendError(405);

	String account = request.getParameter("account");
	String[] habbits = request.getParameterValues("habbit");
	System.out.println(habbits.length);
	request.getRemoteAddr();
	Locale loc = request.getLocale();
	loc.getDisplayLanguage();
	request.getScheme();
%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		Method: ${pageContext.request.method }<br />
		1. ${paramValues.habbit[0] } <br />
		2. ${paramValues.habbit[1] } <br />
		3. ${paramValues.habbit[2] } <br />
		4. ${paramValues.habbit[3] } <br />
		5. ${paramValues.habbit[4] } <br />
		6. ${paramValues.habbit[5] } <br />
		7. ${paramValues.habbit[6] } <br />
		<hr />
		${pageContext.request.remoteAddr }<br />
		${pageContext.request.locale }<br />
		${pageContext.request.locale.displayLanguage }<br />
		${pageContext.request.scheme }<br />
	</body>
</html>