package com.his.controllers;

import com.jfoenix.controls.*;
import com.his.model.User;
import com.his.repository.UserRepository;
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
    private JFXTextField passwordTextField;
    @FXML
    private JFXTextField newUsernameField;
    @FXML
    private JFXPasswordField newPasswordField;
    @FXML
    private Pane loginPane;
    @FXML
    private Pane createAccountPane;
    private Stage primaryStage;

    @FXML
    private JFXComboBox<String> comboboxRole;

    UserRepository userRepository = new UserRepository();
    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboboxRole.getItems().add("Admin");
        comboboxRole.getItems().add("Doctor");
        comboboxRole.getItems().add("Pharmacist");
        comboboxRole.getItems().add("Patient");
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        //Auth
        User user = userRepository.getUserByUsername(username);

        if (user != null) {
            String storedPasswordHash = user.getPasswordHash();
            byte[] salt = user.getSalt();
            boolean passwordMatch = PasswordUtil.verifyPassword(password, storedPasswordHash, salt);
            if (passwordMatch) {
                String role = user.getRole();
                showMainApplicationScreen(role);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid password");
            }
        }else{
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username");
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

        System.out.println(role);
        //Check if user exists
        User existingUser = userRepository.getUserByUsername(username);
        if(existingUser!=null){
            showAlert(Alert.AlertType.ERROR, "Account Creation Failed", "Username already exists.");
        }

        User newUser = userRepository.createUser(username, password, role);
        if (newUser != null) {
            showAlert(Alert.AlertType.INFORMATION, "Account Creation Successful", "Account created successfully!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Account Creation Failed", "Failed to create the account. Please try again.");
        }
        }

        private void showMainApplicationScreen(String role){

        }

        private void showAlert(Alert.AlertType alertType, String title, String message){
            Alert alert = new Alert(alertType);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

