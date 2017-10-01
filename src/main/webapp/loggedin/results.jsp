<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<title>Insert title here</title>
<%@page import="edu.unsw.comp9321.SearchServlet"%>  
</head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="Styles.css">

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">UNSWBook</a>
    </div>
    <ul class="nav navbar-nav">
      <li> <a href="#">Profile</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="/UNSWBook/AuthenticationServlet?param=logout">Sign Out</a></li>
    </ul>
    <form class="navbar-form " align="center" action = "/UNSWBook/SearchServlet" method = "POST" >
      <div class="form-group">
        <input type="text" class="form-control" placeholder="search" name= "search">
      </div>
      <button type="submit" class="btn btn-default">Search</button>
     
      
    </form>
  </div>
</nav>
<body style="background-color:white;">
<center><h2> Search Results </h2> </center>
 
 
 <center>
  <form action = "/UNSWBook/FriendRequest" method = "POST" > 
 <table id="table1" border="0.5" style="height:50%;width:30%" class="table table-hover"><h2>
 

<thead class="thead-inverse">
   <tr>
      <th><center><b>Users</b></center></th>
      <th><center><b>Send Friend Request</b></center></th>
    </tr>

    </thead>
    <tbody>
    
   
	<%@page import="java.util.ArrayList" %>
<%
ArrayList<String> rlist = (ArrayList<String>)request.getAttribute("test");

%>
 <%  for(int i = 0; i < rlist.size(); i = i+2) { %>
 
         <% String option = (String)rlist.get(i) + " " + (String)rlist.get(i+1) ; %>
       
         <tr>
         
         
      <td> <center>  <%= option %> </center> </td>
          
       <td> <center>  <input type="hidden" name="token" value= <c:out value="${token}"/>><button type="submit" class="btn btn-primary">Send Friend Request</button></center></td>
          </tr>
          
           <% ;} %>
           
           
          
           </tbody>
           </h2>
           
        
</table>
  </form>
</center>



</body>
</html>