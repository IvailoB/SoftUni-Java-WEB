package com.example.examprep2021september.model.binding;

import jakarta.validation.constraints.Size;

public class UserLoginBindingModel {
    @Size(min = 5, max = 20)
    private String userName;
    @Size(min = 3)
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
