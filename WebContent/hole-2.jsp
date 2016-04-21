<%@ page import="model.HoleYards, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
	<!-- Zhenxu Add -->
    <!-- jquery mobile -->
    <script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
    <!-- google map api -->
    <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&libraries=geometry"></script>
    <!-- model.js -->
    <script src="./Distance Calcilator.js"></script>
	<!-- The End of Zhenxu Add -->	
	
</head>
<script type="text/javascript">
	var distance = 165;
	function updateDistance() {
		var text = "Distance to hole: " + distance + " yards";
		document.getElementById("gps_distance").innerHTML = text;
	}	

</script>

<style>
	body {
	background-color: #E0E0E0;
	background-size: contain;
  	background-position: center;
	background-attachment: fixed;
  	background-repeat: no-repeat;
 	width: auto;
  	height: 100%;
	}

</style>
<body background="images/${currentHoleNumber}.jpg">	

<%
ArrayList<HoleYards> holeYardsArrayList = new ArrayList<HoleYards>();
holeYardsArrayList = ((ArrayList<HoleYards>) session.getAttribute("holeYardsArrayList"));

Integer currentHoleID = (Integer) session.getAttribute("currentHoleID");
String tee = (String) session.getAttribute("tee");
int counter = 0;
boolean match2 = false;
while (!match2) {
	
	HoleYards HY = holeYardsArrayList.get(counter);
	
	if (HY.getHoleYardsHoleID() == currentHoleID && HY.getHoleYardsTee().equalsIgnoreCase(tee)) {
		match2 = true;
		int currentHoleYardage = HY.getHoleYardsYardage();
		session.setAttribute("currentHoleYardage", currentHoleYardage);
	}
	counter=counter+1;
}


System.out.println(holeYardsArrayList.size());
%>

	<div id="sidebar">
	<div id="hole_info">
		<div id="hole_number">
			<h1>Hole</h1>
			<p>${currentHoleNumber}</p>
		</div>
		<div id="par">
			<h1>Par</h1>
			<p>${currentHolePar}</p>
		</div>
		<div id="result">
			<h1>Stroke</h1>
			<p>${currentShotNumber}</p>
		</div>
	</div>
	<div id="yardage">
		<p>${currentHoleYardage} Yards</p>
	</div>
	<div id="gps_distance">
		<p id="distance"></p><!-- zhenxu's change -->
		<p></p>
		<button class="btn-primary" onclick="dispatcher('record')">Distance</button><!-- zhenxu's change -->
		<input type="submit" value="Get Dist to Hole" onClick="updateDistance()" />
	<p>Total Score:  ${cumulativeShots}</p>

    <p>Front 9 Score: ${totalScoreF9}</p>

	<p>Back 9 Score: ${totalScoreB9}</p>
	</div>
	
	</div>

	<footer>
	<div id="footer-nav">
		<div id="footer-button">
			<a href="InTheHole"><h5>In the Hole!</h5></a>
		</div>
		<div id="footer-button">
			<a href="record-details.jsp"><h5>Select Club</h5></a>
		</div>
		
		<div id="footer-button">
			<a href="Hole2"><h5>Next Shot</h5></a>
		</div>
	</div>
	</footer>
	
</body>
</html>