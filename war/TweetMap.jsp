<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.logging.Level"%>
<%@ page import="com.google.appengine.api.datastore.DatastoreService"%>
<%@ page
	import="com.google.appengine.api.datastore.DatastoreServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.Entity"%>
<%@ page import="com.google.appengine.api.datastore.FetchOptions"%>
<%@ page import="com.google.appengine.api.datastore.Key"%>
<%@ page import="com.google.appengine.api.datastore.KeyFactory"%>
<%@ page import="com.google.appengine.api.datastore.Query"%>
<%@ page import="com.google.appengine.api.memcache.ErrorHandlers"%>
<%@ page import="com.google.appengine.api.memcache.MemcacheService"%>
<%@ page import="com.google.appengine.api.memcache.MemcacheServiceFactory"%>
<%@ page import="com.google.appengine.api.datastore.Query.FilterOperator"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
<script
	src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAq1qbnV6PGUYiTDuhDpisqsEmeDQYYHkk&sensor=false">
</script>

<script>
var mapCenter=new google.maps.LatLng(2.8800,23.6560);//Coords for D R Congo
function initialize()
{
	var twittCircle;
	var mapProp = {
	  center:mapCenter,
	  zoom:2,
	  mapTypeId:google.maps.MapTypeId.ROADMAP
	  };
	
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

	<%
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		MemcacheService twittCache = MemcacheServiceFactory.getMemcacheService(); 
	    //Key twittKey = KeyFactory.createKey("TweetsData", "TwittKey");
		twittCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
		List<Entity> twittsList;
		String filter = request.getParameter("filter");
		String entityKind="TweetsData";
		if(filter!="" && filter!="All")
		{		
		twittsList = (List<Entity>)twittCache.get(entityKind);
		if(twittsList==null){
		    Query query = new Query(entityKind);
		    twittsList = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5000));
		    twittCache.put(entityKind, twittsList);
		}
		}
		
		else{
			twittsList = (List<Entity>)twittCache.get(entityKind);
			if(twittsList==null){
			    Query query = new Query(entityKind);//.addFilter("Keyword", FilterOperator.,filter);
			    query.setFilter(FilterOperator.EQUAL.of("Keyword",filter));
			    twittsList = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5000));
			    twittCache.put(entityKind, twittsList);
			}
		}
	    // Run an ancestor query to ensure we see the most up-to-date
	    // view of the Greetings belonging to the selected Guestbook.
	    
	    for(Entity twitt:twittsList){%>
	      	var twittCoords = {
	      	      strokeColor: '#FF0000',
	      	      strokeOpacity: 0.8,
	      	      strokeWeight: 2,
	      	      fillColor: '#FF0000',
	      	      fillOpacity: 0.35,
	      	      map: map,
	      	      center: new google.maps.LatLng(<%=twitt.getProperty("latitude")%>, <%=twitt.getProperty("longitude")%>),
	      	      radius: 2500
	      	    };
	      	twittCircle = new google.maps.Circle(twittCoords);
	<%}%>
}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>

</head>

<body>
	<select onchange="document.location.href='/TweetMap.jsp?filter='+this.value;" >
		<option value="All" selected>All</option>
		<option value="happy">Happy</option>
		<option value="Easter">USA</option>
		<option value="T">Techcrunch</option>
		<option value="world">Elections</option>
		<option value="friend">Obama</option>
	</select>
	<div id="googleMap" style="width: 1200px; height: 900px;"></div>
	
</body>
</html>