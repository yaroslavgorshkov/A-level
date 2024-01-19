package ua.gorshkov.moduleDB.DAOPackage;

import jakarta.persistence.EntityManager;
import ua.gorshkov.moduleDB.DBManagersPackage.ConnectionManager;
import ua.gorshkov.moduleDB.Entities.Operation;

import java.util.List;
import java.util.Optional;

public class DBOperationHibernateDAO implements DAO<Operation> {
    @Override
    public Operation save(Operation obj) {
        try(EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
        try(EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
        try(EntityManager entityManager = ConnectionManager.getEntityManager()) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(obj);
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
        try (EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
        try(EntityManager entityManager = ConnectionManager.getEntityManager()) {
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
