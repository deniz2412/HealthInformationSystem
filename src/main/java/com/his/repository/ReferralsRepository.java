package com.his.repository;

import com.his.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class ReferralsRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ReferralsRepository() {
        // Create the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("hisDB");

        // Create the EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
   /* public void saveReferral(Patient patient) {
        entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(patient);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }*/
   public List<Referral> getReferrals(User user) {
       return entityManager.createQuery("SELECT a FROM Referral a WHERE patient=:patient", Referral.class)
               .setParameter("patient",user.getPatient())
               .getResultList();
   }

}
