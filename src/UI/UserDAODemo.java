package UI;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import BO.Validation;
import DAO.UserDAOImplementation;
import DTO.User;

public class UserDAODemo {
	
	static UserDAOImplementation userDAO = new UserDAOImplementation();
	static Scanner input = new Scanner(System.in);
	
	public static void menu() throws SQLException, InputMismatchException {
		
		while(true) {
			System.out.println("Izaberite zeljenu opciju: ");
			System.out.println("1.Registracija");
			System.out.println("2.Prijava");
			
			int odabir = input.nextInt();
			switch(odabir) {
				case(1):
					try {
					registracija();
					} catch(InputMismatchException e) {
						input.nextLine();
						registracija();
					}
					break;
				case(2):
					try {
						login();
						} catch(InputMismatchException e) {
							input.nextLine();
							login();
						}
					break;
			}
			
			if(odabir < 1 || odabir > 2)
				menu();
		}
	}
	
	public static String inputFirstName() throws SQLException, InputMismatchException {
		System.out.print("Unesite ime: ");
		String firstName = input.next();
		return firstName;
	}
	
	public static User registracija() throws SQLException, InputMismatchException {
		
		inputFirstName();
		
		System.out.print("Unesite prezime: ");
		String lastName = input.next();
		
		System.out.print("Unesite username: ");
		String username = input.next();
		
		System.out.print("Unesite sifru: ");
		String password = input.next();
		
		if(!Validation.usernameValidation(username) && Validation.passwordLengthValidation(password)) {
			userDAO.register(firstName, lastName, username, password);
			System.out.println("Registracija uspjesno obavljena!");
			return new User(firstName, lastName, username, password);
		} 
		else if(Validation.usernameValidation(username)) {
				System.out.println("Vec postoji identican username..");
				return null;
		} else 
			registracija();
		return null;
	}
	
	public static void login() throws SQLException, InputMismatchException {
		
		System.out.print("Unesite username: ");
		String username = input.next();
		
		System.out.print("Unesite password: ");
		String password = input.next();
		
		if(Validation.usernameValidation(username) && Validation.passwordValidation(password, username)) {
			userDAO.logIn(username, password);
			System.out.println("Prijava uspjesno obavljena!");
			try {
				menu2(username);
			} catch (InputMismatchException e) {
				input.nextLine();
				menu2(username);
			}
		} else {
			System.out.println("Unijeli ste netacne podatke..");
			login();
		}
	}
	
	public static void menu2(String username) throws SQLException, InputMismatchException {
		
		while(true) {
			System.out.println("Izaberite zeljenu opciju: ");
			System.out.println("1. Promijeniti ime");
			System.out.println("2. Promijeniti prezime");
			System.out.println("3. Promijeniti sifru");
			System.out.println("4. Odjaviti se");
			
			int odabir = input.nextInt();
			switch(odabir) {
				case(1):
					try {
						changeFirstName(username);
					} catch (InputMismatchException e) {
						input.nextLine();
						changeFirstName(username);
					}
					break;
				case(2):
					try {
						changeLastName(username);
					} catch (InputMismatchException e) {
						input.nextLine();
						changeLastName(username);
					}
					break;
				case(3): 
					try {
						changePassword(username);
					} catch (InputMismatchException e) {
						input.nextLine();
						changePassword(username);
					}
					break;
				case(4): 
					logOut();
					break;
			}
			
			if(odabir < 1 || odabir > 4)
				menu2(username);
		}	
	}
	
	public static void changeFirstName(String username) throws SQLException, InputMismatchException {
		
		System.out.print("Unesite novo ime: ");
		String firstName = input.next();
		
		userDAO.changeFirstName(username, firstName);
		System.out.println("Promjena uspjesno izvrsena!");
		
	}
	
	public static void changeLastName(String username) throws SQLException, InputMismatchException {
		
		System.out.print("Unesite novo prezime: ");
		String lastName = input.next();
		
		userDAO.changeLastName(username, lastName);
		System.out.println("Promjena uspjesno izvrsena!");
	}
	
	public static void changePassword(String username) throws SQLException, InputMismatchException {
		
		System.out.print("Unesite novu šifru: ");
		String password = input.next();
		
		if(Validation.passwordLengthValidation(password)) {
			userDAO.changePassword(username, password);
			System.out.println("Promjena uspjesno izvrsena!");
		} else 
			changePassword(username);
	}
		
	public static void logOut() throws SQLException, InputMismatchException {
		
		System.out.println("Odjavljeni ste!");
		menu();
	}
	
	
	public static void main(String[] args) throws SQLException, InputMismatchException {
		try {
			menu();
		} catch (InputMismatchException e) {
			input.nextLine();
			menu();
		}
	}


}
