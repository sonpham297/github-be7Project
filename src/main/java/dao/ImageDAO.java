package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utility.DBUtils;

public class ImageDAO {
	public ArrayList<String> getAllImages(String productName) {
		ArrayList<String> images = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM images WHERE product_name = \"" +productName +"\"" )) {
			while (resultSet.next()) {
				images.add(resultSet.getString("image"));
			}
			return images;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
