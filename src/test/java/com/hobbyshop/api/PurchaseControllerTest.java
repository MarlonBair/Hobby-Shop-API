package com.hobbyshop.api;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbyshop.api.controller.PurchaseController;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.service.PurchaseService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for PurchaseController.
 */
@WebMvcTest(PurchaseController.class)
public class PurchaseControllerTest {

    /**
     * MockMvc needed to simulate HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock PurchaseService for testing.
     */
    @MockBean
    private PurchaseService purchaseService;

     /**
     * Purchase instance for testing.
     */
    private Purchase purchase;

    @BeforeEach
    void setUp() {
        purchase = new Purchase();
        purchase.setPurchaseId(1L);
    }

    @Test
    void getPurchaseByPurchaseId() throws Exception {
        Long purchaseId = 1L;
        given(purchaseService.getPurchaseByPurchaseId(purchaseId)).willReturn(purchase);

        mockMvc.perform(get("/api/purchases/" + purchaseId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").value(purchase.getPurchaseId()));
    }

    @Test
    void listAllPurchases() throws Exception {
        List<Purchase> purchases = Arrays.asList(purchase);
        given(purchaseService.listAllPurchases()).willReturn(purchases);

        mockMvc.perform(get("/api/purchases"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].purchaseId").value(purchase.getPurchaseId()));
    }

    @Test
    void updatePurchaseItemByItemId() throws Exception {
        Long purchaseId = 1L;
        Long itemId = 2L;
        given(purchaseService.updatePurchaseItemByItemId(purchaseId, itemId)).willReturn(purchase);

        mockMvc.perform(put("/api/purchases/" + purchaseId + "/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(itemId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").value(purchase.getPurchaseId()));
    }

    @Test
    void updatePurchaseUserByUserId() throws Exception {
        Long purchaseId = 1L;
        Long userId = 3L;
        given(purchaseService.updatePurchaseUserByUserId(purchaseId, userId)).willReturn(purchase);

        mockMvc.perform(put("/api/purchases/" + purchaseId + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userId)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseId").value(purchase.getPurchaseId()));
    }

    @Test
    void deletePurchase() throws Exception {
        Long purchaseId = 1L;

        mockMvc.perform(delete("/api/purchases/" + purchaseId))
                .andExpect(status().isOk());
    }
}