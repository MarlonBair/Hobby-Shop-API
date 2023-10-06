package com.hobbyshop.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbyshop.api.controller.UserController;
import com.hobbyshop.api.model.User;
import com.hobbyshop.api.service.UserService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for UserController.
 */

@WebMvcTest(UserController.class)
public class UserControllerTest {
    
    /**
     * MockMvc needed to simulate HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock UserService for testing.
     */
    @MockBean
    private UserService userService;

    /**
     * User instance for testing.
     */
    private User user;

    /**
     * Test initialization.
     */
    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@email.com");
    }


    /**
     * Tests saveUser with a POST request.
     * 
     * @throws Exception if any exception occurs.
     */
    @Test
    void saveUser() throws Exception {

        given(userService.saveUser(user)).willReturn(user);

        mockMvc.perform(post("/api/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(user)))
            .andExpect(status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(user.getUserId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));

    }

    /**
     * Tests getUserByUserId with a GET request.
     * 
     * @throws Exception if any exception occurs.
     */
    @Test
    void getUserByUserID() throws Exception {
        long userId = 1L;
        given(userService.getUserByUserId(userId)).willReturn(user);

        mockMvc.perform(get("/api/users/" + userId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(user.getUserId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));
    }

    /**
     * Tests updateUser with a PUT request.
     * 
     * @throws Exception if any exception occurs.
     */
    @Test
    void updateUser() throws Exception {
        long userId = 1L;
        user.setName("Jane Doe");
        user.setEmail("janedoe@email.com");
        given(userService.updateUser(user, userId)).willReturn(user);

        mockMvc.perform(put("/api/users/" + userId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(user)))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(user.getUserId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));;
    }

    /**
     * Tests deleteUser with a DELETE request.
     * 
     * @throws Exception if any exception occurs.
     */
    @Test
    void deleteUser() throws Exception {
        long userId = 1L;
        mockMvc.perform(delete("/api/users/" + userId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    // TODO
    // TODO



}