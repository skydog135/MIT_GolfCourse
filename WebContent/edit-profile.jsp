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
	<form action="UpdateUser" method="post">
	<p>Email address associated with account:
  		<input type="text" name="email"></p>
  		<p>Updated First Name:
		<input type="text" name="firstName"></p>
  		<p>Updated Last Name:
  		<input type="text" name="lastName"></p>
		<p>Updated Gender:
  		<select id="gender" name="gender">
			<option>Male</option>
			<option>Female</option>
		</select></p>
		<p>Updated Handicap:
  		<input type="text" name="handicap"></p>
		<p>Updated Password:
  		<input type="text" name="password"></p>
		<p>Password<br>Confirmation:
  		<input type="text" name="password"></p>
		<div id="buttons">
		<br>
		<br>
		<input type="submit" value="Log In">
		</div>
	</form>
	<form action="index.jsp">
		<div id="buttons">
		<input type="submit" value="Back">
		</div>
	</form> 
	</div>
	<!-- END UPDATED CONTENT -->

	
	
</body>
</html>