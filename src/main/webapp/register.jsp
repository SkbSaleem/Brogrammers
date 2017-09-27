<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<!-- Latest compiled JavaScript -->
<script type="text/javascript"
	src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
<link rel="stylesheet" href="Styles.css">
<title>Register</title>
</head>
<body>

	<script type="text/javascript">
		$(function() {
			$('input[name="dob"]').daterangepicker({
				singleDatePicker : true,
				showDropdowns : true,
				autoUpdateInput : false,
				locale : {
					cancelLabel : "clear"
				}
			});
			$('input[name="dob"]').on('apply.daterangepicker',
					function(ev, picker) {
						$(this).val(picker.startDate.format('DD/MM/YYYY'));
					});

			$('input[name="dob"]').on('cancel.daterangepicker',
					function(ev, picker) {
						$(this).val('');
					});
		});
	</script>
	<div class="container">
		<div class="row">
			<form class="form-horizontal" method="POST"
				enctype="multipart/form-data"
				action="AuthenticationServlet?param=create">
				<fieldset>
					<legend class="legend">Create New User</legend>
					<c:if test="${exists eq true}">
						<h3 class="filtererror">That username already exists.</h3>
					</c:if>
					<div class="form-group">
						<label class="col-md-4 control-label" for="username">Username</label>
						<div class="col-md-5">
							<input id="username" type="text" name="username"
								placeholder="Username" class="form-control input-md"
								required="required"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="password">Password</label>
						<div class="col-md-5">
							<input id="password" name="password" type="password"
								placeholder="" class="form-control input-md" required="required">
							<span class="help-block">Minimum 8 characters</span>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="email">E-Mail
							Address</label>
						<div class="col-md-5">
							<input id="email" type="email" name="email" placeholder="E-mail"
								class="form-control input-md" required="required"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="firstname">First
							Name</label>
						<div class="col-md-5">
							<input id="firstname" type="text" name="firstname"
								placeholder="First Name" class="form-control input-md"
								required="required"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="lastname">Last
							Name</label>
						<div class="col-md-5">
							<input id="lastname" type="text" name="lastname"
								placeholder="Last Name" class="form-control input-md"
								required="required"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="gender">Gender</label>
						<div class="col-md-4">
							<label class="radio-inline" for="gendermale"> <input
								type="radio" name="gender" id="gendermale" value="M">
								Male
							</label> <label class="radio-inline" for="genderfemale"> <input
								type="radio" name="gender" id="genderfemale" value="F">
								Female
							</label> <label class="radio-inline" for="genderundefined"> <input
								type="radio" name="gender" id="genderundefined" value="U">
								Undefined
							</label>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="dob">Date of
							Birth</label>
						<div class="col-md-5">
							<input id="dob" type="text" name="dob"
								placeholder="Click to choose date" class="form-control input-md"
								required="required"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="civilstatus">Civil
							status</label>
						<div class="col-md-5">
							<select id="civilstatus" name="civilstatus" class="form-control">
								<option>Single</option>
								<option>Married</option>
								<option>In a relationship</option>
							</select>
						</div>
					</div>

					<br />
					<div class="form-group">
						<label class="col-md-4 control-label" for="profilepicture">Upload
							Profile Picture</label>
						<div class="col-md-5">
							<input id="profilepicture" type="file" name="profilepicture"
								class="custom-file-input" required="required"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-4 control-label" for="submit"></label>
						<div class="col-md-5">
							<button id="submit" name="submit" class="btn btn-success">Create
								User</button>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</body>
</html>