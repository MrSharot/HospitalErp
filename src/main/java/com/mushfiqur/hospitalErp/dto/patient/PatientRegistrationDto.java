package com.mushfiqur.hospitalErp.dto.patient;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PatientRegistrationDto {

    @NotEmpty(message = "Name must not be empty")
    @Size(max = 50, message = "Name mustn't exceed 50 chars")
    private String name;

    @NotEmpty(message = "Phone must not be empty")
    @Size(max = 15, message = "Phone mustn't exceed 15 chars")
    private String phone;

    @NotEmpty(message = "Username must not be empty")
    @Size(max = 20, message = "Username mustn't exceed 20 chars")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(max = 255, message = "Password mustn't exceed 255 chars")
    private String password;

    public PatientRegistrationDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
