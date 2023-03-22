package com.example.carweb.service;

import com.example.carweb.model.dtos.RegisterDTO;
import com.example.carweb.model.entity.User;
import com.example.carweb.repo.RoleRepository;
import com.example.carweb.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserService toTest;

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private RoleRepository mockRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @BeforeEach
    void setUp() {
        toTest = new UserService(
                mockUserRepository, mockRoleRepository, passwordEncoder
        );
    }

    @Test
    void testUserFound() {
        User testUser = new User();
        testUser.setUsername("admin");
        testUser.setPassword("123");
        testUser.setRoles(List.of());

        when(mockUserRepository.findByUsername("admin"))
                .thenReturn(Optional.of(testUser));

        User adminUser = toTest.getUserByUsername("admin");

        Assertions.assertNotNull(adminUser);

    }

    @Test
    void testUserNotFound() {
        assertThrows(NullPointerException.class,
                () ->
                        toTest.getUserByUsername("non-existent-username")
        );
    }

    @Test
    void testUserRegistration_SaveInvoked() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("user");
        registerDTO.setEmail("user@email");
        registerDTO.setPassword("123");
        registerDTO.setConfirmPassword("123");
        registerDTO.setLastName("userLastName");

        toTest.register(registerDTO);

        Mockito.verify(mockUserRepository).save(any());
    }

    @Test
    void testUserRegistration_SaveInvoked_version2() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("user");
        registerDTO.setEmail("user@email");
        registerDTO.setPassword("123");
        registerDTO.setConfirmPassword("123");
        registerDTO.setLastName("userLastName");

        when(passwordEncoder.encode(registerDTO.getPassword())).thenReturn("encode pass");
        toTest.register(registerDTO);
    }

    @Test
    void testUserRegistrationThrow() {
        RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUsername("user");
        registerDTO.setLastName("user");
        registerDTO.setEmail("invalidEmail");
        registerDTO.setPassword("123");
        registerDTO.setConfirmPassword("23");

        assertThrows(RuntimeException.class,
                () ->
                        toTest.register(registerDTO)
        );
    }


}
