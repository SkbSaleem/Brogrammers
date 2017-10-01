<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
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
<link rel="stylesheet" href="Styles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
</head>
<body>
<div class="container">
		<div class="row">
			<form class="form-horizontal" method="post"
				action="AuthenticationServlet?param=admin">
				<fieldset>
					<legend class="legend">Admin Log In</legend>
			<c:if test="${param.unauthorized eq true}">
						<h3 class="filtererror" style="color:red;">You are not authorized to view that page. Please sign in with a valid user or confirm your e-mail address.</h3>
					</c:if>
					<c:if test="${param.loginfailed ne null}">
						<h3 class="filtererror" style="color:red;">Your username or password is incorrect.</h3>
					</c:if>
					<div class="form-group">
						<label class="col-md-4 control-label" for="username">Username</label>
						<div class="col-md-5">
							<input type="text" id="username" name="username" placeholder="Username"
								class="form-control input-md" required="required" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="password">Password</label>
						<div class="col-md-5">
							<input type="password" id="password" name="password" placeholder="Password"
								class="form-control input-md" required="required" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="submit"></label>
						<div class="col-md-5>">
							<button id="submit" type="submit" class="btn btn-warning btn-sm">Sign
								In</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>