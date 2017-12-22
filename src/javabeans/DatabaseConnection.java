package javabeans;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public static Connection openConnection() throws Exception {
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
		

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sisdata", "root", "");
		return conn;

	}
}
