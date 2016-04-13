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
				//var loc1 = new google.maps.LatLng(input.getAttribute("currentHolePinLatitude")+"", input.getAttribute("currentHolePinLongitude")+"");
				var loc2 = new google.maps.LatLng(latitude+"", longitude+"");
				var yards = (google.maps.geometry.spherical.computeDistanceBetween(loc1,loc2))*1.09;
				$("#distance").html(yards.toFixed(0) + " yards");//display
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



//dispatch message to model and model render view then
function dispatcher(request){
	if(request === "record")
	{
	      record();
	}
}
