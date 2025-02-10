package com.LastestScrapeData.LastestScrape.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map.Entry;

import com.LastestScrapeData.LastestScrape.entity.Product;

public class UpdateData {
	public static void updateData(HashMap<String, Product> data) {
		Connection connection = null;
		try {
			// below two lines are used for connectivity.
			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/be7_project_database", "root", "root");
//			connection = DriverManager.getConnection("jdbc:mysql://be7-project.cv8u2qecakqr.ap-southeast-2.rds.amazonaws.com:3306/be7Project", "admin", "Tukhongtoi9");
			connection = DriverManager.getConnection("jdbc:mysql://103.186.146.221:3306/be7Project", "sonpham", "Tukhongtoi9@");

			for (Entry<String, Product> entry : data.entrySet()) {
				Product prod = entry.getValue();

				PreparedStatement pstmt = connection.prepareStatement("UPDATE products "
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