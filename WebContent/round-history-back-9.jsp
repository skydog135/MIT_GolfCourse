<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	
    <% String table = (String) session.getAttribute("B9Table");
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
	<div id="round-history-header">
		<p>Round Summary Back 9</p>
	</div>
	
	<%= table %>

	<a href="round-history-front-9.jsp"><span style="color:grey">Front 9</span></a> | Back 9
	<div id="comments-section">
		<div id="buttons">
		<button type="button" value="Back" onclick="history.back()">Back</button>
		</div>
	</div>
		
</body>
</html>