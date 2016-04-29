<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	
    <% String table = (String) session.getAttribute("shotDetailTable");
    System.out.println("table String is: " + table);
    %>	
	
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
	<script>
	function goBack(){
		window.history.back()
	}
	
	</script>
</head>
<style>

tr, td {
	width: 20%;
}


</style>
<body>
	<h1>Hole ${holeNum}</h1>
	<h4>Summary </h4>
	<%= table %>
	<div id="comments-section">
	${holeCom}
	<div id="buttons">
	
	<button onclick="goBack()">Back</button>
	
	</div>
	</div>
	
	
</body>
</html>