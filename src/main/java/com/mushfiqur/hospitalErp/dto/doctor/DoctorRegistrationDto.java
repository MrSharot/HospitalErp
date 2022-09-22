package com.mushfiqur.hospitalErp.dto.doctor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DoctorRegistrationDto {

    @NotEmpty(message = "Name must not be empty")
    @Size(max = 50, message = "Max name size is 50")
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

    @NotEmpty(message = "Speciality must not be empty")
    @Size(max = 30, message = "Speciality mustn't exceed 30 chars")
    private String speciality;

    @NotEmpty(message = "Degree must not be empty")
    @Size(max = 50, message = "Degree mustn't exceed 50 chars")
    private String degree;

    public DoctorRegistrationDto() {
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
