package com.bastawesy.doctor_reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bastawesy")
public class DoctorReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorReservationApplication.class, args);
    }
}
