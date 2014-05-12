import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


public class DataStoreTest {
	public static void main(String[] args) {
		com.google.appengine.api.datastore.DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity user1 = new Entity("User1",123);
		user1.setProperty("userid", 123);
		user1.setProperty("username", "joe");
		user1.setProperty("password", "abc");
		user1.setProperty("email", "joe@gmail.com");
		ds.put(user1);
	}

}
