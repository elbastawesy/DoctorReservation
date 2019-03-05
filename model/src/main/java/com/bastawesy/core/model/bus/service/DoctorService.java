package com.bastawesy.core.model.bus.service;

import com.bastawesy.core.model.entity.Doctor;
import com.bastawesy.core.model.repository.DoctorRepository;


public interface DoctorService extends EntityCRUDService<Doctor,DoctorRepository> {

    Doctor testUpdate(Doctor doctor) throws Exception;
}
