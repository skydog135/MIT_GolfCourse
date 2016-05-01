<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	
    <% String table = (String) session.getAttribute("HistoryRoundSummary");
    System.out.println("table String is: " + table);
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
	border: 1px solid #d3d3d3;
	text-align: left;
	width: 100%;
	margin-top: 3%;
}

td {
	width: 33%;
}

#buttons {
	margin-top: 5px;
	float: right;
	width: 30%;
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
		<p>Round Summary</p>
	<div id="buttons">
					<button type="button" value="Back" onclick="history.back()">Back</button>
				</div>	
	</div>
	<table style="height: 5%;">
		<tr>
			<td style="width: 100%; text-align: right; padding-right: 3%">Round Date: ${RoundDate}</td>
		</tr>
	</table>
	<hr>
	<br>
	<div id="round-summary-details">
	<%= table %>
	</div>
	<br>
	<div id="comments-section">
	Comments
	<div id="comments">
  		${RoundComments}
	</div>
	<div id="buttons" style="width: 100%">
		<form action="RoundHoleHistory">
			<input type="submit" value="Hole Details">	
		</form>
	</div>
	</div>
	
	
</body>
</html>