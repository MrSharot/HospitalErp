package com.mushfiqur.hospitalErp.dto.appt;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mushfiqur.hospitalErp.dto.LoginDto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class ApptInfoDto {

    @Valid
    private LoginDto login;

    @NotEmpty(message = "Doctor's username must not be empty")
    @Size(max = 20, message = "Doctor's username mustn't exceed 20 chars")
    private String doctorUsername;

    @NotEmpty(message = "Slot cannot be empty")
    @Size(max = 20, message = "Slot mustn't exceed 20 chars")
    @Pattern(
            regexp = "[0-9]{1,2} [AP]M - [0-9]{1,2} [AP]M",
            message = "Invalid Slot Pattern. Slot must be in this format `1 AM - 2 AM`. Slot can only be 1 hour."
    )
    private String slot;

    @NotNull(message = "Date cannot be empty")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    public ApptInfoDto() {
    }

    public LoginDto getLogin() {
        return login;
    }

    public void setLogin(LoginDto login) {
        this.login = login;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
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
}
