
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mesg = "";
	String errType = request.getParameter("errType");
	if (errType != null){
		switch (errType){
			case "1": mesg = "帳號已存在!"; break;
			case "2": mesg = "連線錯誤!"; break;
			case "3": mesg = "註冊失敗"; break;
			case "4": mesg = "帳號格式錯誤";break;
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
	
	<script>
		function checkForm(){
			
			
			return true;
		}
	</script>
	<div><h1>註冊</h1></div>
	<form action="OwntestRegister" method="post" onsubmit="return checkForm();">
		Account : <input name="account" /><br />
		Password : <input name="passwd" type="password" /><br />
		<input type="submit" value="Register" />
	</form>
	<div><%= mesg %></div>


	
	</body>
</html>
