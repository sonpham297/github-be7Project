package com.scrappingdata.ScrapeData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map.Entry;

public class UpdateData {
	public static void updateData(HashMap<Product, Integer> data) {
		Connection connection = null;
		try {
			// below two lines are used for connectivity.
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/be7_project_database", "root",
					"root");

			for (Entry<Product, Integer> entry : data.entrySet()) {
				Product prod = entry.getKey();

				PreparedStatement pstmt = connection.prepareStatement("UPDATE products2 "
						+ "SET price =? , price_discount=?"
						+ "WHERE name =?");
				pstmt.setFloat(1, prod.getPrice());
				pstmt.setFloat(2, prod.getPriceDiscount());
				pstmt.setString(3, prod.getName());

				try {
					pstmt.executeUpdate();
				} catch (Exception exception) {
					System.out.println(exception);
				}
			}
			connection.close();
			System.out.println("Database is updated successfully.");
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}
}