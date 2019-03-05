package com.bastawesy.doctor_reservation.rest.resource;

import com.bastawesy.core.model.bus.service.PatientService;
import com.bastawesy.core.model.entity.Patient;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TransferQueue;

@RestController
@RequestMapping("/patient")
@CrossOrigin
public class PatientResource {

    BlockingDeque blockingDeque;
    BlockingQueue blockingQueue;
    Deque deque;
    List list;
    NavigableSet navigableSet;
    Queue queue;
    Set set;
    SortedSet sortedSet;
    TransferQueue transferQueue;

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Patient createDoctor(@RequestBody Patient patient) throws Exception {
        return patientService.saveOrUpdate(patient);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Patient updateDoctor(@RequestBody Patient patient) throws Exception {
        return patientService.saveOrUpdate(patient);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Patient getById(@PathVariable("id") Long id) throws Exception {
        return patientService.findById(id);
    }

    @RequestMapping(path = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Patient> getAllPatients() throws Exception {
        return patientService.findAll();
    }

}
