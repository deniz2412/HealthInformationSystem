package com.his.controllers;

import com.his.model.Doctor;
import com.his.model.Patient;
import com.his.repository.DoctorRepository;
import com.his.repository.PatientRepository;
import com.his.repository.UserRepository;
import com.jfoenix.controls.*;
import com.his.model.User;
import com.his.util.PasswordUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private JFXTextField usernameTextField;
    @FXML
    private JFXPasswordField passwordTextField;
    @FXML
    private JFXTextField newUsernameField;
    @FXML
    private JFXPasswordField newPasswordField;
    @FXML
    private Pane loginPane;
    @FXML
    private Pane createAccountPane;
    private Stage primaryStage;
    private UserRepository userRepository = new UserRepository();
    private DoctorRepository doctorRepository = new DoctorRepository();
    private PatientRepository patientRepository = new PatientRepository();
    @FXML
    private JFXComboBox<String> comboboxRole;

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxRole.getItems().add("Doctor");
        comboboxRole.getItems().add("Patient");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            String storedPasswordHash = user.getPasswordHash();
            byte[] salt = user.getSalt();
            boolean passwordMatch = PasswordUtil.verifyPassword(password, storedPasswordHash, salt);
            if (passwordMatch) {
                String role = user.getRole();
                showMainApplicationScreen(role, event,user);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password");
            }
        }else{
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password");
            }
    }
    public void showCreateAccountPane(ActionEvent event) {
        loginPane.setVisible(false);
        createAccountPane.setVisible(true);
    }
    public void showLoginPane(ActionEvent event) {
        createAccountPane.setVisible(false);
        loginPane.setVisible(true);
    }
    @FXML
    private void handleUserCreation(ActionEvent event){
        String username = newUsernameField.getText();
        String password = newPasswordField.getText();
        String role = comboboxRole.getValue();


        //Check if user exists
        User existingUser = userRepository.getUserByUsername(username);
        if(existingUser!=null){
            showAlert(Alert.AlertType.ERROR, "Account Creation Failed", "Username already exists.");
        }else{
            byte[] salt = PasswordUtil.generateSalt();
            String passwordHash = PasswordUtil.hashPassword(password,salt);
            User newUser = new User(username, passwordHash,salt, role);
            try{
                userRepository.saveUser(newUser);
                if(newUser.getRole().equals("Doctor")){
                    Doctor doctor = new Doctor();
                    doctor.setUser(newUser);
                    doctor.setName(username.toUpperCase());
                    doctorRepository.saveDoctor(doctor);
                }else{
                    Patient patient = new Patient();
                    patient.setUser(newUser);
                    patient.setName(username.toUpperCase());
                    patientRepository.savePatient(patient);
                }
                showAlert(Alert.AlertType.INFORMATION, "Account Creation Successful", "Account created successfully!");
            } catch(Exception e)
            {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Account Creation Failed", "Failed to create the account. Please try again.");
            }
        }
    }


        private void showMainApplicationScreen(String role, ActionEvent event, User user){
            try {
                FXMLLoader loader = null;
                if (role.equals("Patient")) {
                    loader = new FXMLLoader(getClass().getResource("/fxml/patient.fxml"));
                    PatientController patientController = new PatientController(user);
                    loader.setController(patientController);
                } else if (role.equals("Doctor")) {
                    loader = new FXMLLoader(getClass().getResource("/fxml/doctor.fxml"));
                    DoctorController doctorController = new DoctorController(user);
                    loader.setController(doctorController);
                } else {
                    showAlert(Alert.AlertType.ERROR, "Unkown role", "You have been assigned an unknown role, contact IT!");
                }
                Parent nextPage = loader.load();

                Scene scene = new Scene(nextPage);

                // Get the current stage
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // Set the new scene on the stage
                currentStage.setScene(scene);
                currentStage.show();

            }
            catch (IOException e) {
                e.printStackTrace();
                // Handle any exceptions that occur while loading the page
            }
        }

        private void showAlert(Alert.AlertType alertType, String title, String message){
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

