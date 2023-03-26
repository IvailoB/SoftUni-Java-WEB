package com.example.carweb.controller;


import com.example.carweb.model.entity.User;
import com.example.carweb.model.view.UserProfileView;
import com.example.carweb.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService mockUserService;


    @Test
    public void testRegistration() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("id", "1")
                        .param("username", "pesho")
                        .param("firstName", "Pesho")
                        .param("lastName", "Petrov")
                        .param("email", "pesho@email.com")
                        .param("password", "123")
                        .param("confirmPassword", "123")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("login"));

    }


    @Test
    public void testRegistrationFail() throws Exception {
        mockMvc.perform(post("/users/register")
                        .param("id", "1")
                        .param("username", "pesho")
                        .param("firstName", "Pesho")
                        .param("lastName", "Petrov")
                        .param("email", "pesho@email.com")
                        .param("password", "123")
                        .param("confirmPassword", "1234")
                        .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users/register"));

    }

    @Test
    public void profile_ShouldReturnUserProfileView() throws Exception {
        // Set up mock user and user profile view
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("testUser@test.com");
        user.setFirstName("Test");
        user.setLastName("User");
        UserProfileView profileView = new UserProfileView();
        profileView.setUsername(user.getUsername());
        profileView.setEmail(user.getEmail());
        profileView.setFirstName(user.getFirstName());
        profileView.setLastName(user.getLastName());

        // Mock UserService to return test user
        when(mockUserService.getUserByUsername("testUser")).thenReturn(user);

        // Perform GET request to "/profile"
        mockMvc.perform(get("/users/profile"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", Matchers.samePropertyValuesAs(profileView)))
                .andExpect(view().name("profile"));
    }

}
