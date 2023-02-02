package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    private void executeUPDT(String sql) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void createUsersTable() {
        executeUPDT("CREATE TABLE IF NOT EXISTS users (id BIGINT primary key auto_increment, " +
                "name VARCHAR(10), lastname VARCHAR(10), age TINYINT)");
    }

    @Override
    public void dropUsersTable() {
        executeUPDT("DROP TABLE IF EXISTS users");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> users = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return users;
        }
    }

    @Override
    public void cleanUsersTable() {
        executeUPDT("TRUNCATE TABLE users");
    }
}
