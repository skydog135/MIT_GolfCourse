<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<style>
	a {
		margin-top: 10px;
	}

</style>
<body>
	<header>
		<img src="http://www.golfcourse.uga.edu/sites/golfcourse.uga.edu/files/2013%20UGA%20Golf%20logo.png" />
	</header>
	<!-- Updated Content -->
	<div id="new-user-form">
	<form action="LoginController" method="post">
  		<p>Email:
		<input type="text" name="email"></p>
  		<p>Password:
  		<input type="password" name="password"></p>
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
	<a href="forgot-password.html">Forgot Password?</a>
	<!-- End Updated Content -->
	</div>

	
	
</body>
</html>