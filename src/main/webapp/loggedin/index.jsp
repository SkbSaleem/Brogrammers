<html>
<%@page isELIgnored="false"%>
<%@ include file="header.html"%>
<meta name="viewport" content="width=device-width, initial-scale=1">

<body>
	<h2>Welcome ${credit.firstName } ${credit.lastName }</h2>


	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div>
				<img src="data:image/jpg;base64,${credit.profilePic }"
					class="avatar img-circle" alt="avatar"
					style="width: 100px; height: 100px;"></div>
					<div>  
					<a href="#" class="btn btn-info" role="button">Link Button</a>
					</div>
			</div>
			<div class="content" style="overflow: auto">
				<div class="col-md-8" style="background-color: #D3D3D3">
					<form action="#" method="POST">
						<div class="form-group">
							<label for="comment">Post:</label>
							<textarea class="form-control" rows="3" id="comment"></textarea>
						</div>
						<div class="form-group">
							<div class="col-sm-10">
								<div class="btn-group">
  <button type="submit" class="btn btn-primary">Post</button>
  <button type="submit" class="btn btn-primary">Upload</button>
</div>
									
							</div>
						</div>
					</form>
					<div class="col-md-12">
						<div class="posts">
							<div class="panel-group">
								<div class="panel panel-default">
									<div class="panel-heading">Panel with panel-default class</div>
									<div class="panel-body">Panel Content</div>
								</div>
							</div>


						</div>
					</div>
				</div>
			</div>
		</div>
</body>
</html>
