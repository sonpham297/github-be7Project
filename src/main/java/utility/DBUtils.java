package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/be7_project_database";
    static final String DATABASE_USER = "root";
    static final String DATABASE_PASSWORD = "root";

    public static Connection makeConnection () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
			return conn;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return null;
    }
}