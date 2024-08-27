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
<sql:query var="rs">
	SELECT * FROM orderdetails od
	JOIN orders o ON (od.OrederID = o.OrderID)
	JOIN products p ON (od.ProductID = p.ProductID)
	WHERE od.OrderID = ?
	<sql:param>${param.orderID }</sql:param>
</sql:query>
${RCUtils.order2JSON(rs.rows) }

    