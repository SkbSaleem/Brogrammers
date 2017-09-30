<%@page import="edu.unsw.comp9321.PostPojo"%>
<%@page import="edu.unsw.comp9321.PostData"%>
<%@ page import="java.util.*"%>
<html>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta name="viewport" content="width=device-width, initial-scale=1">

<body>
<%@ include file="header.html"%>

	<h2>Welcome ${credit.firstName } ${credit.lastName }</h2>
<c:out value="${message }"></c:out></title>




	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div>
					<img src="data:image/jpg;base64,${credit.profilePic }"
						class="avatar img-circle" alt="avatar"
						style="width: 100px; height: 100px;">
				</div>

			</div>
			<div class="content" style="overflow: auto">
				<div class="col-md-8" style="background-color: #D3D3D3">
					<form action="/UNSWBook/profileController" method="POST">
						<div class="form-group">
							<label for="comment">Post:</label>
							<textarea class="form-control" rows="3" id="comment"
								name="textareapost" style="resize: none"></textarea>
						</div>
						<div class="form-group">
							<div class="col-sm-10">
								<div class="btn-group">
									<button type="submit" class="btn btn-primary" name="btn-post"
										value="post">Post</button>
									<button type="submit" class="btn btn-primary">Upload</button>
								</div>

							</div>
						</div>
					</form>

				</div>
											<div class="col-sm-10"><h5>Header</h5></div>
				
			</div>
		</div>
</body>
</html>
