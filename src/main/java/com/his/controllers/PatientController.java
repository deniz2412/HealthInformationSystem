package com.his.controllers;


import com.his.model.*;
import com.his.repository.AppointmentRepository;
import com.his.repository.PrescriptionRepository;
import com.his.repository.ReferralsRepository;
import com.his.util.JFoenixTableUtils;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private Label pendingAppointments;
    @FXML
    private Label pendingPrescriptions;
    @FXML
    private Label pendingReferrals;
    @FXML
    private Label orderedPrescriptions;

    @FXML
    private JFXTreeTableView tableView;
    private Stage primaryStage;

    private User user;
    AppointmentRepository appointmentRepository = new AppointmentRepository();
    ReferralsRepository referralsRepository = new ReferralsRepository();
    PrescriptionRepository prescriptionRepository = new PrescriptionRepository();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showAppointments();
        refreshLabels();
    }

    public PatientController(User user) {
        this.user = user;
    }

    public PatientController() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showAppointments(){
        tableView.getColumns().clear();
        List<String> columnNames = Arrays.asList("ID", "Patient", "Doctor","Date","Time");
        List<String> propertyNames = Arrays.asList("id", "patientName", "doctorName","date", "time");

        List<Appointment> appointmentList= appointmentRepository.getAllAppointments(user);
        for (Appointment appointment: appointmentList) {
            appointment.setPatientName(appointment.getPatient().getName());
            appointment.setDoctorName(appointment.getDoctor().getName());
        };
        JFoenixTableUtils<Appointment> appointmentTableUtils = new JFoenixTableUtils<>();
        appointmentTableUtils.generateColumns(tableView, columnNames, propertyNames);
        appointmentTableUtils.populateTableData(tableView, appointmentList);

    }

    public void showAppointmentsForm(){
        refreshLabels();



    }

    public void showPrescription(){
        refreshLabels();
        tableView.getColumns().clear();
        List<String> columnNames = Arrays.asList("ID", "Medication","Reason","Status");
        List<String> propertyNames = Arrays.asList("id", "medication", "reason", "status");

        List<Prescription> prescriptionList= prescriptionRepository.getPrescriptions(user);
        JFoenixTableUtils<Prescription> prescriptionJFoenixTableUtils = new JFoenixTableUtils<>();
        prescriptionJFoenixTableUtils.generateColumns(tableView, columnNames, propertyNames);
        prescriptionJFoenixTableUtils.populateTableData(tableView, prescriptionList);

    }

    public void showReferrals(){
        refreshLabels();
        tableView.getColumns().clear();
        List<String> columnNames = Arrays.asList("ID", "Doctor","Reason");
        List<String> propertyNames = Arrays.asList("id","doctorname", "refferalReason");

        List<Referral> referralList= referralsRepository.getReferrals(user);
        for (Referral referral: referralList) {
            referral.setPatientName(referral.getPatient().getName());
            referral.setDoctorName(referral.getDoctor().getName());
        };
        JFoenixTableUtils<Referral> referralJFoenixTableUtils = new JFoenixTableUtils<>();
        referralJFoenixTableUtils.generateColumns(tableView, columnNames, propertyNames);
        referralJFoenixTableUtils.populateTableData(tableView, referralList);

    }

    public void refreshLabels(){
        pendingAppointments.setText(appointmentRepository.getCount(user).toString());
        pendingPrescriptions.setText(prescriptionRepository.getCount(user).toString());
        pendingReferrals.setText(referralsRepository.getCount(user).toString());
        orderedPrescriptions.setText(prescriptionRepository.getOrderedCount(user).toString());

    }
    public void showPrescriptionOrder(){
        refreshLabels();

    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }
}
