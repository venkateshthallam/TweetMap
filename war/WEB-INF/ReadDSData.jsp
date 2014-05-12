<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import ="com.google.appengine.api.datastore.PreparedQuery"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Info windows</title>

</head>
<body>
<%
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//Key guestbookKey = KeyFactory.createKey("Guestbook", "");
Query query = new Query("TweetsData");
List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
PreparedQuery pq =  datastore.prepare(query);
int i=0,j=0;
double[] latArray=new double[100];
double[] lonArray=new double[100];
for(Entity result: pq.asIterable()){
	
	latArray[i]=new Double(result.getProperty("latitude").toString());
	lonArray[i]=new Double(result.getProperty("latitude").toString());
	response.getWriter().println(result.getProperty("latitude") + " " + result.getProperty("longitude"));
	i++;
	
}

%>
    <script type="text/javascript">
    function show_value(val) {
    alert("goingggg");
        alert(val);
        document.getElementById('KeywordDropdownId').value = val;
        window.location.href = "kmatch.html?field1="+val;
        
    }
</script> 
<style>
      html, body, #map-canvas {
        height: 100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    <script type="text/javascript"> 
function start(){
block = setInterval("window.clipboardData.setData('text','')",20);
}
function stop(){
clearInterval(block);
}	
</script> 
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
    <script>
// This example displays a marker at the center of Australia.
// When the user clicks the marker, an info window opens.

function initialize(double[] latArray,double[] lonArray) {
	for(int i=0;i<latArray.length;i++){
  var myLatlng = new google.maps.LatLng(latArray[i],lonArray[i]);
  var mapOptions = {
    zoom: 4,
    center: myLatlng
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

  var contentString = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">Uluru</h1>'+
      '<div id="bodyContent">'+
      '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
      'sandstone rock formation in the southern part of the '+
      'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
      'south west of the nearest large town, Alice Springs; 450&#160;km '+
      '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
      'features of the Uluru - Kata Tjuta National Park. Uluru is '+
      'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
      'Aboriginal people of the area. It has many springs, waterholes, '+
      'rock caves and ancient paintings. Uluru is listed as a World '+
      'Heritage Site.</p>'+
      '<p>Attribution: Uluru, <a href="http://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
      'http://en.wikipedia.org/w/index.php?title=Uluru</a> '+
      '(last visited June 22, 2009).</p>'+
      '</div>'+
      '</div>';

  var infowindow = new google.maps.InfoWindow({
      content: contentString
  });
 
  var marker = new google.maps.Marker({
      position: myLatlng,
      map: map,
      title: 'Uluru (Ayers Rock)'
  });
  google.maps.event.addListener(marker, 'click', function() {
    infowindow.open(map,marker);
  });
 }
}

google.maps.event.addDomListener(window, 'load', initialize);


    </script>
	<script>
	function keydropdown{
		var ddl = document.getElementById('KeywordDropdownId');

		for (var i = 1; i <= 100; i++) {
		    var theOption = new Option;
		    theOption.text = "no" + i;
		    theOption.value = i;
		    ddl.options[i] = theOption;
		    
		}
	}
</script>
 <div id="map-canvas"></div>
</body>
</html>