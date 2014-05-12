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
</head>
<body>
<%
DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//Key guestbookKey = KeyFactory.createKey("Guestbook", "");
Query query = new Query("TweetsData");
List<Entity> greetings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(100));
PreparedQuery pq =  datastore.prepare(query);
for(Entity result: pq.asIterable()){
	
	double latitude = (double) result.getProperty("latitude");
	double longitude = (double) result.getProperty("longitude");
	Long height = (Long) result.getProperty("height");
	response.getWriter().println(latitude + " " + longitude);
	
}

%>
</body>
</html>