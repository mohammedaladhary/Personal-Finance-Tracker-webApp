package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user1;

    @BeforeEach
    void setup() {
        user1 = new User(112,"John","helloworld","John@gmail.com");
        userRepository.save(user1);
    }

    @AfterEach
    void teardown() {
        userRepository.deleteById(11);
    }

    @Test
    public void FindByUserName(){
        User userFromDb = userRepository.findByUsername("Hector");
        assertEquals("John", user1.getUsername());
    }

    @Test
    public void FindByUserId(){
        User userFromDb = userRepository.findByUserID(11);
        assertEquals(112, user1.getUserID());
    }

}