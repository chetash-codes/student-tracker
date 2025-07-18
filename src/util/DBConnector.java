package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	public static final String URL = "jdbc:mysql://localhost:3306/studentdb";
	public static final String USER = System.getenv("DB_USER");
	public static final String PASS = System.getenv("DB_PASS");
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASS);
	}
}
