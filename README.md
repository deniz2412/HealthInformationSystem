# Hospital Information System

This project is a Hospital Information System designed to manage patient records, appointments, prescriptions, referrals, and medical data for a hospital and its patients. It provides various features to facilitate efficient healthcare operations and improve patient care.

## Table of Contents
- [Controllers](#controllers)
- [Repositories](#repositories)
- [Models](#models)
- [FXHTML for JavaFX](#fxhtml-for-javafx)
- [Connecting to Database](#connecting-to-database)
- [Installation](#installation)
- [Usage](#usage)
- [Planned Features](#planned-features)

## Controllers

The controllers in this system handle the incoming requests from the user interface and are responsible for processing the data and orchestrating the flow of information within the application. Some of the main controllers used in this project include:

- `PatientController`: Handles the Patient screen and the data received from it. Provides appointments, referrals and prescriptions that are signed for the patient and allows him to order prescriptions as well as schedule appointments.
- `LoginController`: Handles the initial login screen which include user creation and sign in. Uses some methods from password utils class for secure hashing of credentials
- `DoctorController`: Deals with medical records, writing prescriptions and referrals and allows the doctor to see appointments that he has to attend.

## Repositories

The repositories are responsible for data persistence and retrieval. They interact with the database and provide an interface for performing CRUD (Create, Read, Update, Delete) operations. The following repositories are used in this system:

- `PatientRepository`: Provides methods for storing and retrieving patient records from the database along with patient details.
- `AppointmentRepository`: Handles operations related to appointments, such as storing appointment details, retrieving upcoming appointments.
- `DoctorRepository`: Provides CRUD operations for doctors and information about them.
- `PrescriptionRepository`: Provides CRUD operations for prescriptions.
- `ReferralsRepository`: Provides CRUD operations for referrals.
- `UserRepository`: Handles account creating operations as well as login.

## Models

Models represent the entities in the hospital information system and define their structure and behavior. The object relation mapping is done with Hibernate .The main models used in this project include:

- `Patient`: Represents a patient and contains attributes such as name, date of birth.
- `Appointment`: Represents an appointment with attributes like patient ID, date, time.
- `Doctor`: Stores information for a doctor, name, specialty etc.
- `PatientRecord`: Represents details for patients, including patientID and text of the record.
- `Referral`: Represents the referral prescription for patients and doctors.
- `Prescription`: Represents prescriptions for medications.
- `User`: Represents the Account which is seperate from different roles such as patient and doctor.
- `Patient`: Represents the patient.


## FXHTML for JavaFX

The Hospital Information System utilizes JavaFX for the user interface. Below is an example of the FXHTML code used in the Login Screen:

### Login Screen

The login screen is designed using JavaFX, and the following is an example of the FXHTML code used for the login screen:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import com.jfoenix.controls.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.image.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.text.*?>

<AnchorPane id="AnchorPane" prefHeight="478.0" prefWidth="794.0" style="-fx-background-color: #0C1017ff;" stylesheets="@../css/style.css" xmlns="http://javafx.com/javafx/11.0.14-internal" xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.his.controllers.LoginController">
    <!-- ... -->
</AnchorPane>
rPane>
```

### Main Dashboard

The main dashboard of the Hospital Information System also utilizes JavaFX. Here is an example of the FXHTML code used for the main dashboard:

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import com.jfoenix.controls.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.effect.*?>
<?import javafx.scene.image.*?>
<?import javafx.scene.layout.*?>
<?import javafx.scene.text.*?>

<AnchorPane maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="768.0" prefWidth="1360.0" style="-fx-background-color: #02030A;" xmlns="http://javafx.com/javafx/11.0.14-internal" xmlns:fx="http://javafx.com/fxml/1">
    <children>
        <VBox prefHeight="768.0" prefWidth="260.0" style="-fx-background-color: #05071F;" AnchorPane.bottomAnchor="0.0" AnchorPane.topAnchor="0.0">
            <children>
                <ImageView fitHeight="314.0" fitWidth="257.0" opacity="0.36" pickOnBounds="true" preserveRatio="true">
                    <Image url="@../images/logo.jpg" />
                </ImageView>
                <JFXButton onAction="#showAppointmentsForm" prefHeight="55.0" prefWidth="260.0" text="Schedule Appointment" />
                <JFXButton onAction="#showAppointments" prefHeight="55.0" prefWidth="260.0" text="View Scheduled Appointments" />
                <JFXButton onAction="#show

```

### Complete structure of the FXHTML views
- login_screen.fxml
    - AnchorPane
        - AnchorPane (RightPane)
            - ImageView
            - Label
            - JFXButton (SignUp)
            - JFXButton (SignIn)
        - AnchorPane
            - Pane (loginPane)
                - JFXPasswordField
                - JFXButton (LoginButton)
                - JFXTextField
            - Pane (createAccountPane)
                - JFXPasswordField
                - JFXButton (createUserButton)
                - JFXTextField
                - JFXComboBox

- patient.fxml
    - AnchorPane
        - VBox
            - ImageView
            - JFXButton
            - JFXButton
            - JFXButton
            - JFXButton
            - JFXButton
        - StackPane
            - Pane (panelOne)
                - TextField
                - Label
                - HBox
                    - VBox
                        - Label (pendingAppointments)
                        - Label
                    - VBox
                        - Label (pendingPrescriptions)
                        - Label
                    - VBox
                        - Label (pendingReferrals)
                        - Label
                    - VBox
                        - Label (orderedPrescriptions)
                        - Label
            - Pane (panelTwo)
                - JFXTreeTableView


## Connecting to Database

To connect the hospital information system to a database, follow these steps:

1. Open the `persistence.xml` file.
2. Update the with the appropriate database credentials (e.g., hostname, port, username, password).
3. You can also provide it env variables instead.
4. Save the file.

## Installation

To install and set up the hospital information system locally, please follow these steps:

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn install` to install the required dependencies and generate the jar.
4. You need to run it with the following VM options (due to MaterialUI library being outdated):
   --add-exports=javafx.controls/com.sun.javafx.scene.control.behavior=ALL-UNNAMED
   --add-opens=java.base/java.lang.reflect=ALL-UNNAMED
   --add-exports=javafx.base/com.sun.javafx.binding=ALL-UNNAMED
   --add-exports=javafx.graphics/com.sun.javafx.stage=ALL-UNNAMED
   --add-exports=javafx.base/com.sun.javafx.event=ALL-UNNAMED
   --add-exports=javafx.controls/com.sun.javafx.scene.control=ALL-UNNAMED


## Planned Features

Some of the upcoming features that will be added to this project are:
- **Electronic Health Records (EHR)**: Implementing a comprehensive electronic health records system to digitize and manage patient health records securely.
- **Integration with Lab Systems**: Enabling seamless integration with laboratory systems to streamline test orders, results retrieval, and lab data management.
- **Enhanced Reporting**: Adding advanced reporting functionalities to generate insightful analytics, patient statistics, and financial reports.
- **Mobile Application**: Developing a mobile application to provide convenient access to the hospital information system on smartphones and tablets.
- **Automated Appointment Reminders**: Implementing automated appointment reminders through SMS or email notifications to reduce no-shows.
- **Conversion to a web app**: Completly remove JavaFX, implement Spring framework and make a UI in a JS framework
- **Potential Salesforce integration**: Create a plugin for the Salesforce marketplace which will be compatible with the app.

These planned features are aimed at enhancing the functionality, usability, and efficiency of the hospital information system. We're dedicated to continuously improving our system to meet the evolving needs of healthcare providers and patients.

If you have any suggestions or feature requests, we encourage you to submit them through the issue tracker or contribute directly to the project.


