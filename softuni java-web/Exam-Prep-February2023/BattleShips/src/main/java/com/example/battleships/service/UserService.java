package com.example.battleships.service;

import com.example.battleships.domain.entity.User;
import com.example.battleships.domain.helpers.LoggedUser;
import com.example.battleships.domain.model.UserModel;
import com.example.battleships.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;



    public UserService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }

    public UserModel findByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username).orElse(new User()), UserModel.class);
    }

    public UserModel findById(Long id) {
        return this.modelMapper.map(this.userRepository.findById(id).orElse(new User()), UserModel.class);
    }

    public UserModel findByIdNot(Long id) {
        return this.modelMapper.map(this.userRepository.findByIdNot(id).orElse(new User()), UserModel.class);
    }

}
