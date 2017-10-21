<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
<h1>Graph Table</h1>
<table class="table table-hover">
<tr class="warning">
<th>Subject</th>
<th>Predicate</th>
<th>Object</th>
</tr>
<c:forEach var="l" items="${list}">
<tr>
<td class="success">${l.subject}</td>
<td class="info">${l.predicate}</td>
<td class="danger">${l.object}</td>
</tr>
</c:forEach>


</table>

</body>
</html>