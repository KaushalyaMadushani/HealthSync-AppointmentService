package com.healthsync.appointment.repository;

import com.healthsync.appointment.model.Appointment;
//import com.healthsync.appointment.model.Availability;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment,String> {
    List<Appointment> findByDoctorId(String doctorId);
}


