package com.datastore;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
//import twitter4j.FilterQuery;
//import twitter4j.StallWarning;
//import twitter4j.StatusListener;
/*import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;*/
@SuppressWarnings("serial")
public class DataStoreTestServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		final String Access_Token = "433077519-JrUfgLAtVs0WrUDmiZqHWf4cbTXoN3mXBZNTaBr5";
		final String Access_Token_Secret= "EBcZ2tluwzmwWxtnYChHqbp8ucB8Km7ygRBbGr7bjPhrb";
		final String Consumer_Key = "n9EhEfXgnGvRe5LqPKC0tT4K2";
	    final String Consumer_Secret = "UKxK7gYKIHck7Ipe6ziNafNGo5Alk7gts3OACIYNG15FoDH45e";
	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true) 
	    .setOAuthAccessToken(Access_Token)
	    .setOAuthAccessTokenSecret(Access_Token_Secret)
	    .setOAuthConsumerKey(Consumer_Key)
	    .setOAuthConsumerSecret(Consumer_Secret);
	    try {
	    	TwitterFactory tf = new TwitterFactory(cb.build());
	    	Twitter twitter_instance = tf.getInstance();
	    	QueryResult qr;
	    	String keyword="Techcrunch";
	    	Query q = new Query(keyword);
	    	do 
	    	{
	    		qr = twitter_instance.search(q);
	    		List<Status> twitts = qr.getTweets();
	    		//System.out.println("tweets list "+twitts);
	    		for (Status each_twitt : twitts) 
	    		{
	    			//String collection_twitts="@" + each_twitt.getUser().getScreenName() + " - " + each_twitt.getText();
	    			//System.out.println("each_twitt.getUser().getScreenName() "+ each_twitt.getUser().getScreenName()+"each_twitt.getText() "+each_twitt.getText());
	    			//if(each_twitt.getGeoLocation().getLatitude()==)
	    			if(each_twitt.getGeoLocation()!=null){
	    			/*System.out.println(each_twitt.getGeoLocation().getLatitude());
	    			System.out.println(each_twitt.getGeoLocation().getLongitude());*/
	    			writeToDataStore(keyword,each_twitt.getUser().getName(),each_twitt.getText(),each_twitt.getGeoLocation().getLatitude(),each_twitt.getGeoLocation().getLongitude());
	    			}
	    			//System.out.println(gl.getLatitude()+""+gl.getLongitude());
	    			
	    			//bw.write(collection_twitts);
	    			//System.out.println(collection_twitts);
	    			//System.out.println(each_twitt.getGeoLocation().getLatitude());
	    			
	    		}
	    		//bw.close();
	    	} while ((q = qr.nextQuery()) != null);
	    	System.exit(0);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	public static void writeToDataStore(String keyword,String username,String content,double latitude,double longitude){
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		if(username!=null && content!=null){
		Entity e = new Entity("TweetsData");
		e.setProperty("keyword",keyword);
		e.setProperty("username",username);
		e.setProperty("content",content);
		e.setProperty("latitude",latitude);
		e.setProperty("longitude",longitude);
		ds.put(e);
		}
		else{
			throw new NullPointerException("Some null pointer in parameters");
			
		}
	}
	/*public static void readDBData() throws ClassNotFoundException, SQLException{
		Connection conn=connect();
		Statement stmt=null;
		String sql="select * from Tweets";
		 stmt = conn.createStatement();
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		        String username=rs.getString("username");
		        String location=rs.getString("location");
		        long tweetId=rs.getLong("TweetId");
		        String content=rs.getString("content");
		        double latitude=rs.getDouble("latitude");
		        double longitude=rs.getDouble("longitude");
		        writeToDataStore(username,content,latitude,longitude);
		         //Display values
		         System.out.print("username: " + username);
		         System.out.print(", location: " + location);
		         System.out.print(", tweetId: " + tweetId);
		         System.out.println(", content: " + content);
		         System.out.println(", latitude: " + latitude);
		         System.out.println(", longitude: " + longitude);
		      }
		      stmt.close();
		      conn.close();
	}
public static Connection connect() throws ClassNotFoundException, SQLException{
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/tweetsdata";
		String username= "root"; 
		String password= "root";
		Connection conn = null;
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("Connection created succesfully");
		return conn;
		String myDriver = "com.mysql.jdbc.Driver";
		String myUrl = "jdbc:mysql://tweetvideousers.cngwtgfvvaab.us-west-2.rds.amazonaws.com:3306/TweetVideo";
		Class.forName(myDriver);
		Connection conn = DriverManager.getConnection(myUrl, "vthallam", "Steve123");
		System.out.print("Connection created");
		return conn;
	}*/
}
