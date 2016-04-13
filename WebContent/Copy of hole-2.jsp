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

//model logic for reset location and distance tag//
function reset1(){
	//restore  to default value
$("#distance").html("");//display
}

//model logic for record location
function record(){
//1.get location
//2.append location to table
var gps = navigator.geolocation;//get gps location
if (gps){
  	gps.getCurrentPosition(
  		    function(position){//when success occur
  		    	var latitude = position.coords.latitude;//get latitude
  		    	var longitude = position.coords.longitude;//get longitude

				var loc1 = new google.maps.LatLng(33.91281+"", -83.368162+"");
				//var loc1 = new google.maps.LatLng(session.getAttribute("currentHolePinLatitude", currentHolePinLatitude)+"", session.getAttribute("currentHolePinLongitude", currentHolePinLongitude)+"");
				var loc2 = new google.maps.LatLng(latitude+"", longitude+"");
				var yards = (google.maps.geometry.spherical.computeDistanceBetween(loc1,loc2))*1.09;
				$("#distance").jsp(yards.toFixed(0) + " yards");//display
  		     },
             function(error){//when error occur
                      alert("Got an error, code: " + error.code + " message: "+ error.message);
             },
             {maximumAge: 10000}); // max wait time 10000 ms
        }//end if(gps)
else {
		alert("please open gps and try again!");//error infomation
     }
}

//controller
//dispatch message to model and model render view then
function dispatcher(request){
	if(request === "reset"){
		reset1();
	}
	else if(request === "record"){
	      record();
	}
}
</script><!-- zhenxu's change -->

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
		<p id="distance"></p><!-- zhenxu's change -->
	</div>
	<button class="btn-primary" onclick="dispatcher('record')">Calculate Distance</button><!-- zhenxu's change -->
	</div>
	<p>Cumulative score:  ${cumulativeShots}</p>

    <p>Front 9 score: ${totalScoreF9}</p>

	<p>Back 9 score: ${totlaScoreB9}</p>
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