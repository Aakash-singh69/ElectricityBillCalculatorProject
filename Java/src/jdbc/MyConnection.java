package jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyConnection {
	private static Connection con = null;
	private static MyConnection instance = null;

	// connects to database
	private MyConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electricityproject", "root", "root789");
	}

	// method that return single instance of the class
	public static synchronized MyConnection getInstance() throws SQLException, ClassNotFoundException {
		if (instance == null) {
			instance = new MyConnection();
		}
		return instance;
	}

	// return connection object
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		return con;
	}

}
