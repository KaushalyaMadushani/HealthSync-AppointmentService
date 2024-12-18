package com.healthsync.appointment.service;

import com.healthsync.appointment.exception.AppointmentException;
import com.healthsync.appointment.model.Availability;
import com.healthsync.appointment.repository.AvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public AvailabilityService(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    public Availability createAvailability(Availability availability){
        //check for overlapping availability slots
        List<Availability> existingAvailabilities = availabilityRepository.findByDoctorId(availability.getDoctorId());

        for (Availability existing : existingAvailabilities){
            if (availability.getStartTime().isBefore(existing.getEndTime()) &&
                    availability.getEndTime().isAfter(existing.getStartTime())){
                throw new AppointmentException("Overlapping availability slots detected for the doctor");
            }
        }
        return availabilityRepository.save(availability);

    }

    public List<Availability> getAvailabilitiesByDoctorId(String doctorId){
        return availabilityRepository.findByDoctorId(doctorId);

    }


}
