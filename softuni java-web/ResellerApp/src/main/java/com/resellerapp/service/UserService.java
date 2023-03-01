package com.resellerapp.service;

import com.resellerapp.model.dto.RegisterDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    public void register(RegisterDTO registerDTO) {
        this.userRepository.save(modelMapper.map(registerDTO, User.class));
        this.login(registerDTO.getUsername());
    }

    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getUsername());
    }

    private User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public void logout() {
        this.httpSession.invalidate();
        this.loggedUser.setId(null);
        this.loggedUser.setUsername(null);
    }

    public User findByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }

    public User findUserByID(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

}
