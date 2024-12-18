//package com.healthsync.appointment.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Document(value = "availability")
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Data
//public class Availability {
//
//    @Id
//    private String availabilityId;
//    private String doctorId;
//    private LocalDateTime startTime;
//    private LocalDateTime endTime;
//
//    public String getAvailabilityId() {
//        return availabilityId;
//    }
//
//    public void setAvailabilityId(String availabilityId) {
//        this.availabilityId = availabilityId;
//    }
//
//    public String getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(String doctorId) {
//        this.doctorId = doctorId;
//    }
//
//    public LocalDateTime getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(LocalDateTime startTime) {
//        this.startTime = startTime;
//    }
//
//    public LocalDateTime getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(LocalDateTime endTime) {
//        this.endTime = endTime;
//    }
//}
