package houserenting.controllers;

import houserenting.repositories.UserRepository;
import houserenting.models.Home;
import java.util.Scanner;

public class HomeController {
    private final UserRepository userRepository;
    private final Scanner scanner;
    private boolean loggedIn;

    public HomeController(UserRepository userRepository, Scanner scanner) {
        this.userRepository = userRepository;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            if (!loggedIn) {
                System.out.println("Welcome to House Renting Application");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter option: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option. Please enter 1, 2, or 3.");
                        break;
                }
            } else {
                System.out.println();
                System.out.println("Welcome to House Renting Application");
                System.out.println("Select option:");
                System.out.println("1. View houses");
                System.out.println("2. Add a new house");
                System.out.println("3. Delete a house");
                System.out.println("4. Display additional information by ID");
                System.out.println("5. Log out");
                System.out.println("6. Exit");
                System.out.println();
                try {
                    System.out.print("Enter option (1-5): ");
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            userRepository.listHomes();
                            break;
                        case 2:
                            addNewHouse();
                            break;
                        case 3:
                            deleteHouse();
                            break;
                        case 4:
                            displayAdditionalInfo();
                            break;
                        case 5:
                            logout();
                            break;
                        case 6:
                            System.out.println("Exiting the program. Goodbye!");
                            return;
                        default:
                            System.out.println("Invalid option. Please enter a number between 1 and 6.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    scanner.nextLine(); // Clear input buffer
                }
            }
        }
    }

    private void register() {
        System.out.println("Registration");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        boolean registered = userRepository.registerUser(username, password);
        if (registered) {
            System.out.println("Registration successful.");
            loggedIn = true;
        } else {
            System.out.println("Registration failed.");
        }
    }

    private void login() {
        System.out.println("Login");
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        boolean authenticated = userRepository.authenticateUser(username, password);
        if (authenticated) {
            System.out.println("Login successful.");
            loggedIn = true;
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private void logout() {
        System.out.println("Logged out successfully.");
        loggedIn = false;
    }

    private void addNewHouse() {
        System.out.println("Add a New House");
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Home newHome = new Home(0, address, price, phoneNumber);
        userRepository.insertHomeDetails(newHome);
        System.out.println("New house added successfully.");
    }

    private void deleteHouse() {
        System.out.print("Enter ID of the house to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        userRepository.deleteHomeDetails(id);
        System.out.println("House with ID " + id + " deleted successfully.");
    }

    private void displayAdditionalInfo() {
        System.out.print("Enter ID of the house to display additional information: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        userRepository.displayHomeDetails(id);
    }
}
