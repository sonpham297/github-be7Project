package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Product;
import utility.DBUtils;

public class ProductDAO {
	
	public ArrayList<Product> getFeaturedProduct() {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE WHERE rating >= 97")) {

			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

	public ArrayList<Product> getProductByName(String name) {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				PreparedStatement preparedStatement = conn
						.prepareStatement("SELECT * FROM products WHERE name LIKE ?")) {
			preparedStatement.setString(1, "%" + name + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			resultSet.close();
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Product getProductDetail(String name) {
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE name = \"" + name +"\"")) {

			if (resultSet.next()) {
				return new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Product> getProductByCat(String category) {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE category = \"" + category+"\"")) {

			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products")) {
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Product> getMoreThanHalfProducts() {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE price_discount >= price")) {
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Product> getMenFragrances() {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE category=\"Fragrances\""
						+ " AND gender in ('U','M')")) {
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Product> getWomenFragrances() {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet resultSet = stmt.executeQuery("SELECT * FROM products WHERE category=\"Fragrances\""
						+ " AND gender in ('U','W')")) {
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	// will implement later when user choose favour
	public ArrayList<Product> getFavPro(String userName) {
		ArrayList<Product> products = new ArrayList<>();
		try (Connection conn = DBUtils.makeConnection();
				PreparedStatement preparedStatement = conn
						.prepareStatement("SELECT * FROM products JOIN users On .....??? ?")) {
			preparedStatement.setString(1, "%" + userName + "%");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				products.add(new Product(resultSet.getString("name"), resultSet.getString("category"),
						resultSet.getString("gender"), resultSet.getFloat("price"),
						resultSet.getFloat("price_discount"), resultSet.getFloat("rating"),
						resultSet.getInt("rating_count"), resultSet.getString("general_info"),
						resultSet.getInt("avail_qty")));
			}
			resultSet.close();
			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}
}
