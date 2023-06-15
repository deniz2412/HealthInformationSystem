package com.his.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

public class Prescription extends RecursiveTreeObject<Prescription> {
    private int id;
    private String medication;
    private String reason;

    public Prescription(int id, String medication, String reason) {
        this.id = id;
        this.medication = medication;
        this.reason = reason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
