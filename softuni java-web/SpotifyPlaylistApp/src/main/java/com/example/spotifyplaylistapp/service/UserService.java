package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.User;

public interface UserService {
    void registerUser(User user);

    User findUser(String username, String password);

    void login(User user);

    User findUserById(Long id);

    void logout();

}
