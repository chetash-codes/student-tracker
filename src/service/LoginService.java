package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;
import util.HashUtil;

public class LoginService {
	
	public boolean isValidUser(String username, String password) {
		String query = "SELECT password_hash FROM users WHERE username = ?";
		
		try (Connection conn = DBConnector.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String storedHash = rs.getString("password_hash");
				return HashUtil.verifyPassword(password, storedHash);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getUserRole(String username, String password) {
		String query = "SELECT password_hash, role FROM users WHERE username = ?";
		
		try (Connection conn = DBConnector.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String hash = rs.getString("password_hash");
				String role = rs.getString("role");
				
				if (HashUtil.verifyPassword(password, hash)) {
					return role;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
