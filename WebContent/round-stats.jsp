<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<style>

input {
	width: 100%
	height: 20px;
	border-radius: 5px;
	background-color: #d3d3d3;
	color: red;
	align-text: left;
	float: right;
}

table, tr, td {
	border-collapse: collapse;
	border: 1px solid white;
	text-align: left;
	width: 100%;
	margin-top: 3%;
}

td {
	width: 50%;
}

#buttons {
	margin-top: 5px;
	float: right;
	width: 30%;
}

a {
	font-size: 16px;
	color: red;
}

p {
	float: left;
	text-align: center;
	font-size: 30px;
	padding-left: 5px;
}

#round-summary-details {
	background-color: white;
}

</style>
<body>
	
	<div id="round-history-header">
		<p>Round Stats</p>
		<div id="buttons">
			<input type="submit" value="Done">	
		</div>	
	</div>
	<table style="height: 5%;">
		<tr>
			<td style="width: 100%; text-align: right; padding-right: 3%">Round Date <a href="">11-02-2015</a></td>
		</tr>
	</table>
	<hr>
	<br>
	<div id="round-summary-details">
	<table>
		<tr>
			<td>Total Strokes</td>
			<td>70</td>
		</tr>
		<tr>
			<td>Average Strokes Above Par</td>
			<td>0</td>
		</tr>
		<tr>
			<td>Total Bogeys</td>
			<td>4</td>
		</tr>
		<tr>
			<td>Total Birdies</td>
			<td>6</td>
		</tr>
		<tr>
			<td>Greens in Regulation</td>
			<td>16</td>
		</tr>
		<tr>
			<td>Fairways in Regulation</td>
			<td>12</td>
		</tr>
		<tr>
			<td>Total Putts</td>
			<td>27</td>
		</tr>
		<tr>
			<td>Longest Drive</td>
			<td>325 yards</td>
		</tr>
	
	</table>
	</div>
	<br>
	<hr>
	<table>
		<tr>
			<td style="text-align: center;">Front: 34(-2)</td>
			<td style="text-align: center;">Back: 36(+0)</td>
		</tr>
	</table>
	<table>
		<tr>
			<td style="text-align: center; width: 100%;">Total: 70(-2)</td>
		</tr>
	</table>
	
</body>
</html>