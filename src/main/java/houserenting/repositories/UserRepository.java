package houserenting.repositories;

import houserenting.models.Home;
import houserenting.data.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class UserRepository {
    private Connection connection;

    public UserRepository() {
        try {
            this.connection = DatabaseUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(username, password) VALUES (?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void listHomes() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM homes");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String address = resultSet.getString("address");
                double price = resultSet.getDouble("price");

                DecimalFormat df = new DecimalFormat("#,###.##");
                String formattedPrice = df.format(price);

                System.out.println("ID: " + id +
                        ", Address: " + address +
                        ", Price: " + formattedPrice);
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
                        ", Price: " + formatPrice(resultSet.getDouble("price")) +
                        ", Phone number: " + resultSet.getString("phone_number"));
            } else {
                System.out.println("Home with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        return df.format(price);
    }

    public void insertHomeDetails(Home home) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO homes(address, price, phone_number) VALUES (?, ?, ?)");
            preparedStatement.setString(1, home.getAddress());
            preparedStatement.setDouble(2, home.getPrice());
            preparedStatement.setString(3, home.getPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteHomeDetails(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM homes WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
