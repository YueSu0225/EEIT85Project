<%@tag import="java.util.HashMap"%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ tag dynamic-attributes="product" %>
<%
	HashMap<String,String> itemTR = (HashMap<String,String>)jspContext.getAttribute("product");
	out.append("<tr>")
		.append("<td>" + itemTR.get("name") + "</td>")
		.append("<td>" + itemTR.get("price") + "</td>")
		.append("<tr>");
%>