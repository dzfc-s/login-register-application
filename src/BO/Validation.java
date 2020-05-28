package BO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DAO.ConnectionManager;
import DTO.User;

public class Validation {

	static Connection connection = ConnectionManager.getInstance().getConnection();
	
	public static boolean usernameValidation(String username) throws SQLException {
		
		String query = "SELECT * FROM info WHERE username = ?";
		ResultSet rs = null;
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, username);
			rs = statement.executeQuery();
			
			return rs.next();
		}	
		
	}
	
	public static boolean passwordLengthValidation(String password) throws SQLException {
		
		if(password.length() < 6) {
			System.out.println("Sifra mora imati najmanje 6 karaktera..");
			return false;
		}
			
			return true;
	}
	
	public static boolean passwordValidation(String password, String username) throws SQLException {
		
		String query = "SELECT * FROM info WHERE username = ?";
		ResultSet rs = null;
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, username);
			rs = statement.executeQuery();
				
			if(rs.next()) {
			User user = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"));
			rs.close();
				if(user.getPassword().equals(password))
					return true;
			}	
		}	
		
		return false;
	}
	
}
