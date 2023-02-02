package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private void executeUPDT(String sql) {
        try (Statement statement = Util.getConnectionDB().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUsersTable() {
        executeUPDT("CREATE TABLE IF NOT EXISTS users (id BIGINT primary key auto_increment, " +
                "name VARCHAR(10), lastname VARCHAR(10), age TINYINT)");
    }

    public void dropUsersTable() {
        executeUPDT("DROP TABLE IF EXISTS users");
    }

    public void saveUser(String name, String lastName, byte age) {
        executeUPDT("INSERT INTO users (name, lastname, age) " +
                "VALUES ('" + name + "','" + lastName + "','" + age + "')");
        System.out.println("User с именем " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) {
        executeUPDT("DELETE FROM users WHERE Id = '" + id + "'");
    }

    public List<User> getAllUsers() {
        try (Statement statement = Util.getConnectionDB().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            List<User> list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3),
                        resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                list.add(user);
            }
            return list;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanUsersTable() {
        executeUPDT("TRUNCATE TABLE users");
    }
}