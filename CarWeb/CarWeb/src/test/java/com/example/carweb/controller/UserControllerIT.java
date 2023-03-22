package com.example.carweb.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;


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
    @WithMockUser(username = "testUser", password = "password", roles = "USER")
    public void profile_ShouldReturnUserProfileView() throws Exception {
        // Perform GET request to "/profile"
        ResultActions resultActions = mockMvc.perform(get("/profile"));

        // Verify that the response status is 200 OK
        resultActions.andExpect(status().isOk());

        // Verify that the returned view name is "profile"
        resultActions.andExpect(view().name("UserProfileView"));

        // Verify that the "user" attribute has been added to the model
        resultActions.andExpect(model().attributeExists("user"));
    }

}
