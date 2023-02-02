package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.*;

public class Util {

    public static Connection getConnectionDB() throws ClassNotFoundException, SQLException {
        String userName = "root";
        String password = "12345";
        String urlDB = "jdbc:mysql://localhost:3306/mydb";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(urlDB, userName, password);
    }

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.Driver", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "12345")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .addAnnotatedClass(User.class);
        return configuration.buildSessionFactory(new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build());
    }
}
