package com.example.carweb.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileView {
    private String username;

    private String email;

    private String firstName;

    private String lastName;
}
