package com.hobbyshop.api;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobbyshop.api.controller.ItemController;
import com.hobbyshop.api.model.Item;
import com.hobbyshop.api.model.Purchase;
import com.hobbyshop.api.service.ItemService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for ItemController.
 */
@WebMvcTest(ItemController.class)
public class ItemControllerTest {

    /**
     * MockMvc needed to simulate HTTP requests.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock ItemService for testing.
     */
    @MockBean
    private ItemService itemService;

    /**
     * Item instance for testing.
     */
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setName("Cool Item");
        item.setDescription("A Description");
    }

    @Test
    void saveItem() throws Exception {
        given(itemService.saveItem(item)).willReturn(item);

        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemId").value(item.getItemId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(item.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(item.getDescription()));
    }

    @Test
    void getItemByItemId() throws Exception {
        Long itemId = 1L;
        given(itemService.getItemByItemId(itemId)).willReturn(item);

        mockMvc.perform(get("/api/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemId").value(item.getItemId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(item.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(item.getDescription()));
    }

    @Test
    void getPurchasesByItemId() throws Exception {
        Long itemId = 1L;
        List<Purchase> purchases = Arrays.asList(new Purchase());
        given(itemService.getAllPurchasesByItemId(itemId)).willReturn(purchases);

        mockMvc.perform(get("/api/items/" + itemId + "/purchases")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void listAllItems() throws Exception {
        List<Item> items = Arrays.asList(item);
        given(itemService.listAllItems()).willReturn(items);

        mockMvc.perform(get("/api/items")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].itemId").value(item.getItemId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(item.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value(item.getDescription()));
    }

    @Test
    void updateItem() throws Exception {
        Long itemId = 1L;
        given(itemService.updateItem(item, itemId)).willReturn(item);

        mockMvc.perform(put("/api/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemId").value(item.getItemId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(item.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(item.getDescription()));
    }

    @Test
    void updateItemQuantity() throws Exception {
        Long itemId = 1L;
        int quantityChange = 5;
        given(itemService.updateItemQuantity(itemId, quantityChange)).willReturn(item);

        mockMvc.perform(patch("/api/items/" + itemId + "/quantity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(quantityChange)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.itemId").value(item.getItemId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(item.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(item.getDescription()));
    }

    @Test
    void deleteItem() throws Exception {
        Long itemId = 1L;
        mockMvc.perform(delete("/api/items/" + itemId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
