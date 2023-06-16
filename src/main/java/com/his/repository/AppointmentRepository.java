package com.his.repository;

import com.his.model.Appointment;
import com.his.model.Patient;
import com.his.model.User;
import jakarta.persistence.*;

import java.util.List;

public class AppointmentRepository {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public AppointmentRepository() {
        // Create the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("hisDB");

        // Create the EntityManager
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void save(Appointment appointment) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(appointment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public List<Appointment> getAllAppointments(User user) {

        return entityManager.createQuery("SELECT a FROM Appointment a WHERE patient=:patient", Appointment.class)
                .setParameter("patient",user.getPatient())
                .getResultList();
    }


    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

}
