package com.healthsync.appointment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "appointment")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Appointment
{
    @Id
    private String appointmentId;
    private String doctorId;
    private String patientId;
    private LocalDateTime appointmentTime;
    private String symptomsConditions;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientName(String patientName) {
        this.patientId = patientId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getSymptomsConditions() {
        return symptomsConditions;
    }

    public void setSymptomsConditions(String symptomsConditions) {
        this.symptomsConditions = symptomsConditions;
    }
}
