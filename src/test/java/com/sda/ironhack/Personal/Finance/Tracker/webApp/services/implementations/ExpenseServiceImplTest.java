package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ExpenseServiceImplTest {
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // Add any setup code here if needed
    }

    @AfterEach
    void tearDown() {
        // Add any teardown code here if needed
    }

    @Test
    void testGetExpenseById() throws Exception {
        // Perform a GET request to the /expenses/{expenseId} endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/expenses/{expenseId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // You can add assertions based on the expected behavior
    }

    @Test
    void testDeleteExpense() throws Exception {
        // Perform a DELETE request to the /expenses/{expenseId} endpoint
        mockMvc.perform(delete("/expenses/{expenseId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // You can add assertions based on the expected behavior
    }
}