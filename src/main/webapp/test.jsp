<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script src="https://www.w3schools.com/lib/w3.js"></script>

<input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search for names..">


<table id="id01" border="1">

  <tr>
    <th>Content</th>
  </tr>
    <c:forEach var="item" items="${plist}">
  
  <tr class="item">
  
    <td>${item[0]}</td>
    <td>${item[1]}</td>

  </tr>
  </c:forEach>
  
 </table>
</body>
</html>
