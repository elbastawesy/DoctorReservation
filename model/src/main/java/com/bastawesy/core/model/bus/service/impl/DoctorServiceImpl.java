package com.bastawesy.core.model.bus.service.impl;

import com.bastawesy.core.model.bus.service.DoctorService;
import com.bastawesy.core.model.entity.Doctor;
import com.bastawesy.core.model.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DoctorServiceImpl extends EntityCRUDServiceImpl<Doctor, DoctorRepository> implements DoctorService {

    @Override
    public Doctor testUpdate(Doctor doctor) throws Exception {
         doctor = super.findById(1L);
//        doctor.setSpeciality("foo");
        doctor.setSpeciality("foo");
        return doctor;
    }

}
