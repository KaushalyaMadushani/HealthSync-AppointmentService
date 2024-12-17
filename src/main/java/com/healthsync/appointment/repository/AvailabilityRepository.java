package com.healthsync.appointment.repository;

import com.healthsync.appointment.model.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AvailabilityRepository extends MongoRepository<Availability, String> {
    List<Availability> findByDoctorId(String doctorId);

}
