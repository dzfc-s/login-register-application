package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.InputMismatchException;

import org.junit.jupiter.api.Test;

import DTO.User;
import UI.UserDAODemo;

class TestUI {

	@Test
	void shouldReturnUserWhenRegisterSuccessful() throws InputMismatchException, SQLException {
		User user = new User("selma", "Dzafic", "sdz", "lelele");
		User userTest = UserDAODemo.registracija();
		assertEquals(user, userTest);
	}

}
