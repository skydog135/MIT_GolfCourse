<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>

<style>
	#new-user-form {
		margin-top: 10%;
	}

</style>

<body>
	<div id="profile-edit-toolbar">
		<div id="profile-edit-content">
			<a href="edit-profile.jsp">Change<br>Profile</a>
		</div>
		<div id="profile-edit-content">
			<a href="index.jsp"><br>Logout</a>
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
	<!--UPDATED CONTENT:  Julie Jewell 4/30/16 reactivated History Button-->
	<form action="History" method="post">
		<div id="buttons">
		<input type="submit" value="Round History">
		</div>
		<br>
	</form>
	</div>
	<!--END UPDATED CONTENT-->

</body>
</html>
