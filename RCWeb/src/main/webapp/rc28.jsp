<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name1 = "RC";
	pageContext.setAttribute("name11", name1);
	
	String name2 = "Eric";
	request.setAttribute("name11", name2);
	//session 若沒摧毀,他會一直存在著,常用於登入器用於登入狀態
	//由於session比較先執行,所以在執行過session後,不會出現application,前提是沒有request&pageContext

	String name3 = "Vivan";
	session.setAttribute("name11", name3);
	
	
	String name4 = "Joe";
	application.setAttribute("name11", name4);
	
%>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	<!-- 使用EL呼叫JAVA變數,變數重複,以離自己近為優先 -->
	<!-- JAVA將變數放進去setAttribute裡,EC就可以呼叫物件來做使用 -->
	<!-- 近~~遠 pageContext ~ request ~ session ~ application -->
	<!-- xxxxxScope為存活區域,就可以呼叫到此範圍 -->
	<!--Name1: ${pageScope.name11 }<br /> 
	Name2: ${requestScope.name11 }<br />
	Name3: ${sessionScope.name11 }<br />
	Name4: ${applicationScope.name11 }<br /> -->
		Name1: ${name11 }<br />
	Name2: ${name11 }<br />
	Name3: ${name11 }<br />
	Name4: ${name11 }<br />
	</body>
</html>