package ua.gorshkov.moduleDB.DAOPackage.HibernateDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import ua.gorshkov.moduleDB.DAOPackage.DAO;
import ua.gorshkov.moduleDB.DBManagersPackage.ConnectionManager;
import ua.gorshkov.moduleDB.Entities.Operation;

import java.util.List;
import java.util.Optional;

public class DBOperationHibernateDAO implements DAO<Operation> {
    @Override
    public Operation save(Operation obj) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.persist(obj);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public Operation update(Operation obj) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                entityManager.merge(obj);
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public void delete(Operation obj) {
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                /*entityManager.remove(entityManager.contains(obj) ? obj : entityManager.merge(obj));*/
                String hql = "DELETE FROM Operation WHERE id = :entityId";
                entityManager.createQuery(hql)
                        .setParameter("entityId", obj.getId())
                        .executeUpdate();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Optional<Operation> get(Long id) {
        Optional<Operation> obj;
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                Operation operation = entityManager.find(Operation.class, id);
                entityManager.getTransaction().commit();
                obj = Optional.ofNullable(operation);
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    @Override
    public List<Operation> getAll() {
        List<Operation> operationList;
        String selectAllQuery = "select o from Operation o";
        try(EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
            EntityManager entityManager = entityManagerFactory.createEntityManager()
        ) {
            try {
                entityManager.getTransaction().begin();
                operationList = entityManager.createQuery(selectAllQuery, Operation.class).getResultList();
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                entityManager.getTransaction().rollback();
                throw new RuntimeException(e);
            }
        }
        return operationList;
    }
}
