<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<style>

input {
	width: 100%;
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
			<td style="width: 100%; text-align: right; padding-right: 3%">Round Date <a href="">${roundDate}</a></td>
		</tr>
	</table>
	<hr>
	<br>
	<div id="round-summary-details">
	<table>
		<tr>
			<td>Total Strokes</td>
			<td>${cumulativeShots}</td>
		</tr>
		<tr>
			<td>Average Strokes Above Par</td>
			<td>${avgStrokesAbovePar}</td>
		</tr>
		<tr>
			<td>Total Bogeys</td>
			<td>${totalBogeys}</td>
		</tr>
		<tr>
			<td>Total Birdies</td>
			<td>${totalBirdies}</td>
		</tr>
		<tr>
			<td>Total Pars</td>
			<td>${totalPars}</td>
		</tr>
		<tr>
			<td>Total Putts</td>
			<td>${totalPutts}</td>
		</tr>
		<tr>
			<td>Greens in Regulation</td>
			<td>${totalGIR}</td>
		</tr>
		<tr>
			<td>Fairways in Regulation</td>
			<td>${totalFIR}</td>
		</tr>
	
	</table>
	</div>

	<table style="height: 5px;">
		<tr>
			<td style="text-align: center;">Front: ${totalScoreF9}(${totalScoreF9OverUnder})</td>
			<td style="text-align: center;">Back: ${totalScoreB9}(${totalScoreB9OverUnder})</td>
		</tr>
	</table>
	<table style="height: 5px;">
		<tr>
			<td style="text-align: center; width: 100%;">Total: ${totalRoundScore}(${totalRoundOverUnder})</td>
		</tr>
	</table>
	<form action="round-summary-front-9.jsp">
	<div id="comments-section">
	Comments:
	<div id="comments">
  		<input type="text" name="comments">
	</div>
	<div id="buttons" style="width: 100%">
		<input type="submit" value="End Round">	
	</div>
	</div>
	</form>
</body>
</html>