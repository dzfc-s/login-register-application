package DAO;

import java.sql.SQLException;

public interface UserDAOInterface {

	public void logIn(String username, String password) throws SQLException;
	
	public void logOut(String username, String password) throws SQLException;
	
	public void changeFirstName(String username, String firstName) throws SQLException;
	
	public void changeLastName(String username, String lastName) throws SQLException;
	
	public void changePassword(String username, String password) throws SQLException;
	
	public void register(String firstName, String lastName, String username, String password) throws SQLException;

}
