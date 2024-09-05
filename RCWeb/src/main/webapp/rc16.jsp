
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mesg = "";
	String errType = request.getParameter("errType");
	if (errType != null){
		switch (errType){
			case "1": mesg = "Account EXIST!"; break;
			case "2": mesg = "SQL ERROR!"; break;
			case "3": mesg = "Register FAILURE!"; break;
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
	<div><%= mesg %></div>
	<script>
		function checkForm(){
			
			
			return true;
		}
	</script>
	<form action="Register" method="post" onsubmit="return checkForm();">
		Account : <input name="account" /><br />
		Password : <input name="passwd" /><br />
		Name : <input name="name" /><br />
		<input type="submit" value="Register" />
	</form>


	
	</body>
</html>
