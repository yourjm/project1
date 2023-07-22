package com.zor07;

import com.zor07.model.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Storage {

    private  final String dbUrl;
    private  final String dbUsername;
    private  final String dbPassword;

    public Storage(String dbUrl, String dbUsername, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    public Person get(Integer id) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Выполнение SELECT-запроса
            String sql = "SELECT name, age FROM person where id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            Person person = null;
            // Обработка результатов
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                person =  new Person(name, age);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();

            return person;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    public Integer create(Person person) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Выполнение SELECT-запроса
            String sql = "INSERT INTO person (name, age) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());

            // Обработка результатов
            int rowsAffected = statement.executeUpdate();
            Integer newId = null;
            if (rowsAffected > 0) {
                newId = getLastInsertedId();
            }

            // Закрытие ресурсов
            statement.close();
            connection.close();
            return newId;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    public boolean update(Integer id, Person person) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Выполнение SELECT-запроса
            String sql = "UPDATE person SET name = ?, age = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setInt(3, id);

            // Обработка результатов
            int rowsAffected = statement.executeUpdate();

            // Закрытие ресурсов
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    public boolean delete(Integer id) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Выполнение SELECT-запроса
            String sql = "DELETE FROM person WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            // Обработка результатов
            int rowsAffected = statement.executeUpdate();


            // Закрытие ресурсов
            statement.close();
            connection.close();

            return rowsAffected > 0;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    public HashMap<Integer, Person> getMap() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Выполнение SELECT-запроса
            String sql = "SELECT * FROM person";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            HashMap<Integer, Person>  result = new HashMap<>();
            // Обработка результатов
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                Person person =  new Person(name, age);

                result.put(id, person);
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();

            return result;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    private Integer getLastInsertedId() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Выполнение SELECT-запроса
            String sql = "SELECT max(id) as maxId FROM person";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            Integer result = null;
            // Обработка результатов
            if (resultSet.next()) {
                result = resultSet.getInt("maxId");
            }

            // Закрытие ресурсов
            resultSet.close();
            statement.close();
            connection.close();

            return result;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

}
