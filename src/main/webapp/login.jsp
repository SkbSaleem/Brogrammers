<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="Styles.css">
<title>Login</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<form class="form-horizontal" method="post" action="AuthenticationServlet?param=login">
				<fieldset>
					<legend class="legend">Log In</legend>
					<div class="form-group">
						<div class="col-md-5">
							<input type="text" name="username" placeholder="Username" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-5">
							<input type="password" name="password" placeholder="Password" />
						</div>
					</div>
					<div class="form-group">
					<div class="col-md-5>">
					<input type="submit" value="Sign In" class="btn btn-warning btn-sm" />
					</div>
					</div>
				</fieldset>
			</form>
			
		</div>
	</div>
	<form class="form-horizontal">
			<div class="remember-forgot" style="display: none;">
						<div class="row">
							<div class="col-md-6">
								<div class="checkbox">
									<label> <input type="checkbox" /> Remember Me
									</label>
								</div>
							</div>
							<div class="col-md-6 forgot-pass-content" style="display: none;">
								<a href="javascription:void(0)" class="forgot-pass">Forgot
									Password</a>
							</div>
						</div>
					</div>
			</form>
			<div class="col-md-12">
				<div class="pr-wrap">
					<div class="pass-reset">
						<label> Enter the email you signed up with</label> <input
							type="email" placeholder="Email" /> <input type="submit"
							value="Submit" class="pass-reset-submit btn btn-success btn-sm" />
					</div>
				</div>
			</div>
</body>
</html>