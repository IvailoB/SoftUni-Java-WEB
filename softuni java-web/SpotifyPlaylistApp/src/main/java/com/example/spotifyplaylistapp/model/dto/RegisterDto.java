package com.example.spotifyplaylistapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterDto {
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @NotBlank
    private String username;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 chars")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 chars")
    @NotBlank(message = "Password cannot be empty")
    private String confirmPassword;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    public RegisterDto() {
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
