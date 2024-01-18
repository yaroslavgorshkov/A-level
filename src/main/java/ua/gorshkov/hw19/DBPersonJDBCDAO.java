package ua.gorshkov.hw19;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBPersonJDBCDAO implements DAO<Person> {
    @Override
    public Person save(Person obj) {
        final String SAVE_SQL = "INSERT INTO person_hw19 (name, salary) VALUES (?, ?)";
        try (Connection connection = ConnectionsManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getSalary());
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
    public Person update(Person obj) {
        final String UPDATE_SQL = "UPDATE person_hw19 SET name = ?, salary = ? WHERE id = ?";
        try (Connection connection = ConnectionsManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setInt(2, obj.getSalary());
            preparedStatement.setLong(3, obj.getId());
            preparedStatement.executeUpdate();
            return obj;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Person obj) {
        final String DELETE_SQL = "DELETE FROM person_hw19 WHERE id = ?";
        try (Connection connection = ConnectionsManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setLong(1, obj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Person> get(Long id) {
        final String GET_SQL = "SELECT * FROM person_hw19 WHERE id = ?";
        try (Connection connection = ConnectionsManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getLong("id"));
                person.setName(resultSet.getString("name"));
                person.setSalary(resultSet.getInt("salary"));
                return Optional.of(person);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Person> getAll() {
        final String GET_ALL_SQL = "SELECT * FROM person_hw19";
        try (Connection connection = ConnectionsManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSetToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Person> resultSetToList(ResultSet resultSet) throws SQLException {
        List<Person> resultList = new ArrayList<>();
        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setName(resultSet.getString("name"));
            person.setSalary(resultSet.getInt("salary"));
            resultList.add(person);
        }
        return resultList;
    }
}
