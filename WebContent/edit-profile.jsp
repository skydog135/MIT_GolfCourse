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
	<!-- UPDATED CONTENT added <p> tags, and took out all of the <br> tags -->
	<h1>Edit Profile</h1>
	<div id="new-user-form">
	<form>
  		<p>First Name:
		<input type="text" name="firstname"></p>
  		<p>Last Name:
  		<input type="text" name="lastname"></p>
		<p>Gender:
  		<select id="gender">
			<option>Male</option>
			<option>Female</option>
		</select></p>
		<p>Email:
  		<input type="text" name="email"></p>
		<p>Handicap:
  		<input type="text" name="handicap"></p>
		<div id="buttons">
		<input type="submit" value="Log In">
		<input type="submit" value="Back">
		</div>
	</form> 
	</div>
	<!-- END UPDATED CONTENT -->

	
	
</body>
</html>