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
	<!-- Updated Content -->
	<h1>New User</h1>
	<div id="current-user">
		<p>Already have an account?</p>
		
		<input type="submit" value="login">
			
	</div>
	<div id="new-user-form">
	<form action="AddUser" method="post">
  		<p>First Name:
		<input type="text" name="firstName"></p>
  		<p>Last Name:
  		<input type="text" name="lastName"></p>
		<p>Gender:
  		<select id="gender" name="gender">
			<option>Male</option>
			<option>Female</option>
		</select></p>
		<p>Email:
  		<input type="text" name="email"></p>
		<p>Handicap:
  		<input type="text" name="handicap"></p>
		<p>Password:
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
	<!-- End Updated Content -->
	</div>

	
	
</body>
</html>