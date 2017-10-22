<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<title>Insert title here</title>
</head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="Styles.css">
<%@ include file="header.html"%>
<body style="background-color: white;">
	<center>
		<h2>Search Results</h2>
	</center>


	<center>
		<form action="/UNSWBook/FriendRequest" method="POST">
			<table id="table1" border="0.5" style="height: 50%; width: 30%"
				class="table table-hover">


					<thead class="thead-inverse">
						<tr>
							<th><center>
									<b>First Name</b>
								</center></th>
								<th><center>
									<b>Last Name</b>
								</center></th>
								<th><center>
									<b>Username</b>
								</center></th>
								<th><center>
									<b>Date of Birth</b>
								</center></th>
							<th><center>
									<b>Send Friend Request</b>
								</center></th>
						</tr>

					</thead>
					<tbody>
						<c:forEach var="item" items="${result}">
							<tr>
								<td>${item.firstName}</td>
								<td>${item.lastName}</td>
								<td>${item.userName}</td>
								<td>${item.DOB}</td>
								<td><center>
										<input type="hidden" name="token"
											value=<c:out value="${token}"/>>
										<!-- <input type="hidden" name="userTo" value="${item.userName}"> -->
											
										<button type="submit" name="userTo" value="${item.userName},${item.url}" 
										class="btn btn-primary">Send
											Friend Request</button>
									</center></td>
							</tr>
						</c:forEach>
					</tbody>
			</table>
		</form>
	</center>



</body>
</html>