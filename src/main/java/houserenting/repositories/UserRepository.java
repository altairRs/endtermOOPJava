package houserenting.repositories;

import houserenting.Home;

import java.sql.*;
import java.util.Scanner;

public class UserRepository {
    private Connection connection;
    public UserRepository() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "87654321";
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void listHomes() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM homes");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Address: " + resultSet.getString("address") +
                        ", Price: " + resultSet.getLong("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayHomeDetails(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM homes WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Address: " + resultSet.getString("address") +
                        ", Price: " + resultSet.getDouble("price"));
            } else {
                System.out.println("Home with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertHomeDetails(Home hm) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO public.homes(\n" +
                    "\t address, price, phone_number)\n" +
                    "\tVALUES ( ?, ?, ?);");


            preparedStatement.setString(1,hm.getAddress() );
            preparedStatement.setDouble(2,hm.getPrice());
            preparedStatement.setInt(3,hm.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteHomeDetails(int id) {
        try {
            Connection connection = null;
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.homes WHERE id = ?;");



            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
