package com.bastawesy.core.model.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "patient")
public class Patient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Patient's birthdate can't by null")
    @Valid
    private LocalDate birthDate;

    @NotNull(message = "Patient's Name can't by null")
    @Valid
    private String name;

    @NotNull(message = "Patient's gender can't by null")
    @Valid
    private String gender;

    public Patient() {
    }

    public Patient(@NotNull(message = "Patient's birthdate can't by null") LocalDate birthDate,
                   @NotNull(message = "Patient's gender can't by null") String name,
                   @NotNull(message = "Patient's gender can't by null") String gender) {
        this.birthDate = birthDate;
        this.setName(name);
        this.gender = gender;
    }

    @PrePersist
    public void validatePrePersist() {
        super.validatePrePersist();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
