package com.example.spotifyplaylistapp.service.imp;

import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.repository.UserRepo;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final LoggedUser loggedUser;

    private final HttpSession httpSession;

    public UserServiceImpl(UserRepo userRepo, LoggedUser loggedUser, HttpSession httpSession) {
        this.userRepo = userRepo;
        this.loggedUser = loggedUser;
        this.httpSession = httpSession;
    }

    @Override
    public void registerUser(User user) {
        userRepo.save(user);
    }

    @Override
    public User findUser(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password).orElse(null);
    }

    @Override
    public void login(User user) {
        loggedUser.setId(user.getId());
        loggedUser.setUsername(user.getUsername());
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }



    @Override
    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }
}
