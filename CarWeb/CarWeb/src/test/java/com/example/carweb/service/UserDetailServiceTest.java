package com.example.carweb.service;

import com.example.carweb.model.entity.User;
import com.example.carweb.repo.RoleRepository;
import com.example.carweb.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class UserDetailServiceTest {

    private UserDetailService toTest;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userService = new UserService(
                userRepository,
                roleRepository,
                passwordEncoder
        );
        toTest = new UserDetailService(
                userService
        );
    }

    @Test
    void testUserFound() {
        User testUser = new User();
        testUser.setUsername("admin");
        testUser.setPassword("123");
        testUser.setRoles(List.of());

      when(userRepository.findByUsername("admin"))
                .thenReturn(Optional.of(testUser));

        when(userService.getUserByUsername("admin"))
                .thenReturn(testUser);

        UserDetails adminUser = toTest.loadUserByUsername("admin");

        Assertions.assertNotNull(adminUser);

    }

    @Test
    void testUserNotFound() {
        assertThrows(NullPointerException.class,
                () ->
                        toTest.loadUserByUsername("non-existent-username")
        );
    }
}
