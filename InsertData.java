package com.scrappingdata.ScrapeData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class InsertData {
	public static void insertData(HashMap<Product, Integer> data) {
		Connection connection = null;
		try {
			// below two lines are used for connectivity.
			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/be7_project_database", "root",
//					"root");
			connection = DriverManager.getConnection("jdbc:mysql://be7-project.cv8u2qecakqr.ap-southeast-2.rds.amazonaws.com:3306/be7Project", "admin",
					"Tukhongtoi9");

			for (Entry<Product, Integer> entry : data.entrySet()) {
				Product prod = entry.getKey();

				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO products "
						+ "(name, category, gender, price, price_discount, rating, rating_count,"
						+ "general_info, avail_qty) " + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				pstmt.setString(1, prod.getName());
				pstmt.setString(2, prod.getCategory());
				pstmt.setString(3, prod.getGender());
				pstmt.setFloat(4, prod.getPrice());
				pstmt.setFloat(5, prod.getPriceDiscount());
				pstmt.setFloat(6, prod.getRatingStarBar());
				pstmt.setInt(7, prod.getRateCount());
				pstmt.setString(8, prod.getGeneralInfo());
				pstmt.setInt(9, prod.getAvailQty());

				try {
					pstmt.executeUpdate();
				} catch (Exception exception) {
					System.out.println("Insert data into table products is fail: " + exception);
				}

//				PreparedStatement pstmt2 = connection.prepareStatement("INSERT INTO images " + "(product_name) " + "values (?)");
//				pstmt2.setString(1, prod.getName());
//
//				try {
//					pstmt2.executeUpdate();
//				} catch (Exception exception) {
//					System.out.println("Insert data into table images is fail: " + exception);
//				}
				
				int nth = 1;
				LinkedList<String> images = prod.getImage();
				for (String image : images) {
					PreparedStatement pstmt2 = connection.prepareStatement(
							"INSERT INTO images " + "(product_name, image) " + " values (?,?)");
					pstmt2.setString(1, prod.getName());
					if (nth == 1)
						pstmt2.setString(2, prod.getName());
					else
						pstmt2.setString(2, prod.getName()+" "+ nth);
						
					nth++;
					try {
						pstmt2.executeUpdate();
					} catch (Exception exception) {
						System.out.println("Insert images into table images is fail: " + exception);
					}

				}

			}
			connection.close();
			System.out.println("Data is inserted successfully into database.");
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}
}
