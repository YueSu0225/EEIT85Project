<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String mesg = "";
	String errType = request.getParameter("errType");
	if (errType != null){
		switch (errType){
			case "1": mesg = "密碼錯誤!"; break;
			case "2": mesg = "查無此帳號"; break;			
		}
	}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<div><h1>登入</h1></div>
		<form action='Owntest' method='post'>
			Account:  <input name='account' /><br />
			Password: <input type='password' name='passwd' /><br />
			<input type='submit' value='Login'>
		</form>
		    <% if ("2".equals(errType)) { %>
        <p><%= mesg %></p>
        <form action='owntestregister.jsp' method='get'>
            <input type='submit' value='註冊'>
        </form>
    	<% } else if ("1".equals(errType)) { %>
        <p><%= mesg %></p>
    	<% } %>	
	</body>
</html>