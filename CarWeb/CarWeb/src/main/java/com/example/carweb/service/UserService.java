package com.example.carweb.service;

import com.example.carweb.model.dtos.RegisterDTO;
import com.example.carweb.model.entity.User;
import com.example.carweb.repo.RoleRepository;
import com.example.carweb.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public void init() {
        if (userRepository.count() != 0) {
            return;
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@admin.com");
        admin.setRoles(roleRepository.findAll());
        admin.setPassword(passwordEncoder.encode("admin-pass"));

        userRepository.save(admin);
    }

    public void register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = this.userRepository.findByUsername(registerDTO.getUsername());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        User user = new User(
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                registerDTO.getEmail(),
                registerDTO.getFirstName(),
                registerDTO.getLastName()
        );

        this.userRepository.save(user);


//        User user = modelMapper.map(registerDTO, User.class);
//        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
//        user.setRoles(roleRepository.findByRole(UserRoleEnum.USER));
//        this.userRepository.save(user);
//        this.login(registerDTO.getUsername());
    }

//    public void login(String username) {
//        User user = this.getUserByUsername(username);
//        this.loggedUser.setId(user.getId());
//        this.loggedUser.setUsername(user.getEmail());
//    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

//    public void logout() {
//        this.httpSession.invalidate();
//        this.loggedUser.setId(null);
//        this.loggedUser.setUsername(null);
//    }

//    public User findByUserNameAndPassword(String username, String password) {
//        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
//    }
//
//    public User findUserByID(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
