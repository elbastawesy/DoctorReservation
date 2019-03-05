package com.bastawesy.doctor_reservation.rest.resource;

import com.bastawesy.core.model.bus.service.ReservationService;
import com.bastawesy.core.model.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationResource {


    @Autowired
    ReservationService reservationService;

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reservation createDoctor(@RequestBody Reservation reservation) throws Exception {
        return reservationService.saveOrUpdate(reservation);
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reservation updateDoctor(@RequestBody Reservation reservation) throws Exception {
        return reservationService.saveOrUpdate(reservation);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Reservation getById(@RequestParam("id") Long id) throws Exception {
        return reservationService.findById(id);
    }

    @RequestMapping(path = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Reservation> getAllReservations() throws Exception {
        return reservationService.findAll();
    }

}
