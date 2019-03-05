package com.bastawesy.core.model.repository;

import com.bastawesy.core.model.entity.Doctor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    @Override
    @Modifying
    @Query(value = "select count(d) from Doctor d where d.id>10")
    long count();
}
