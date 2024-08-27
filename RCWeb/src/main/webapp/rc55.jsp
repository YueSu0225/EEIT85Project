<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="rc" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="iii" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<rc:tag2 />
		<hr />
		<rc:tag1 /><br />
		Lottery: <rc:lottery /><hr />
		<table width="100%" border="1">
			<tr>
				<th>Name</th>
				<th>Price</th>
			</tr>
			<rc:item name="iphone" price="3,000" />
			<rc:item name="Mouse" price="100" />
			<rc:item name="Mic" price="2,000" />
			<rc:item name="NB" price="40,000" />
			<hr />
			<iii:hello name="Rechard" />
		</table>
	</body>
</html>