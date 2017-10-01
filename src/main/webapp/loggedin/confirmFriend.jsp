<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="Styles.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
BLA BLA

<form action = "/UNSWBook/AcceptFriendServlet" method = "POST" > 

Do you want to accept the friend request from <c:out value="${param.token}"/>? 

<input type="hidden" name="request_user" value=<c:out value="${param.token}"/>>
<button type="submit" class="btn btn-primary">Accept</button> 

</form>

</body>
</html>