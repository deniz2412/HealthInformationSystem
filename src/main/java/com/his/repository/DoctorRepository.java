package com.his.repository;

import com.his.model.Doctor;
import com.his.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DoctorRepository {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    public DoctorRepository() {
        // Create the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("hisDB");

        // Create the EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }
    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    public void saveDoctor(Doctor doctor) {
        entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(doctor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
