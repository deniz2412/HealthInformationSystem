package com.his.controllers;


import com.his.model.Appointment;
import com.his.model.Prescription;
import com.his.repository.AppointmentRepository;
import com.his.util.JFoenixTableUtils;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private JFXTreeTableView tableView;
    private Stage primaryStage;

    AppointmentRepository appointmentRepository = new AppointmentRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAppointments();
    }

    public void showAppointments(){
        tableView.getColumns().clear();
        List<String> columnNames = Arrays.asList("ID", "Patient", "Doctor");
        List<String> propertyNames = Arrays.asList("id", "patientName", "doctorName");

        List<Appointment> appointmentList= appointmentRepository.getAllAppointments();
        //appointmentList.add(new Appointment(56,2512,"Test","Doc"));

        JFoenixTableUtils<Appointment> appointmentTableUtils = new JFoenixTableUtils<>();
        appointmentTableUtils.generateColumns(tableView, columnNames, propertyNames);
        appointmentTableUtils.populateTableData(tableView, appointmentList);

        System.out.println(tableView.getColumns());

    }

    public void showAppointmentsForm(){



    }

    public void showPrescription(){
        tableView.getColumns().clear();
        List<String> columnNames = Arrays.asList("ID", "Medication", "Reason");
        List<String> propertyNames = Arrays.asList("id", "medication", "reason");

        List<Prescription> prescriptionList= new ArrayList<>();
        prescriptionList.add(new Prescription(55,"Test","Doc"));

        JFoenixTableUtils<Prescription> prescriptionJFoenixTableUtils = new JFoenixTableUtils<>();
        prescriptionJFoenixTableUtils.generateColumns(tableView, columnNames, propertyNames);
        prescriptionJFoenixTableUtils.populateTableData(tableView, prescriptionList);

        System.out.println(tableView.getColumns());

    }

    public void showReferrals(){

    }

    public void showPrescriptionOrder(){

    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
}
