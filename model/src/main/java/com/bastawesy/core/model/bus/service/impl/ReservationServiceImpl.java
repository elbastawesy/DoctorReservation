package com.bastawesy.core.model.bus.service.impl;

import com.bastawesy.core.model.bus.service.ReservationService;
import com.bastawesy.core.model.entity.Reservation;
import com.bastawesy.core.model.repository.ReservationRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ReservationServiceImpl extends EntityCRUDServiceImpl<Reservation, ReservationRespository> implements ReservationService {


}
