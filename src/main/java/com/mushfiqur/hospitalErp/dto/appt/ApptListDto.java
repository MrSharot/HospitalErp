package com.mushfiqur.hospitalErp.dto.appt;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ApptListDto {

    private static class ApptPatientDto {

        private String name;

        private String phone;

        private String username;

        public ApptPatientDto(String name, String phone, String username) {
            this.name = name;
            this.phone = phone;
            this.username = username;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    private String slot;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    private ApptPatientDto patient;

    public ApptListDto(String slot, LocalDate date, String patientName, String patientPhone, String patientUsername) {
        this.slot = slot;
        this.date = date;

        this.patient = new ApptPatientDto(patientName, patientPhone, patientUsername);
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ApptPatientDto getPatient() {
        return patient;
    }

    public void setPatient(ApptPatientDto patient) {
        this.patient = patient;
    }
}
