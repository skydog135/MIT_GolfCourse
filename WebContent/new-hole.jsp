<%@ page import="model.HoleYards, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang='en'>

<head>
	<link rel="stylesheet" type="text/css" href="theme.css">
	<title></title>
	
	
</head>
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
	
	#footer-button {
		width: 45%;
	}

</style>

<%
System.out.println("in new-hole.jsp and just about to read in holeyardsarraylist");
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
	System.out.println("in new-hole.jsp and counter = " + counter);
}


System.out.println(holeYardsArrayList.size());
%>

<body background="images/${currentHoleNumber}.jpg">	
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
			<h1>Result</h1>
			<p>${currentShotNumber}</p>
		</div>
	</div>
	<div id="yardage">
		<p>${currentHoleYardage}</p>
		<p>Yards</p>
	</div>
	</div>
	<footer>
	<div id="footer-nav">
		<div id="footer-button">
			<a href="InTheHole"><h5>In the Hole!</h5></a>
		</div>
		
		<div id="footer-button">
			<a href="new-hole-details.jsp"><h5>Select Club</h5></a>
		</div>
		
		<div id="footer-button">
			<!--  <a href="hole-2.jsp"><h5>Next Shot</h5></a>-->
			<a href="Hole2"><h5>Next Shot</h5></a>
		</div>
		</div>
	<!--  </div> -->
	</footer>

</body>
</html>