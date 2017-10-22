<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.html"%>
<%@page isELIgnored="false"%>



<!DOCTYPE html">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="Styles.css">


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wall</title>
</head>
<body style="background-color: white;">
	<center>
		<h2>UNSW Community Timeline</h2>
	</center>



	<div class="row">
		<div class="col-md-4 col-md-offset-4">

			<c:if test="${not empty posts}">

				<c:forEach var="array" items="${posts}">
					<form action="/WallServlet" method="post">
						<c:set var="pid" scope="session" value="${array.timeposted}" />
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
										<c:out value="${array.username}" />
								&nbsp; wrote: <br>
										<input class="form-control" type="text" name="bodyContent"
											value="<c:out value="${array.content}" />" disabled
											style="cursor: default">
									</c:otherwise>
								</c:choose>
								<input type="hidden" name="post_id"
									value="<c:out value="${array.postid}" />">
								<div class="panel-footer">
									<button name="likes" type="submit" class="btn btn-primary"
										value="<c:out value="${array.likes}" />">Like!</button>
									<span style="float: right"
										class="glyphicon glyphicon-thumbs-up"> <c:out
											value="${array.likes}" />
									</span>
								</div>
							</div>
						</div>
					</form>
				</c:forEach>
			</c:if>
		</div>
	</div>

</body>
</html>