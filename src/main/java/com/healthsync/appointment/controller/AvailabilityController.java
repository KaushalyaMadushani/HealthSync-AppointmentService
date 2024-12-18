package com.healthsync.appointment.controller;

import com.healthsync.appointment.model.Availability;
import com.healthsync.appointment.service.AvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping("/availability")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Availability> createAvailability(@RequestBody Availability availability){
        return ResponseEntity.ok(availabilityService.createAvailability(availability));
    }

    @GetMapping("/availability/{doctorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Availability>> getAvailabilities(@PathVariable String doctorId){
        return ResponseEntity.ok(availabilityService.getAvailabilitiesByDoctorId(doctorId));
    }
}
