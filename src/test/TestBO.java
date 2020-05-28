package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import BO.Validation;

class TestBO {

	@Test
	void shouldReturnFalseWhenUsernameEqualsNull() throws SQLException {	
		boolean rezultat = Validation.usernameValidation("o");
		assertFalse(rezultat);
	}
	
	@Test
	void shouldReturnTrueWhenUsernameEqualsS() throws SQLException {	
		boolean rezultat = Validation.usernameValidation("S");
		assertTrue(rezultat);
	}
	
	@Test
	void shouldReturnTrueWhenPasswordLengthGreaterThan6() throws SQLException {	
		boolean rezultat = Validation.passwordLengthValidation("123456");
		assertTrue(rezultat);
	}
	
	@Test
	void shouldReturnFalseWhenPasswordLengthLessThan6() throws SQLException {	
		boolean rezultat = Validation.passwordLengthValidation("11");
		assertFalse(rezultat);
	}
	
	@Test
	void shouldReturnFalseWhenPasswordIsIncorrect() throws SQLException {	
		boolean rezultat = Validation.passwordValidation("fwelkflv", "dzfc");
		assertFalse(rezultat);
	}

	@Test
	void shouldReturnTrueWhenPasswordIsCorrect() throws SQLException {	
		boolean rezultat = Validation.passwordValidation("fwelkflvk", "dzfc");
		assertTrue(rezultat);
	}
	
	@Test
	void shouldReturnFalseWhenUsernameEqualsNull1() throws SQLException {	
		boolean rezultat = Validation.passwordValidation("fwelkflvk", "dzf");
		assertFalse(rezultat);
	}
	
	@Test
	void shouldReturnTrueWhenUsernameEqualsDzfc() throws SQLException {	
		boolean rezultat = Validation.passwordValidation("fwelkflvk", "dzfc");
		assertTrue(rezultat);
	}
}
