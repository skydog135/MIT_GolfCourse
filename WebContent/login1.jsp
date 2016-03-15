<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="theme.css">
</head>
<body>
	<header>
		<img src="http://www.golfcourse.uga.edu/sites/golfcourse.uga.edu/files/2013%20UGA%20Golf%20logo.png" />
	</header>
	<!--UPDATE CONTENT-->
	<div id="new-user-form">
	<form action="LoginController" method="post">
  		<p>Username:
  		<input type="text" name="username"></p>
  		<p>Password:
  		<input type="password" name="password"></p>
		<div id="buttons">
		<input type="submit" value="Log In">
		</div>
	</form>
	<form action="index.jsp" method="post">
		<div id="buttons">
			<input type="button" value="Back">
		</div>
	
	</form> 
	</div>
	
	<a href="forgot-password.html">Forgot Password?</a>
	<!--END UPDATED CONTENT-->
</body>
</html>