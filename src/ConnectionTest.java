import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectionTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		/*Connection con=connect();
		PreparedStatement ps  = con.prepareStatement("insert into tweets(username, location, tweetId,content,latitude,longitude) values(?,?,?,?,?,?) ");
		ps.setString(1, "test");
		ps.setString(2, "test");
		ps.setLong(3, 123456);
		ps.setString(4,"sample tweet");
		ps.setDouble(5, 2.55555);
		ps.setDouble(6, 3.55555);

		ps.executeUpdate();
		con.close();*/
		readDBData();
	}
	
public static Connection connect() throws ClassNotFoundException, SQLException{
		
		/*Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/tweetsdata";
		String username= "root"; 
		String password= "root";
		Connection conn = null;
		conn = DriverManager.getConnection(url,username,password);
		System.out.println("Connection created succesfully");
		return conn;*/
	String myDriver = "com.mysql.jdbc.Driver";
String myUrl = "jdbc:mysql://tweetvideousers.cngwtgfvvaab.us-west-2.rds.amazonaws.com:3306/TweetVideo";
Class.forName(myDriver);
Connection conn = DriverManager.getConnection(myUrl, "vthallam", "Steve123");
System.out.print("Connection created");
return conn;
	}
public static void readDBData() throws ClassNotFoundException, SQLException{
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

}
