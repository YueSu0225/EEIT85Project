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
<c:if test="${!empty param.delid }">
	<sql:update>
		DELETE FROM member WHERE id = ?
		<sql:param>${param.delid }</sql:param>
	</sql:update>
</c:if>	
<c:if test="${empty member.account }">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
	Welcome, ${member.name }
	<a href = "logout.jsp"><input type='submit' value='Logout' /></a>
	<a href = "addMember.jsp"><input type='submit' value='Addmember' /></a>
	<hr />
	<table border="1" width="100%">
		<tr>
			<th>ID</th>
			<th>Account</th>
			<th>Name</th>
			<th>icon</th>
			<th>AddIcon</th>
			<th>Delete</th>
			<th>Edit</th>
			
		</tr> 	
		<sql:query var="rs">
			SELECT * FROM member
		</sql:query>
		<c:forEach var="row" items="${rs.rows }" >
			<tr>
				<td>${row.id }</td>
				<td>${row.account }</td>
				<td>${row.name }</td>
				<td><img src="iconouttest?id=${row.id }" width="50" height="50" /></td>
				<td>
    			<form action="addicontest" method="post" enctype="multipart/form-data">
        				<input type="file" name="icon" />
        				<input type="hidden" name="id" value="${row.id }" />     				
        				<input type="submit" value="AddIcon" />      				
   					</form>
				</td>
				<td><a href="?delid=${row.id }" onclick="return isDel('${row.name}');">Delete</a></td>
				<td><a href="editMember.jsp?editid=${row.id }" >Edit</a></td>
				
				
			</tr>
		</c:forEach>
		
		
	</table>
	
	
	
	
	</body>
	<script>
		function isDel(who){
			var isDel = confirm("是否刪除 *" + who + "* ?");
			return isDel;
		}
	</script>
</html>