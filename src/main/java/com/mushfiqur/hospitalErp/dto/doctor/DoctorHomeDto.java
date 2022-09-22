package com.mushfiqur.hospitalErp.dto.doctor;

import java.util.HashMap;
import java.util.Map;

public class DoctorHomeDto {

    private String name;

    private String speciality;

    private String degree;

    private Map<String, String> actions;

    public DoctorHomeDto() {
        actions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Map<String, String> getActions() {
        return actions;
    }

    public void setActions(Map<String, String> actions) {
        this.actions = actions;
    }
}
