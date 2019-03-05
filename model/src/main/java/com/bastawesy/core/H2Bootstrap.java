package com.bastawesy.core;

import com.bastawesy.core.model.bus.service.DoctorService;
import com.bastawesy.core.model.bus.service.PatientService;
import com.bastawesy.core.model.bus.service.ReservationService;
import com.bastawesy.core.model.entity.Doctor;
import com.bastawesy.core.model.entity.Patient;
import com.bastawesy.core.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class H2Bootstrap implements CommandLineRunner {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ReservationService reservationService;


    @Autowired
    public H2Bootstrap(PatientService patientService, DoctorService doctorService, ReservationService reservationService) {
        Assert.notNull(patientService, "patientService must not be null!");
        this.patientService = patientService;

        Assert.notNull(doctorService, "doctorService must not be null!");
        this.doctorService = doctorService;

        Assert.notNull(reservationService, "reservationService must not be null!");
        this.reservationService = reservationService;
    }

    @Override
    public void run(String... args) throws Exception {
//        try {
//            createTempDoctors();
//            createTempPatients();
//            createTemptReservation();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    private void createTempDoctors() throws Exception {
        // create new doctor
        Doctor doctor = new Doctor("ahmed", "test", "masnoura", "mansoura university");
        doctorService.saveOrUpdate(doctor);
        doctor = new Doctor("Ali", "foo", "masnoura", "mansoura university");
        doctorService.saveOrUpdate(doctor);

    }

    private void createTempPatients() throws Exception {
        // create new patient
        Patient patient = new Patient(LocalDate.of(1992, 6, 24),"ahmed", "test");
        patientService.saveOrUpdate(patient);
//        throw new NullPointerException();
    }

    private void createTemptReservation() throws Exception {

        Doctor doctor = doctorService.findById(1L);
        System.out.println(doctor.getSpeciality());
//        doctor.setSpeciality("foo");
        doctorService.testUpdate(doctor);
        doctor = doctorService.findById(1L);
        System.out.println(doctor.getSpeciality());
        Patient patient = patientService.findAll().iterator().next();
        // create new reservation
        Reservation reservation = new Reservation(LocalDateTime.of(2018, 8, 31, 14, 51), doctor, patient, "just complain");
        reservationService.saveOrUpdate(reservation);

    }
}
