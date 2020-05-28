package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImplementation implements UserDAOInterface {
	
	Connection connection = ConnectionManager.getInstance().getConnection();
	
	@Override
	public void logIn(String username, String password) throws SQLException {}

	@Override
	public void logOut(String username, String password) throws SQLException {}

	@Override
	public void changeFirstName(String username, String firstName) throws SQLException {
		
		String query = "UPDATE info SET firstName = ? WHERE username = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, firstName);
			statement.setString(2, username);
			
			statement.executeUpdate();
		}
	}

	@Override
	public void changeLastName(String username, String lastName) throws SQLException {
		
		String query = "UPDATE info SET lastName = ? WHERE username = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, lastName);
			statement.setString(2, username);
			
			statement.executeUpdate();
		}	
	}

	@Override
	public void changePassword(String username, String password) throws SQLException {
		
		String query = "UPDATE info SET password = ? WHERE username = ?";
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, password);
			statement.setString(2, username);
			
			statement.executeUpdate();
		}
	}

	@Override
	public void register(String firstName, String lastName, String username, String password) throws SQLException {
		
		String query = "INSERT INTO info(firstName, lastName, username, password) VALUES(?, ?, ?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, username);
			statement.setString(4, password);

			statement.executeUpdate();
			
		}
	}
}
