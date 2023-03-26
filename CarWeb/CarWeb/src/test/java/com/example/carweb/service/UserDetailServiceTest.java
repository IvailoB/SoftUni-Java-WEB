package com.example.carweb.service;

import com.example.carweb.model.entity.User;
import com.example.carweb.repo.RoleRepository;
import com.example.carweb.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserDetailServiceTest {

    private UserDetailService toTest;

    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        toTest = new UserDetailService(
                mockUserService
        );
    }

    @Test
    void testUserFound() {
        User testUser = new User();
        testUser.setUsername("admin");
        testUser.setPassword("123");
        testUser.setRoles(List.of());

      when(mockUserService.getUserByUsername("admin"))
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
