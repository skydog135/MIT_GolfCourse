<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	
<% String roundTable = (String) session.getAttribute("RoundTable");
    System.out.println("table String is: " + roundTable);
    %>	
    
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<style>

input {
	width: 100%
	height: 20px;
	border-radius: 5px;
	background-color: #d3d3d3;
	color: red;
	align-text: left;
	float: right;
}

table, tr, td {
	border-collapse: collapse;
	border: 1px solid white;
	text-align: center;
	width: 100%;
	margin-top: 3%;
}

td {
	width: 25%;
}

#buttons {
	margin-top: 5px;
	float: right;
	width: 30%;	
	padding-right: 5px;
}

a {
	font-size: 16px;
	color: red;
}

p {
	float: left;
	text-align: center;
	font-size: 30px;
	padding-left: 5px;
}

</style>
<body>
	
	<div id="round-history-header">
		<p>Rounds Played</p>
		<div id="buttons">
		<form action="new-round.jsp">
			<input type="submit" value="Done">	
		</form>
		</div>	
	</div>
	<%= roundTable %>	
</body>
</html>