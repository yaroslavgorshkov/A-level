package ua.gorshkov.moduleDB.DBManagersPackage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionsManager {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres", "20052005");
    }
}
