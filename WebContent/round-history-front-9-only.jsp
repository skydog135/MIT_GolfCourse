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
	<div id="round-history-header">
		<p>Round Summary 9 Holes</p>

	</div>

	<%= table %>
	<div id="comments-section">

	<div id="buttons">
	
	<button type="button" value="Back" onclick="history.back()">Back</button>
	
	</div>
	</div>
	
	
</body>
</html>