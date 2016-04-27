<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	
    <% String table = (String) session.getAttribute("9OnlyTable");
    System.out.println("table String is: " + table);
    %>
    

<html lang='en'>


<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>

<style>

tr, td {
	width: 20%;
}

</style>

<body>
	<%= table %>
	<br />
	Front 9 | <a href="round-summary-back-9.jsp"><span style="color:grey">Back 9</span></a>
	<div id="comments-section">
	<div id="comments"> 
		<h4>Comments</h4>
		<input type="text"></input><br>
	</div>
	<br>
	<div id="buttons">
		<input type="submit" value="Done"></input>
	</div>
	</div>
	
	
</body>
</html>