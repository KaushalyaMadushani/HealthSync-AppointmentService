package com.healthsync.appointment;

import com.healthsync.appointment.model.Availability;
import com.healthsync.appointment.repository.AvailabilityRepository;
import com.healthsync.appointment.service.AvailabilityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AvailabilityServiceTest {
    private final AvailabilityRepository availabilityRepository = mock(AvailabilityRepository.class);
    private final AvailabilityService availabilityService = new AvailabilityService(availabilityRepository);

    @Test
    void testCreateAvailability() {
        Availability availability = new Availability("1", "doctor1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        when(availabilityRepository.save(Mockito.any())).thenReturn(availability);

        Availability savedAvailability = availabilityService.createAvailability(availability);
        assertNotNull(savedAvailability);
        assertEquals("doctor1", savedAvailability.getDoctorId());
        verify(availabilityRepository, times(1)).save(availability);
    }

    @Test
    void testGetAvailabilitiesByDoctorId() {
        Availability availability = new Availability("1", "doctor1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        when(availabilityRepository.findByDoctorId("doctor1")).thenReturn(List.of(availability));

        List<Availability> availabilities = availabilityService.getAvailabilitiesByDoctorId("doctor1");
        assertEquals(1, availabilities.size());
        assertEquals("doctor1", availabilities.get(0).getDoctorId());
        verify(availabilityRepository, times(1)).findByDoctorId("doctor1");
    }
}
