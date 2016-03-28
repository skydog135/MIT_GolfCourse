<%@ page import="model.HoleYards, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
</head>
<script type="text/javascript">
	var distance = 165;
	function updateDistance() {
		var text = "Distance to hole: " + distance + " yards";
		document.getElementById("yardage").innerHTML = text;
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
	<input type="submit" value="Calculate Distance" onClick="updateDistance()" />
	</div>
	<footer>
	<div id="footer-nav">
		<div id="footer-button">
			<a href="Hole2"><h5>Next Shot</h5></a>
		</div>
		<div id="footer-button">
			<a href="record-details.jsp"><h5>Select Club</h5></a>
		</div>
		<div id="footer-button">
			<a href="hole-summary.jsp"><h5>In the Hole!</h5></a>
		</div>
	</div>
	</footer>
	
</body>
</html>