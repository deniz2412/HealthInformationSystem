package com.his.repository;

import com.his.model.User;
import jakarta.persistence.*;

import java.util.List;

public class UserRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserRepository() {
        // Create the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("hisDB");

        // Create the EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    public User getUserByUsername(String username) {
        entityManager = getEntityManager();
        try {
            String query = "SELECT u FROM User u WHERE u.username = :username";
            return entityManager.createQuery(query, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        }catch (NoResultException e) {
            return null; // Return null when no result is found
        } finally {
            entityManager.close();
        }

    }

    public List<User> getAllUsers() {
        String query = "SELECT u FROM User u";
        return entityManager.createQuery(query, User.class)
                .getResultList();
    }

    public void saveUser(User user) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void updateUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
    }

    public void deleteUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

}
