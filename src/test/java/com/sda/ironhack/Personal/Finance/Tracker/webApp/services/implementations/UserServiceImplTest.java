package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Optional;

//import static jdk.internal.org.jline.utils.InfoCmp.Capability.user1;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserServiceImplTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        User user1 = new User("Khalid", "khalid1122", "khalid@gmail.com",221);
        User user2 = new User("Ahmed", "ahmed1122", "ahmed@gmail.com",433);
        userRepository.saveAll(List.of(user1, user2));
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getAllUsersTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Khalid"));
        assertTrue(result.getResponse().getContentAsString().contains("Ahmed"));

    }

    @Test
    void PostCourseTest() throws Exception{
        //create a new object to odd
        User user = new User("Said","said1122","said@gmail.com",432);
        //Convert the object to JSON
        String requestBody = mapper.writeValueAsString(user);

        //MockMvc post request
        MvcResult result =
                mockMvc.perform(post("/users/add")
                                .content(requestBody)
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
                        .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("User added successfully"));
    }

//    @Test
//    void updateUserTest() throws Exception {
//        User userToUpdate = new User("Khalid", "updatedPassword", "updatedEmail@gmail.com", 500);
//
//        // Convert the updated user to JSON
//        String requestBody = mapper.writeValueAsString(userToUpdate);
//
//        // MockMvc PUT request to update an existing user (user1, for example)
//        MvcResult result = mockMvc.perform(put("/users/{userId}", user1.getUserId())
//                        .content(requestBody)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertTrue(result.getResponse().getContentAsString().contains("User updated successfully"));
//
//        // Verify that the user information has been updated correctly in the database
//        Optional<User> updatedUser = userRepository.findById(user1.getUserId());
//        assertTrue(updatedUser.isPresent());
//        assertEquals("Khalid", updatedUser.get().getUsername());
//        assertEquals("updatedPassword", updatedUser.get().getPassword());
//        assertEquals("updatedEmail@gmail.com", updatedUser.get().getEmail());
//        assertEquals(500, updatedUser.get().getBalance());
//    }
//
//    @Test
//    void deleteUserTest() throws Exception {
//        // MockMvc DELETE request to delete an existing user (user1, for example)
//        MvcResult result = mockMvc.perform(delete("/users/{userId}", user1.getUserId()))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        assertTrue(result.getResponse().getContentAsString().contains("User deleted successfully"));
//
//        // Verify that the user has been deleted from the database
//        Optional<User> deletedUser = userRepository.findById(user1.getUserId());
//        assertFalse(deletedUser.isPresent());
//    }
}