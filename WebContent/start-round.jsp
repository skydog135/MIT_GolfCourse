<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>

<style>



</style>

<body>
	<header>
		<img src="http://www.golfcourse.uga.edu/sites/golfcourse.uga.edu/files/2013%20UGA%20Golf%20logo.png" />
	</header>
	<div id="new-user-form">
	<!--UPDATE CONTENT -->
	<!--UPDATE CONTENT --Julie Jewell add the "name" parameter to the select statements -->
	<form action ="StartRound" method="post" >
		<p>Select Number of Holes:
  		<select id="hole-amount" name = "hole-amount">
			<option>9</option>
			<option>18</option>
		</select></p>
		<!--UPDATE CONTENT --Julie Jewell updated the select id to "tee-box"from "tee-box-id" -->

		<p>Select Tee Box:
  		<select id="tee-box" name = "tee-box">
			<option>Bulldog</option>
			<option>Black</option>
			<option>Red</option>
			<option>White</option>
		</select></p>
		<p>Starting Hole:
  		<select id="starting-hole" name = "starting-hole">
			<option>1</option>
			<option>10</option>
		</select></p>
		<div id="buttons">
			<input type="submit" value="Start Round!">
		</div>
		</form>
		</div>
 
	<!-- END UPDATED CONTENT -->
	<br>
	<br>

	
	
</body>
</html>