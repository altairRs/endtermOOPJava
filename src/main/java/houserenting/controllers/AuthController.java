package houserenting.controllers;

import houserenting.repositories.UserRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class AuthController {
    private final UserRepository userRepository;
    private boolean loggedIn;
    private final Scanner scanner;

    public AuthController() {
        this.userRepository = new UserRepository();
        this.loggedIn = false;
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.println("Registration");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        boolean registered = userRepository.registerUser(username, password);
        if (registered) {
            System.out.println("Registration successful.");
            loggedIn = true;
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }

    public void login() {
        System.out.println("Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        boolean authenticated = userRepository.authenticateUser(username, password);
        if (authenticated) {
            System.out.println("Login successful.");
            loggedIn = true;
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    public void logout() {
        System.out.println("Logged out successfully.");
        loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
