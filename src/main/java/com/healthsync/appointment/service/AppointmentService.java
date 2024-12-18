package com.healthsync.appointment.service;

import com.healthsync.appointment.model.Appointment;
import com.healthsync.appointment.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
//    private final AvailabilityRepository availabilityRepository;
//
//    public AppointmentService(AppointmentRepository appointmentRepository, AvailabilityRepository availabilityRepository) {
//       this.appointmentRepository = appointmentRepository;
//        this.availabilityRepository = availabilityRepository;
//    }

    public Appointment createAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

//    @Transactional
//    public Appointment bookAppointment(Appointment appointment){
//
//        //Fetch availability for the doctor
//        List<Availability> availabilities = availabilityRepository.findByDoctorId(appointment.getDoctorId());
//
//        boolean isAvailable = false;
//
//        if (availabilities.isEmpty()) {
//            throw new AppointmentException("Doctor " +appointment.getDoctorId()+ " has no available time slots.");
//        }
//
//        //Check if the requested appointment time is within any available time slot
//        for (Availability availability : availabilities){
//            if (appointment.getAppointmentTime().isAfter(availability.getStartTime()) &&
//            appointment.getAppointmentTime().isBefore(availability.getEndTime())){
//                isAvailable = true;
//                break;
//            }
//        }
//        if(!isAvailable){
//            throw new AppointmentException("No available slots for the selected time.");
//        }
//
//        //check for overlapping appointments
//        List<Appointment> existingAppointments = appointmentRepository.findByDoctorId(appointment.getDoctorId());
//        for (Appointment existing : existingAppointments){
//            if (appointment.getAppointmentTime().equals(existing.getAppointmentTime())){
//                throw new AppointmentException("An appointment already exists at the selected time.");
//            }
//        }
//        return appointmentRepository.save(appointment);
//    }

    public List<Appointment> getAppointmentsByDoctorId(String doctorId){
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public Optional<Appointment> getAppointmentById(String id){
        return appointmentRepository.findById(id);
    }

    public void deleteAppointment(String id){
        appointmentRepository.deleteById(id);
    }

}
