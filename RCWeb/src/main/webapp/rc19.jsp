I am RC19<hr />
Lottery:<%= (int)(Math.random()*49+1) %><br />
<%
	int x = Integer.parseInt(request.getParameter("x")) ;
	int y = Integer.parseInt(request.getParameter("y")) ;
	out.print(String.format("%d + %d = %d", x, y, x+y));
%>