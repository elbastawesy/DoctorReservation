package com.bastawesy.core.model.repository;

import com.bastawesy.core.model.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRespository extends CrudRepository<Reservation, Long> {
}
