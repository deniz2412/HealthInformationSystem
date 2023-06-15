package com.his.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "doctor")
    private List<Referral> referrals;

    public Doctor(Long id, String name, List<Appointment> appointments, List<Referral> referrals) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
        this.referrals = referrals;
    }

    public Doctor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Referral> getReferrals() {
        return referrals;
    }

    public void setReferrals(List<Referral> referrals) {
        this.referrals = referrals;
    }
}
