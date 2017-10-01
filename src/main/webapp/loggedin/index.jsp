<html>
<%@page import="edu.unsw.comp9321.PostPojo"%>
<%@page import="edu.unsw.comp9321.PostData"%>
<%@ page import="java.util.*"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.html"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div>
					<img src="data:image/jpg;base64,${convertedProfilepic }"
						class="avatar img-circle" alt="avatar"
						style="width: 100px; height: 100px;">
					<h2 style="align-content: left;">${credit.firstName }
						${credit.lastName }</h2>
				</div>
			</div>
			<div class="content" style="overflow: auto">
				<div class="col-md-8" style="background-color: #f2f2f2">
					<form action="/UNSWBook/profileController"
						enctype="multipart/form-data" method="POST">
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
								</div>
								<div class="btn-group">
									<input type="file" name="image"
										class="btn btn-primary float-right">
								</div>
							</div>
						</div>
					</form>

				</div>

			</div>
		</div>
		<div class="row">
			<div class="col-md-12">

				<c:if test="${not empty plist}">

					<c:forEach var="array" items="${plist}">
						<form action="/UNSWBook/profileController" method="post">

							<c:set var="pid" scope="session" value="${array.postid}" />
							<div class="panel panel-success">
								<div class="panel-heading">
									<c:out value="${array.timeposted}" />
								</div>
								<div class="panel-body">
									<c:choose>
										<c:when test="${not empty array.image}">
											<figure class="figure">
												<img src="data:image/jpg;base64,${array.image}"
													class="figure-img img-fluid rounded center-block"
													style="height: 80%; width: auto;">
												<figcaption class="figure-caption text-center">${array.content}</figcaption>
											</figure>

										</c:when>
										<c:otherwise>
											<input class="form-control" type="text" name="bodyContent"
												value="<c:out value="${array.content}" />" disabled
												style="cursor: default">
										</c:otherwise>
									</c:choose>
								</div>
								<div class="panel-footer">
									<button type="submit" class="btn btn-danger btn-xs"
										name="btn-deletepost" value="<c:out value = "${pid}"/>">
										Delete</button>

									<button type="submit" class="btn btn-danger btn-xs"
										name="btn-editpost" value="<c:out value = "${pid}"/>">Edit</button>
									<span style="float: right"
										class="glyphicon glyphicon-thumbs-up"> <c:out
											value="${array.likes}" /></span> <input type="hidden"
										name="content" value="${array.content}">
								</div>
							</div>
						</form>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>
