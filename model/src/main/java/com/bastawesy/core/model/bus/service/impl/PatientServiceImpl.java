package com.bastawesy.core.model.bus.service.impl;

import com.bastawesy.core.model.bus.service.PatientService;
import com.bastawesy.core.model.entity.Patient;
import com.bastawesy.core.model.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PatientServiceImpl extends EntityCRUDServiceImpl<Patient, PatientRepository> implements PatientService {


}
