package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;

    @BeforeEach
    void setup() {
        user1 = new User("John","helloworld","John@gmail.com",211);
        userRepository.save(user1);
    }

    @AfterEach
    void teardown() {
        userRepository.deleteById(1);
    }

    @Test
    public void FindByUserName(){
        User userFromDb = userRepository.findByUsername("Hector");
        assertEquals("John", user1.getUsername());
    }

    @Test
    public void FindByUserId() {
        User userFromDb = userRepository.findByUserId(user1.getUserId());
        // Check if the user is found
        assertNotNull(userFromDb);
        // Compare the found user with the original user (user1)
        assertEquals(user1, userFromDb);
    }

    @Test
    public void UpdateUserBalance() {
        // Update the user's balance
        user1.setBalance(300); // New balance value

        // Save the updated User object to the database
        userRepository.save(user1); // Save the updated User object

        // Retrieve the user again to check if the balance was updated
        User updatedUser = userRepository.findByUserId(user1.getUserId());

        // Check if the balance was updated correctly
        assertNotNull(updatedUser);
        assertEquals(300, updatedUser.getBalance()); // Verify that the balance is now 311
    }
}