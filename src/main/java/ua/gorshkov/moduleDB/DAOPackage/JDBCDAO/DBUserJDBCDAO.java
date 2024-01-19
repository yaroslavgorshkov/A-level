package ua.gorshkov.moduleDB.DAOPackage.JDBCDAO;

import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBUserJDBCDAO implements DAO<User> {
    @Override
    public User save(User obj) {
        final String SAVE_SQL = "INSERT INTO users (name, surname) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getSurname());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                obj.setId(generatedKeys.getLong("id"));
            }
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User update(User obj) {
        final String UPDATE_SQL = "UPDATE users SET name = ?, surname = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getSurname());
            preparedStatement.setLong(3, obj.getId());
            preparedStatement.executeUpdate();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(User obj) {
        final String DELETE_SQL = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> get(Long id) {
        final String GET_SQL = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        final String GET_ALL_SQL = "SELECT * FROM users";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> resultSetToList(ResultSet resultSet) throws SQLException {
        List<User> resultList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            resultList.add(user);
        }
        return resultList;
    }
}
