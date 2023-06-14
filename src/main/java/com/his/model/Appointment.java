package com.his.model;

import java.time.LocalDateTime;

public class Appointment {

    private int id;
    private LocalDateTime dateTime;
    private UUID patientID;
    private UUID doctorID;
    private String patientName;
    private String doctorName;
}
