package com.healthsync.appointment.controller;

import com.healthsync.appointment.model.Appointment;
import com.healthsync.appointment.model.Availability;
import com.healthsync.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment){
        return ResponseEntity.ok(appointmentService.bookAppointment(appointment));
    }

    @GetMapping("/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable String doctorId){
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }


}
