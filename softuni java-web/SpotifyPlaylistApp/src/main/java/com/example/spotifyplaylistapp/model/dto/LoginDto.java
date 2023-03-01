package com.example.spotifyplaylistapp.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @NotBlank
    private String username;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 chars")
    @NotBlank(message = "Password cannot be empty")
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
