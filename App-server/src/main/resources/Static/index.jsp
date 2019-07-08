<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Statistics</title>
</head>
<body>

	<h3>Statistics List</h3>
	<table border="1" cellpadding="2" cellspacing="2">
		<tr>
			<th>statsID</th>
			<th>statsTime</th>
			<th>fk_branch_id</th>
			
		</tr>
		<c:forEach var="statistics" items="${statistics}">
			<tr>
				<td>${statistics.statsID }</td>
				<td>${statistics.statsTime }</td>
				<td>${statistics.fk_branch_id }</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>