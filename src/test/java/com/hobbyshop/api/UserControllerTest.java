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
import com.hobbyshop.api.exception.ResourceNotFoundException;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.model.User;
import com.hobbyshop.api.service.PurchaseService;
import com.hobbyshop.api.service.UserService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

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
     * Mock UserService and PurchaseService for testing.
     */
    @MockBean
    private UserService userService;

    @MockBean
    private PurchaseService purchaseService;

    /**
     * User instance for testing.
     */
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setName("John Doe");
        user.setEmail("johndoe@email.com");
    }

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

    @Test
    void getUserByUserId() throws Exception {
        long userId = 1L;
        given(userService.getUserByUserId(userId)).willReturn(user);

        mockMvc.perform(get("/api/users/" + userId))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value(user.getUserId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    void listAllUsers() throws Exception {
        List<User> users = Arrays.asList(user);

        given(userService.listAllUsers()).willReturn(users);

        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId").value(user.getUserId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(user.getName()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value(user.getEmail()));
    }

    @Test
    void getAllPurchasesByUserId() throws Exception {
        long userId = 1L;

        Purchase purchase = new Purchase();
        purchase.setPurchaseId(101L);
        purchase.setPurchaseDate(LocalDate.of(2023, 1, 1));

        List<Purchase> purchases = Arrays.asList(purchase);

        given(userService.getAllPurchasesByUserId(userId)).willReturn(purchases);

        mockMvc.perform(get("/api/users/" + userId + "/purchases"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].purchaseId").value(purchase.getPurchaseId()))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].purchaseDate").value(purchase.getPurchaseDate().toString()));
    }

    @Test
    void getAllPurchasesByUserIdNotFound() throws Exception {
        long userId = 1L;

        given(userService.getAllPurchasesByUserId(userId)).willThrow(new ResourceNotFoundException("User", "ID", userId));

        mockMvc.perform(get("/api/users/" + userId + "/purchases"))
            .andExpect(status().isNotFound());
    }

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
            .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    void deleteUser() throws Exception {
        long userId = 1L;
        mockMvc.perform(delete("/api/users/" + userId))
            .andExpect(status().isOk());
    }

}
