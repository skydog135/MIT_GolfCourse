<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>

<script>

</script>

<style>




</style>
<body>
	<!-- Updated Content -->
	<h1>Record Details</h1>
	<div id="new-user-form">
	<form action="Hole2ShotDetail">
		<p>Select Club:
  		<select id="club" name="club">
			<option>Driver</option>
			<option>3 Wood</option>
			<option>5 Wood</option>
			<option>7 Wood</option>
			<option>3 Hybrid</option>
			<option>5 Hybrid</option>
			<option>7 Hybrid</option>
			<option>1 Iron</option>
			<option>2 Iron</option>
            <option>3 Iron</option>
            <option>4 Iron</option>
            <option>5 Iron</option>
            <option>6 Iron</option>    
            <option>7 Iron</option>
            <option>8 Iron</option>
            <option>9 Iron</option>  
			<option>1 Iron</option>
			<option>Pitching Wedge</option>
            <option>Gap Wedge</option>
            <option>Sand Wedge</option>
            <option>Lob Wedge</option>
            <option>Putter</option>
		</select></p>
		<p>Select Lie:
  		<select id="lie" name="lie">
			<option>Tee Box</option>
			<option>Fairway</option>
			<option>Rough</option>
			<option>Woods</option>
			<option>Water</option>
			<option>Sand</option>
			<option>Fringe</option>
			<option>Green</option>
		</select></p>
	<!-- JAJ made penalty strokes a selection box -->
		<p>Add Penalty Stroke(s):
  		<select id="penalty-strokes" name="penalty-strokes">
			<option>0</option>
			<option>1</option>
			<option>2</option>
			<option>3</option>
		</select></p>
	
	<!-- JAJ removed the add penalty stroke button -->	
	<!-- <div id="buttons"> -->	
	<!-- <br> -->	
	<!-- <br> -->	
	<!-- <input type="submit" value="Add Penalty Stroke"> -->	
		<br>
		<br>
	<!-- JAJ changed wording from "Next Shot" to submit" -->
		<div id="buttons">
		<input type="submit" value="Submit">
		</div>	
		</div>
	</form> 
	<!-- End Updated Content -->
	</div>

	
	
</body>
</html>