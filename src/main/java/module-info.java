module HealthInformationSystem {
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
    requires com.jfoenix;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;



    opens com.his to javafx.fxml;
    exports com.his;
    opens com.his.controllers to javafx.fxml;
    exports com.his.controllers;
    opens com.his.model to org.hibernate.orm.core;

}
