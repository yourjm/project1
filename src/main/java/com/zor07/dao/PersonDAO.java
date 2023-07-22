package com.zor07.dao;

import com.zor07.model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PersonDAO {

    public Person get(Integer id) {
        String sql = "SELECT name, age FROM person WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Person person = new Person();
                    person.setName(resultSet.getString("name"));
                    person.setAge(resultSet.getInt("age"));
                    return person;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения данных их бд: " + e.getMessage());
        }
    }

    public Integer create(Person person) {
        String sql = "INSERT INTO person (name, age) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return getLastInsertedId();
            } else {
                throw new RuntimeException("Couldn't save person");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка сохранения данных в бд: " + e.getMessage());
        }
    }

    public boolean update(Integer id, Person person) {
        String sql = "UPDATE person SET name = ?, age = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setInt(3, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления данных в бд: " + e.getMessage());
        }
    }

    public boolean delete(Integer id) {
        String sql = "DELETE FROM person WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления данных в бд: " + e.getMessage());
        }
    }

    public HashMap<Integer, Person> findAll() {
        String sql = "SELECT * FROM person";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                HashMap<Integer, Person>  result = new HashMap<>();
                while (resultSet.next()) {
                    Person person = new Person();
                    person.setName(resultSet.getString("name"));
                    person.setAge(resultSet.getInt("age"));
                    result.put(resultSet.getInt("id"), person);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения данных из бд: " + e.getMessage());
        }
    }

    private Integer getLastInsertedId() {
        String sql = "SELECT max(id) as maxId FROM person";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt("maxId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
