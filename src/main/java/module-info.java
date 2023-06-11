module com.his.healthinformationsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires com.jfoenix;


    opens com.his to javafx.fxml;
    exports com.his;
    opens com.his.controllers to javafx.fxml;
    exports com.his.controllers;

}