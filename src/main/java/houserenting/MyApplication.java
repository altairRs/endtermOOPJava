package houserenting;

import houserenting.repositories.UserRepository;
import houserenting.controllers.HomeController;

import java.util.Scanner;

public class MyApplication {
    private final UserRepository userRepository;
    private final Scanner scanner;

    public MyApplication() {
        this.userRepository = new UserRepository();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        HomeController homeController = new HomeController(userRepository, scanner);
        homeController.start();
    }

    public static void main(String[] args) {
        MyApplication app = new MyApplication();
        app.start();
    }
}
