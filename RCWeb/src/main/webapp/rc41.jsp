<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="tw.rc.apis.*" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>    
<sql:setDataSource
	driver="com.mysql.cj.jdbc.Driver"
	url="jdbc:mysql://localhost:3306/iii"	
	user="root"
	password="root"
	/>
<sql:update>
	DELETE FROM food
</sql:update>
<sql:update>
	ALTER TABLE food AUTO_INCREMENT = 1
</sql:update>
<c:import url="https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelFood.aspx" var="data"></c:import>

<c:set var="alldata" value="${RCUtils.parseFood(data) }"></c:set>
<c:forEach var="row" items="${alldata }" >
${row.aid }:
	<c:catch var="err">
		<sql:update>
			INSERT INTO food (name,city,town,picurl,lat,lng) VALUES (?,?,?,?,?,?)
			<sql:param>${row.aname }</sql:param>
			<sql:param>${row.acity }</sql:param>
			<sql:param>${row.atown }</sql:param>
			<sql:param>${row.apicurl }</sql:param>
			<sql:param>${row.alat }</sql:param>
			<sql:param>${row.alng }</sql:param>
		</sql:update>
		ok<br />
	</c:catch>
	${err }<br />
</c:forEach>
