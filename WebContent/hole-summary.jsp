<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<style>
body {
	margin-top: 10%;
}
input {
	width: 100%
	height: 20px;
	border-radius: 5px;
	background-color: #d3d3d3;
	color: red;
	align-text: left;	
}
</style>
<body>
	<h1>Hole Summary</h1>
	<table>
		<tr>
			<td>Shots</td>
			<td>Putts</td>
		</tr>
		<tr>
			<td>3</td>
			<td>2</td>
		</tr>
	</table>
	<br>
	<div id="comments-section">
	Comments:
	<div id="comments">
  		<input type="text" name="comments">
	</div>
	<div id="buttons" style="width: 100%">
		<input type="submit" value="Next Hole">	
	</div>
	</div>
	
	
</body>
</html>