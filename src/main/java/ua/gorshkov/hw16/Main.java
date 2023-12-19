package ua.gorshkov.hw16;

import javax.sound.midi.Soundbank;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "20052005");
            addContact(connection, 5, "kolya.nikolayevich@gmail.com", "email address");
            addEmployee(connection, 3, "Kolya", "Nikolayevich", 5);
            addEmployee(connection, 6, "Volodimir", "Klitchko", 5);
            showAllEmployees(connection);

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void addContact(Connection connection, int id, String contact, String contactType)
            throws SQLException {
        String query = "INSERT INTO contact (id, contact, contact_type) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, contact);
            preparedStatement.setString(3, contactType);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addEmployee(Connection connection, int id, String firstName, String lastName, int contactId)
            throws SQLException {
        String query = "INSERT INTO employee (id, first_name, last_name, contact_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setInt(4, contactId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showAllEmployees (Connection connection) throws SQLException {
        String query = "SELECT * FROM employee";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Employees:");
            while (resultSet.next()) {
                System.out.println("ID = " + resultSet.getInt("id") + ", first name = "
                        + resultSet.getString("first_name") + ", second name = "
                        + resultSet.getString("last_name") + ", contact ID = "
                        + resultSet.getInt("contact_id"));
                System.out.println();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
