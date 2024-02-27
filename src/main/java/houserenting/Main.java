package houserenting;

import houserenting.repositories.UserRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HomeDAO hmd = new HomeDAO();
        hmd.start();
    }
}
