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
	<div class="container">
		<div class="row">
			<form method="post" action="/UNSWBook/AdminServlet">
				<input class="form-control" type="text" name="input"
					oninput="w3.filterHTML('#id01', '.item', this.value)"
					placeholder="Search For User">
				<h2>List of users</h2>
				<table id="#id01" class="table">
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Username</th>
						<th>Ban Status</th>
						<th>Ban</th>
						<th>Log</th>
					</tr>
					<c:forEach var="user" items="${users}">
						<tr class="item">
							<td>${user.firstName}</td>
							<td>${user.lastName}</td>
							<td>${user.userName}</td>
							<td>${user.banned}</td>
							<td><button type="submit" class="btn btn-danger btn-xs"
									name="ban" value="${user.userName}">Ban User</button></td>
							<td><button type="submit" class="btn btn-warning btn-xs"
									name="log" value="${user.userName}">Get User Activity</button></td>
						</tr>
					</c:forEach>
				</table>
				<button type="submit" name="logout"
					class="btn btn-danger glyphicon glyphicon-off"
					style="font-size: 100px; height: 200px; width: 100%">LOG
					OUT</button>
			</form>
		</div>
	</div>
</body>
</html>