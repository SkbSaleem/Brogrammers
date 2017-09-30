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
<link rel="stylesheet" href="../Styles.css">
<title>UNSWBook</title>
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
			<!-- left column -->
			<form id="fileupload" enctype="multipart/form-data" action="/UNSWBook/ProfileServlet?param=newprofilepic" method="post">
			<div class="col-md-3">
				<div class="text-center">
					<img src="data:image/jpg;base64,${convertedProfilepic }" class="avatar img-circle"
						style="width: 100px; height: 100px;" alt="avatar">
					<h6>Upload a different photo...</h6>
					<input type="file" name="upload" onchange="this.form.submit();" class="form-control">
				</div>
			</div>
			</form>
			
			<form class="form-horizontal" action="/UNSWBook/ProfileServlet?param=edit" method="post">
				<fieldset>
					<h1 class="filtererror" style="font-size: 25px;">Edit Profile</h1>
					
					<div class="form-group">
						<label class="col-lg-3 control-label">Password:</label>
						<div class="col-lg-8">
							<input class="form-control" type="password" name="password"
								placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-lg-3 control-label">First name:</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="firstname"
								placeholder="${credit.firstName }">
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">Last name:</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="lastname"
								placeholder="${credit.lastName}">
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input class="form-control" type="text" name="mail"
								placeholder="${credit.email }">
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">Gender:</label>
						<div class="col-lg-8">
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
						<label class="col-lg-3 control-label">Date of Birth:</label>
						<div class="col-lg-8">
							<input id="dob" type="text" name="dob"
								placeholder="Click to choose date" class="form-control input-md"><br />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label" for="civilstatus">Civil
							status</label>
						<div class="col-lg-8">
							<select id="civilstatus" name="civilstatus" class="form-control">
								<option disabled selected="selected">Choose new civil status</option>
								<option>Single</option>
								<option>Married</option>
								<option>In a relationship</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-warning" value="Save Changes">
							<span></span> <input type="reset" class="btn btn-default"
								value="Cancel">
						</div>
					</div>
				</fieldset>
				</form>
		</div>
	</div>
	<hr>
</body>
</html>