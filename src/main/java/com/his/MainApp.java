package com.his;

import com.his.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        org.burningwave.core.assembler.StaticComponentContainer.Modules.exportAllToAll();
        //login
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();

        LoginController loginController = loader.getController();
        loginController.setPrimaryStage(primaryStage);
       // PatientController patientController = loader.getController();
       // patientController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Asclepius Hospital Information System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}