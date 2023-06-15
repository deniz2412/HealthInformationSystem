package com.his.model;

import jakarta.persistence.*;


@Entity
@Table(name = "patient_records")
public class PatientRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Column(name = "record_text")
    private String recordText;

    public PatientRecord(Long id, Patient patient, String recordText) {
        this.id = id;
        this.patient = patient;
        this.recordText = recordText;
    }

    public PatientRecord() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getRecordText() {
        return recordText;
    }

    public void setRecordText(String recordText) {
        this.recordText = recordText;
    }
}
