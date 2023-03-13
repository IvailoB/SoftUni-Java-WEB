package com.example.carweb.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDTO {

    private Long id;


    @Size(min = 5, max = 20)
    @NotNull
    private String username;


    private String firstName;

    @Size(min = 5, max = 20)
    @NotBlank(message = "cannot be empty")
    private String lastName;


    @Email
    @NotBlank(message = "cannot be empty")
    private String email;

    @Size(message = "size must be minimum 3",min = 3)
    @NotNull
    private String password;

    @Size(message = "size must be minimum 3",min = 3)
    @NotNull
    private String confirmPassword;
}
