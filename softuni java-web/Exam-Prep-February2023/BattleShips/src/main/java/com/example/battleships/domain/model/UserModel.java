package com.example.battleships.domain.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserModel {

    private Long id;
    private String username;
    private String fullName;
    private String password;
    private String email;
}
