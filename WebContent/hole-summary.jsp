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
		<p>Congratulations You Shot a ${currentShotNumber}</p>
<!-- 	<td>Putts</td> -->
		
<!--    <td>3</td> -->
	
	<form action="HoleWrapUp">
	<div id="comments-section">
		<p>Additional Penalty Stroke(s):
  		<select id="hole-penalty-strokes" name="hole-penalty-strokes">
			<option>0</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
			<option>4</option>
			<option>5</option>
		</select></p>
		<br />
	
	
	<p>Comments:</p>
	<div id="comments">
  		<input type="text" name="comments">
	</div>
	<div id="buttons" style="width: 100%">
		<input type="submit" value="Next Hole">	
	</div>
	</div>
	</form> 
	
	
</body>
</html>