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
	<div id="profile-edit-toolbar">
		<div id="profile-edit-content">
			<a href="edit-password.jsp">Change<br>Password</a>
		</div>
		<div id="profile-edit-content">
			<a href="edit-profile.jsp">Change<br>Profile</a>
		</div>
		<div id="profile-edit-content">
			<a href=""><br>Logout</a>
		</div>
	</div>
	<br>
	<br>
	<header>
		<img src="http://www.golfcourse.uga.edu/sites/golfcourse.uga.edu/files/2013%20UGA%20Golf%20logo.png" />
	</header>
	<!--UPDATED CONTENT-->
	<div id="new-user-form">
	<!--UPDATED CONTENT:  Julie Jewell 3/9/16 added actions for New Game and div closure-->
	<form action="NewGame" method="post">
		<div id="buttons">
		<input type="submit" value="New Round">
		</div>
		<br>
	</form>
	<!--UPDATED CONTENT:  Julie Jewell 3/9/16 Commenting out History button until we are ready to code-->
	<!--<form action="History" method="post"></form>-->
		<!--<br>-->
		<!--<input type="submit" value="Round History">-->
		<!--</div>-->
	<!--</form> -->
	</div>
	<!--END UPDATED CONTENT-->

</body>
</html>
