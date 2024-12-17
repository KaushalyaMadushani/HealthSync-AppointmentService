package com.healthsync.appointment;

import com.healthsync.appointment.exception.AppointmentException;
import com.healthsync.appointment.model.Appointment;
import com.healthsync.appointment.model.Availability;
import com.healthsync.appointment.repository.AppointmentRepository;
import com.healthsync.appointment.repository.AvailabilityRepository;
import com.healthsync.appointment.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {
    private AppointmentRepository appointmentRepository;
    private AvailabilityRepository availabilityRepository;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentRepository = mock(AppointmentRepository.class);
        availabilityRepository = mock(AvailabilityRepository.class);
        appointmentService = new AppointmentService(appointmentRepository, availabilityRepository);
    }

    @Test
    void testBookAppointment() {
        // Arrange
        String doctorId = "doctor1";
        LocalDateTime startTime = LocalDateTime.of(2024, 12, 15, 10, 0);
        LocalDateTime endTime = startTime.plusHours(2);

        // Mock availability
        Availability availability = new Availability("availability1", doctorId, startTime, endTime);
        when(availabilityRepository.findByDoctorId(doctorId)).thenReturn(List.of(availability));

        // Mock appointment within availability
        LocalDateTime appointmentTime = startTime.plusMinutes(30); // Valid time slot
        Appointment appointment = new Appointment("appointment1", doctorId, "patient1", appointmentTime);
        when(appointmentRepository.save(Mockito.any())).thenReturn(appointment);

        // Act
        Appointment savedAppointment = appointmentService.bookAppointment(appointment);

        // Assert
        assertNotNull(savedAppointment);
        assertEquals(doctorId, savedAppointment.getDoctorId());
        assertEquals("patient1", savedAppointment.getPatientName());
        assertEquals(appointmentTime, savedAppointment.getAppointmentTime());

        // Verify repository interactions
        verify(availabilityRepository, times(1)).findByDoctorId(doctorId);
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void testBookAppointmentNoAvailableSlots() {
        // Arrange
        String doctorId = "doctor1";
        LocalDateTime appointmentTime = LocalDateTime.of(2024, 12, 15, 12, 0);

        // Mock no matching availability
        when(availabilityRepository.findByDoctorId(doctorId)).thenReturn(List.of());

        Appointment appointment = new Appointment("appointment1", doctorId, "patient1", appointmentTime);

        // Act & Assert
        AppointmentException exception = assertThrows(AppointmentException.class, () ->
                appointmentService.bookAppointment(appointment)
        );

        assertEquals("Doctor doctor1 has no available time slots.", exception.getMessage());
        verify(availabilityRepository, times(1)).findByDoctorId(doctorId);
        verify(appointmentRepository, never()).save(any());
    }
}
