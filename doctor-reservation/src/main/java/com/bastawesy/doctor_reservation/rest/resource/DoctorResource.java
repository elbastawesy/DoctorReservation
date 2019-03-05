package com.bastawesy.doctor_reservation.rest.resource;

import com.bastawesy.core.model.bus.service.DoctorService;
import com.bastawesy.core.model.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
@CrossOrigin
public class DoctorResource {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Doctor createDoctor(@RequestBody Doctor doctor) throws Exception {
        return doctorService.saveOrUpdate(doctor);
    }
    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Doctor updateDoctor(@RequestBody Doctor doctor) throws Exception {
        return doctorService.saveOrUpdate(doctor);
    }
    @RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Doctor deleteDoctor(@RequestBody Doctor doctor) throws Exception {
        doctorService.delete(doctor);
        return doctor;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Doctor getById(@RequestParam("id") Long id) throws Exception {
        return doctorService.findById(id);
    }

    @RequestMapping(path = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Doctor> getAllDoctors() throws Exception {
        return doctorService.findAll();
    }


}
