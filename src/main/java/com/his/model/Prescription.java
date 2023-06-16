package com.his.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription extends RecursiveTreeObject<Prescription> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medication")
    private String medication;
    @Column(name = "reason")
    private String reason;
    @Column(name = "patient-id")
    private Long patientID;

    private String status;
    public Prescription( String medication, String reason, Long patientID, String status) {
        this.medication = medication;
        this.reason = reason;
        this.patientID = patientID;
        this.status = status;
    }

    public Prescription() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPatientID() {
        return patientID;
    }

    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
