package houserenting;

import houserenting.repositories.UserRepository;
import houserenting.controllers.HomeController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        Scanner scanner = new Scanner(System.in);

        HomeController homeController = new HomeController(userRepository, scanner);
        homeController.start();
    }
}
