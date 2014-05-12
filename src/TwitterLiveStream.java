import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
/*
import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;*/
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
public class TwitterLiveStream {
	public static void main(String[] args) throws IOException{/*
		//FileWriter fw=null;
		 final String TWITTER_CONSUMER_KEY = "n9EhEfXgnGvRe5LqPKC0tT4K2";
		  final String TWITTER_SECRET_KEY = "UKxK7gYKIHck7Ipe6ziNafNGo5Alk7gts3OACIYNG15FoDH45e";
		   String TWITTER_ACCESS_TOKEN = "433077519-JrUfgLAtVs0WrUDmiZqHWf4cbTXoN3mXBZNTaBr5";
		 final String TWITTER_ACCESS_TOKEN_SECRET = "EBcZ2tluwzmwWxtnYChHqbp8ucB8Km7ygRBbGr7bjPhrb";
		 BufferedWriter bw=null;
		  try{
		        ConfigurationBuilder cb = new ConfigurationBuilder();
		        cb.setDebugEnabled(true);
		        cb.setOAuthConsumerKey(TWITTER_CONSUMER_KEY);
		        cb.setOAuthConsumerSecret(TWITTER_SECRET_KEY);
		        cb.setOAuthAccessToken(TWITTER_ACCESS_TOKEN);
		        cb.setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
		        
		    	
		      
		        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

		        StatusListener listener = new StatusListener() {

		            @Override
		            public void onException(Exception arg0) {
		                // TODO Auto-generated method stub

		            }

		            @Override
		            public void onDeletionNotice(StatusDeletionNotice arg0) {
		                // TODO Auto-generated method stub

		            }

		            @Override
		            public void onScrubGeo(long arg0, long arg1) {
		                // TODO Auto-generated method stub

		            }

		            @Override
		            public void onStatus(Status status) {
		                User user = status.getUser();
		                GeoLocation loc= status.getGeoLocation();
		                System.out.println(loc.getLatitude());
		                System.out.println(loc.getLongitude());
		                // gets Username
		                String username = status.getUser().getScreenName();
		                System.out.println(username);
		                String profileLocation = user.getLocation();
		                
		                System.out.println(profileLocation);
		                long tweetId = status.getId(); 
		                System.out.println(tweetId);
		                String content = status.getText();
		                System.out.println(content +"\n");
		                try {
		                	String tweetContent="username "+username+" \n Location "+profileLocation+" \n TweetId "+tweetId+" \n Tweet Content "+content;
							//writeToFile(tweetContent);
							writeToDataStore(content);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

		            }

		            @Override
		            public void onTrackLimitationNotice(int arg0) {
		                // TODO Auto-generated method stub

		            }

					@Override
					public void onStallWarning(StallWarning arg0) {
						// TODO Auto-generated method stub
						
					}

		        };
		        FilterQuery fq = new FilterQuery();
		    
		        String keywords[] = {"india"};

		        fq.track(keywords);
		        
		        twitterStream.addListener(listener);
		        twitterStream.filter(fq);
		        
		        
		          
		        }
		        catch(Exception e){
		        	e.printStackTrace();
		        }
		   */}
	
	/*public static void writeToFile(String content) throws IOException{
		File file = new File("/home/vthallam/streamtweets.txt");
		BufferedWriter	bw=null;
		FileWriter	fw=null;
		try {	
			fw = new FileWriter(file.getAbsoluteFile());
			 bw = new BufferedWriter(fw);
			 bw.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			bw.close();
			fw.close();
		}
		
	}*/
	
	public static void writeToDataStore(String TweetText){
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		
		Entity e = new Entity("SampleTweet");
		e.setProperty("TweetText",TweetText);		
		ds.put(e);
	}
		}


	


