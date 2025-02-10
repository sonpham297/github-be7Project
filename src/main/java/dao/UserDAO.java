package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import entity.User;
import utility.DBUtils;

public class UserDAO {

	public boolean insert(User user) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = DBUtils.makeConnection();

			// WRITE SQL QUERY
			String SQL_QUERY = "INSERT INTO users(first_name, last_name, email, password, token, token_expiry, is_activate) VALUES(?, ?, ?, ?, ?, ?, ?)";

			// EXECUTE QUERY
			statement = conn.prepareStatement(SQL_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getToken());
			statement.setTimestamp(6, user.getTokenExpiry());
			statement.setString(7, user.getIsActivate());

			statement.execute();
			
			System.out.println("Inserted user with lastname: "+ user.getLastName()+ " and password: "+user.getPassword());
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
	}

	public User findUser(String email) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DBUtils.makeConnection();

			// WRITE SQL QUERY
			String SQL_QUERY = "SELECT * FROM users WHERE email = ?";

			// EXECUTE QUERY
			statement = conn.prepareStatement(SQL_QUERY);
			statement.setString(1, email);
			
			resultSet = statement.executeQuery();
			// READ RESULTSET
			boolean hasNext = resultSet.next();
			if (hasNext) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String token = resultSet.getString("token");
				Timestamp tokenExpiry = resultSet.getTimestamp("token_expiry");
				String isActivate = resultSet.getString("is_Activate");
				System.out.println("found user with lastname: "+ lastName+ " and password: "+ password);

				return new User(firstName, lastName, email, password, token, tokenExpiry, isActivate);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	
	public User findUserByToken(String token) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			conn = DBUtils.makeConnection();

			// WRITE SQL QUERY
			String SQL_QUERY = "SELECT * FROM users WHERE token = ?";

			// EXECUTE QUERY
			statement = conn.prepareStatement(SQL_QUERY);
			statement.setString(1, token);
			
			resultSet = statement.executeQuery();
			// READ RESULTSET
			boolean hasNext = resultSet.next();
			if (hasNext) {
				String firstName = resultSet.getString("first_name");
				String lastName = resultSet.getString("last_name");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				token = resultSet.getString("token");
				Timestamp tokenExpiry = resultSet.getTimestamp("token_expiry");
				String isActivate = resultSet.getString("is_Activate");
				System.out.println("found user by tokene: "+ token + " with lastName:" + lastName + "and password: "+ password);

				return new User(firstName, lastName, email, password, token, tokenExpiry, isActivate);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				resultSet.close();
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();

			}

		}
		return null;
	}
	
	public boolean updateActivation(String token) {
		Connection conn = null;
		PreparedStatement updateStmt = null;
		try {
			conn = DBUtils.makeConnection();

			// WRITE SQL QUERY
			updateStmt = conn.prepareStatement(
					"UPDATE users SET is_activate = 'Y', token = NULL, token_expiry = NULL WHERE token = ?");
			updateStmt.setString(1, token);
			updateStmt.executeUpdate();
			
			System.out.println("Updated user activation with token: "+ token);
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				updateStmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}

		}
	}

	
	
}
