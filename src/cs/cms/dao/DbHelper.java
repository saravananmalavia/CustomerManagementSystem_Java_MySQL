package cs.cms.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {
	


	    // Database URL, username, and password should be configured based on your database
	    private static final String DB_URL = "jdbc:mysql://localhost:3306/CMS";
	    private static final String USER = "root";
	    private static final String PASSWORD = "123456";

	    /**
	     * Establishes and returns a connection to the database.
	     * 
	     * @return a Connection object or null if the connection fails
	     */
	    public static Connection getConnection() {
	        Connection connection = null;
	        try {
	            // Load the database driver if necessary (not required for JDBC 4.0 and later)
	             try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            
	            // Establish the connection
	            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            // Log the exception
	            System.err.println("Connection failed: " + e.getMessage());
	            // Optionally, handle the exception further or rethrow
	        }
	        return connection;
	    }
	}



