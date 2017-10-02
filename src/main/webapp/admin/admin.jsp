<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="AdminCSS.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
<script src="https://www.w3schools.com/lib/w3.js"></script>
<script type="text/javascript">
jQuery(document).ready(function($) {
    $(".item").click(function() {
        window.location = $(this).data("href");
        $('form[name=form]').submit();
    });
});

</script>>

<div class="container">
		<div class="row">
			<form method="post" name="form"
				action="/UNSWBook/AuthenticationServlet?param=log">
				<input type="text" id ="search" class="form-control" oninput="w3.filterHTML('#table', '.item', this.value)" placeholder="Search for names..">
				
				<h2>List of users</h2>
				<table id="table">
				<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Username</th>
				<th>Bitton</th>
				</tr>
				<c:forEach var="user" items="${users}">
				<tr class="item">
				<td >${user.firstName}</td>
				<td >${user.lastName}</td>
				<td >${user.userName}</td>
<%-- 				<td><button type="submit" value="${user.userName}" class="btn btn-primary">Button</button></td> --%>
				
				</tr>
				</c:forEach>
				</table>
			</form>
		</div>
	</div>
</body>
</html>