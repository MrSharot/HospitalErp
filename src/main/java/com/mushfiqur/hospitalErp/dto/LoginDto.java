package com.mushfiqur.hospitalErp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class LoginDto {

    @NotEmpty(message = "Username must not be empty")
    @Size(max = 20, message = "Username mustn't exceed 20 chars")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(max = 255, message = "Password mustn't exceed 255 chars")
    private String password;

    public LoginDto() {
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
