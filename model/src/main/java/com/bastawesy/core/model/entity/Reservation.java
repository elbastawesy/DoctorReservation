package com.bastawesy.core.model.entity;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "Doctor can't be null")
    @Valid
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "Patient can't be null")
    private Patient patient;

    @NotNull(message = "breifComplain can't be null")
    private String breifComplain;

    public Reservation() {
    }

    public Reservation( LocalDateTime dateTime, Doctor doctor, Patient patient, String breifComplain) {
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.patient = patient;
        this.breifComplain = breifComplain;
    }

    @PrePersist
    public void validatePrePersist() {
        super.validateEntity();
    }


    @PreUpdate
    public void validatePreUpdate() {
        super.validateEntity();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getBreifComplain() {
        return breifComplain;
    }

    public void setBreifComplain(String breifComplain) {
        this.breifComplain = breifComplain;
    }
}
