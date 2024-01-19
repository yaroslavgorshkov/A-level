package ua.gorshkov.moduleDB.DAOPackage.JDBCDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.Entities.Enums.OperationCategories;
import ua.gorshkov.moduleDB.Entities.Operation;
import ua.gorshkov.moduleDB.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBOperationJBBCDAO implements DAO<Operation> {
    @Override
    public Operation save(Operation obj) {
        final String SAVE_SQL = "INSERT INTO operations (operation_category, amount_of_money, category) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getOperationCategory().toString());///???
            preparedStatement.setDouble(2, obj.getAmountOfMoney());
            preparedStatement.setString(3, obj.getCategory());
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
    public Operation update(Operation obj) {
        final String UPDATE_SQL = "UPDATE operations SET operation_category = ?, amount_of_money = ?, category = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, obj.getOperationCategory().toString()); ///???
            preparedStatement.setDouble(2, obj.getAmountOfMoney());
            preparedStatement.setString(3, obj.getCategory());
            preparedStatement.setLong(4, obj.getId());
            preparedStatement.executeUpdate();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Operation obj) {
        final String DELETE_SQL = "DELETE FROM operations WHERE id = ?";
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
    public Optional<Operation> get(Long id) {
        final String GET_SQL = "SELECT * FROM operations WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Operation operation = new Operation();
                operation.setId(resultSet.getLong("id"));
                operation.setOperationCategory(OperationCategories.valueOf(resultSet.getString("operation_category"))); ///???
                operation.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
                operation.setCategory(resultSet.getString("category"));
                return Optional.of(operation);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Operation> getAll() {
        final String GET_ALL_SQL = "SELECT * FROM operations";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Operation> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Operation> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Operation operation = new Operation();
            operation.setId(resultSet.getLong("id"));
            operation.setOperationCategory(OperationCategories.valueOf(resultSet.getString("operation_category"))); ///???
            operation.setAmountOfMoney(resultSet.getDouble("amount_of_money"));
            operation.setCategory(resultSet.getString("category"));
            resultList.add(operation);
        }
        return resultList;
    }
}
