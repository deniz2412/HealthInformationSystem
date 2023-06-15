package com.his.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment extends RecursiveTreeObject<Appointment> {

    private int id;
    private int date;
    private String patientName;
    private String doctorName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Appointment(int id, int date, String patientName, String doctorName) {
        this.id = id;
        this.date = date;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }
}
