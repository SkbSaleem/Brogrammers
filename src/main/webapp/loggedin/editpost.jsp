<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="header.html"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>Edit Post</title>
</head>
<body>
<h1>Edit your post</h1>
<div class="container">
  <div class="row">
<form action="/UNSWBook/profileController" method ="POST">
    <div class="col-sm-4 col-sm-offset-4">

<div class="form-group">
      <h2 class="text-center">Edit Content</h2>
  <textarea class="form-control" rows="3" id="comment"
								name="editedpost" style="resize: none">${content}</textarea>
  <button type="submit" name="btn-submitchanges" value="${param.postID}" class="btn btn-primary btn btn-sm">Submit</button>
</div>
</div>
</form>
</div></div>



</body>
</html>