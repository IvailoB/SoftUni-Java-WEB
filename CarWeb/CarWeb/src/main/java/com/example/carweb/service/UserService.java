package com.example.carweb.service;

import com.example.carweb.model.entity.User;
import com.example.carweb.repo.RoleRepository;
import com.example.carweb.repo.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void init() {
        if (userRepository.count() != 0) {
            return;
        }

        User admin = new User();
        admin.setFirstName("Admin");
        admin.setLastName("Admin");
        admin.setEmail("admin@admin.com");
        admin.setRoles(roleRepository.findAll());

        userRepository.save(admin);
    }
}
