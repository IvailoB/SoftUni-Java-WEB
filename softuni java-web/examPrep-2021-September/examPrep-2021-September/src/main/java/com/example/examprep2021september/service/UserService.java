package com.example.examprep2021september.service;

import com.example.examprep2021september.model.entity.User;
import com.example.examprep2021september.model.service.UserServiceModel;
import com.example.examprep2021september.model.view.UserViewModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    UserServiceModel registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUserNameAndPassword(String userName, String password);

    void loginUser(Long id, String userName);

    User findById(Long id);

    List<UserViewModel> findAllUserAndCountOfOrdersOrderByCountDesc();
}
