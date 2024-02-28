package houserenting;

import houserenting.repositories.UserRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HomeDAO homedao = new HomeDAO();
        homedao.start();
    }
}
