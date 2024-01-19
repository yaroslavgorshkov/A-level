package ua.gorshkov.moduleDB.DAOPackage.JDBCDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.Entities.Account;
import ua.gorshkov.moduleDB.Entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBAccountJDBCDAO implements DAO<Account> {

    @Override
    public Account save(Account obj) {
        final String SAVE_SQL = "INSERT INTO accounts (bank_card_number, start_money_amount) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getBankCardNumber());
            preparedStatement.setDouble(2, obj.getStartMoneyAmount());
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
    public Account update(Account obj) {
        final String UPDATE_SQL = "UPDATE accounts SET bank_card_number = ?, start_money_amount = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, obj.getBankCardNumber());
            preparedStatement.setDouble(2, obj.getStartMoneyAmount());
            preparedStatement.setLong(3, obj.getId());
            preparedStatement.executeUpdate();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Account obj) {
        final String DELETE_SQL = "DELETE FROM accounts WHERE id = ?";
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
    public Optional<Account> get(Long id) {
        final String GET_SQL = "SELECT * FROM accounts WHERE id = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getLong("id"));
                account.setBankCardNumber(resultSet.getString("bank_card_number"));
                account.setStartMoneyAmount(resultSet.getDouble("start_money_amount"));
                return Optional.of(account);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Account> getAll() {
        final String GET_ALL_SQL = "SELECT * FROM accounts";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005")) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Account> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Account account = new Account();
            account.setId(resultSet.getLong("id"));
            account.setBankCardNumber(resultSet.getString("bank_card_number"));
            account.setStartMoneyAmount(resultSet.getDouble("start_money_amount"));
            resultList.add(account);
        }
        return resultList;
    }
}
