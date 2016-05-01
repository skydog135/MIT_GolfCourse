<%@ page import="model.User, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<%
User user = (User) session.getAttribute("user");

String gender = (user.getGender());
System.out.println(gender);
String email = (user.getEmail());
System.out.println(email);
String firstName = (user.getFirstName());
String lastName = (user.getLastName());
String golferHandicapIndex = Float.toString((user.getGolferHandicapIndex()));

String oppositeGender = "";

if (gender.equals("Male")) {
	oppositeGender = "Female";
	System.out.println("The opposite gender is Female");
}else{
	oppositeGender = "Male";
	System.out.println("The opposit gender is Male");
}


%>
<style>



</style>
<body>
	<!-- UPDATED CONTENT added <p> tags, and took out all of the <br> tags -->
	<h1>Edit Profile</h1>
	<div id="new-user-form">
	<form action="UpdateUser" method="post">
	<p>Email address associated with account:
  		<input type="text" name="email" value="<%= email %>"></p>
  		<p>Updated Email:
		<input type="text" name="newEmail" value="<%= email %>"></p>
  		<p>Updated First Name:
		<input type="text" name="firstName" value="<%= firstName %>"></p>
  		<p>Updated Last Name:
  		<input type="text" name="lastName" value="<%= lastName %>"></p>
		<p>Updated Gender:
  		<select id="gender" name="gender">
			<option><%= gender %></option>
			<option><%= oppositeGender %></option>
		</select></p>
		<p>Updated Handicap:
  		<input type="text" name="handicap" value="<%= golferHandicapIndex %>"></p>
		<p>Updated Password *:
  		<input type="text" name="password" required></p>
		<p>Password<br>Confirmation *:
  		<input type="text" name="password" required></p>
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