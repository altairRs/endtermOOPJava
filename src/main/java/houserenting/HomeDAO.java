package houserenting;

import houserenting.repositories.UserRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class HomeDAO {

    public HomeDAO(){

    }

    public void start(){
        UserRepository userepo = new UserRepository();
        while (true) {
            System.out.println("1. View houses");
            System.out.println("2. Sell house");
            System.out.println("3. Delete house you already sold");
            System.out.println("4. Display additional information by id");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);

            int input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1:
                    userepo.listHomes();
                    break;
                case 2:
                    System.out.print("Enter adress: ");
                    String adress = scanner.nextLine();
                    System.out.print("Enter price: ");
                    int price = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter seller phone: ");
                    System.out.println("Enter homenuber");
                    Home hm = new Home(0,adress, price);
                    String phone_number = scanner.nextLine();
                    userepo.insertHomeDetails(hm);
                    break;
                case 3:
                    System.out.print("Enter id of the house you sold: ");
                    int idd = Integer.parseInt(scanner.nextLine());
                    userepo.deleteHomeDetails(idd);
                    break;
                case 4:
                    System.out.print("Enter id of the house to get additional info: ");
                    int houseId = Integer.parseInt(scanner.nextLine());
                    userepo.displayHomeDetails(houseId);
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }



//    public static void main(String[] args) {
//        HomeDAO homeDAO = new HomeDAO();
//        homeDAO.listHomes();
//        homeDAO.displayHomeDetails(1);
//    }
}