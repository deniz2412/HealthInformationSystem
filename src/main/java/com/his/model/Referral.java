package com.his.model;

import com.his.model.Doctor;
import com.his.model.Patient;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import jakarta.persistence.*;


@Entity
@Table(name = "referrals")
public class Referral extends RecursiveTreeObject<Referral> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "referral_reason")
    private String referralReason;
    private String doctorName;
    private String patientName;
    public Referral() {
    }

    public Referral(Long id, Patient patient, Doctor doctor, String referralReason) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.referralReason = referralReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getReferralReason() {
        return referralReason;
    }

    public void setReferralReason(String referralReason) {
        this.referralReason = referralReason;
    }
}
