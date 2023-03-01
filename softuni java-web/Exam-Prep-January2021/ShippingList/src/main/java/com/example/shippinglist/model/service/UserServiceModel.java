package com.example.shippinglist.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private String email;
}
