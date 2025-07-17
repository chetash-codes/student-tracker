package tester;

import java.util.Scanner;

import service.LoginService;

public class LoginTester {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Username: ");
		String user = sc.nextLine();
		System.out.print("Password: ");
		String pass = sc.nextLine();
		sc.close();
		
		LoginService loginService = new LoginService();
		boolean isValid = loginService.isValidUser(user, pass);
		
		System.out.println("Login " + (isValid ? "successful" : "failed"));
	}

}
