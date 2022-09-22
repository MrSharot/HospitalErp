package com.mushfiqur.hospitalErp.dto.patient;

import java.util.HashMap;
import java.util.Map;

public class PatientHomeDto {

    private String name;

    private String phone;

    private Map<String, String> actions;

    public PatientHomeDto() {
        actions = new HashMap<>();
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

    public Map<String, String> getActions() {
        return actions;
    }

    public void setActions(Map<String, String> actions) {
        this.actions = actions;
    }
}
