package com.bastawesy.core.model.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "doctor")
public class Doctor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Doctor's name can't by null")
    @Valid
    private String name;

    @NotNull(message = "Doctor's speciality can't by null")
    @Valid
    private String speciality;

    @NotNull(message = "Doctor's address can't by null")
    @Valid
    private String address;

    @NotNull(message = "Doctor's education can't by null")
    @Valid
    private String education;

    public Doctor() {
    }

    public Doctor(@NotNull String name, @NotNull String speciality, @NotNull String address, @NotNull String education) {
        this.setName(name);
        this.setSpeciality(speciality);
        this.setAddress(address);
        this.setEducation(education);
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
