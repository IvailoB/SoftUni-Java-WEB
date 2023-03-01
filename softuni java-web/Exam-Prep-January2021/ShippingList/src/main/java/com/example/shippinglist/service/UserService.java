package com.example.shippinglist.service;

import com.example.shippinglist.model.entity.User;
import com.example.shippinglist.model.service.UserServiceModel;

public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    User findById(String id);
}
