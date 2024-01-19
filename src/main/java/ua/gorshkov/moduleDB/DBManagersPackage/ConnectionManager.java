package ua.gorshkov.moduleDB.DBManagersPackage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionManager {
    private static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("my-persistence-unit");
    }
    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}
