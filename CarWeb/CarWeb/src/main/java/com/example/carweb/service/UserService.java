package com.example.carweb.service;

import com.example.carweb.model.dtos.RegisterDTO;
import com.example.carweb.model.entity.User;
import com.example.carweb.model.enums.UserRoleEnum;
import com.example.carweb.repo.RoleRepository;
import com.example.carweb.repo.UserRepository;
import com.example.carweb.util.LoggedUser;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, LoggedUser loggedUser, ModelMapper modelMapper, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
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
        admin.setPassword("admin-pass");

        userRepository.save(admin);
    }

    public void register(RegisterDTO registerDTO) {
        User user = modelMapper.map(registerDTO, User.class);
        user.setRoles(roleRepository.findByRole(UserRoleEnum.USER));
        this.userRepository.save(user);
        this.login(registerDTO.getUsername());
    }

    public void login(String username) {
        User user = this.getUserByUsername(username);
        this.loggedUser.setId(user.getId());
        this.loggedUser.setUsername(user.getEmail());
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
