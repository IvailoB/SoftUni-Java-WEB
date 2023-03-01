package com.example.shippinglist.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @NotBlank
    private String username;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 chars")
    @NotBlank(message = "Password cannot be empty str")
    private String password;

    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 chars")
    @NotBlank(message = "Password cannot be empty str")
    private String confirmPassword;

    @Email(message = "Email must be valid")
    private String email;
}
