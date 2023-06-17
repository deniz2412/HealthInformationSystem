package com.his.repository;

import com.his.model.Appointment;
import com.his.model.Patient;
import com.his.model.Prescription;
import com.his.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class PrescriptionRepository {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public PrescriptionRepository() {
        // Create the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("hisDB");

        // Create the EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }


    public List<Prescription> getPrescriptions(User user) {
        Long patientID = user.getPatient().getId();
        return entityManager.createQuery("SELECT a FROM Prescription a WHERE patientID=:patientID", Prescription.class)
                .setParameter("patientID",user.getPatient().getId())
                .getResultList();
    }
    public Integer getCount(User user){
        Integer count;
        Long patientID = user.getPatient().getId();
        return entityManager.createQuery("SELECT COUNT(p) FROM Prescription p WHERE patientID=:patientID", Integer.class)
                .setParameter("patientID",user.getPatient().getId()).getSingleResult();
    }

    public Integer getOrderedCount(User user){
        Integer count;
        Long patientID = user.getPatient().getId();
        return entityManager.createQuery("SELECT COUNT(p) FROM Prescription p WHERE patientID=:patientID AND status='Ordered'", Integer.class)
                .setParameter("patientID",user.getPatient().getId()).getSingleResult();
    }
}
