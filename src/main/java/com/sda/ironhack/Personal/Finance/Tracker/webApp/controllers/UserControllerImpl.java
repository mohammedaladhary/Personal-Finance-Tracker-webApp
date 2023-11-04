package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllerImpl {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/financeTracker/auth/welcome")
    public String greetingUser(){
        return "Welcome to my Finance Tracker Management Page...";
    }

    @GetMapping("/financeTracker/auth/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping("/financeTracker/auth/users/add") // ignore the below method for creating new user.
    public ResponseEntity<String> addUser(@RequestBody @Valid User user) {
        try {
            userServiceImpl.addUser(user);
            String message = "User added successfully";
            //return the response status
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            // Saving the error message in a variable and calling getMessage Method
            // to get a detailed error message from exception class
            String errorMessage = "User not added successfully" + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    } // ignore the above method for creating new user.

    @DeleteMapping("/financeTracker/auth/users/delete/{userId}")
    public String deleteUser(@PathVariable int userId){
        return userServiceImpl.deleteUser(userId);
    }

    @PutMapping("/financeTracker/auth/users/update/{userId}")
    public String updateUser(@PathVariable int userId, @RequestBody User user){
        return userServiceImpl.updateUser(userId, user);
    }

    @PutMapping("/financeTracker/dashBoard/users/updateBalance/{userId}")
    public ResponseEntity<String> updateBalanceForUser(@PathVariable int userId, @RequestBody User user) {
        try {
            // Extract the new balance from the User object provided in the request body
            double newBalance = user.getBalance();

            // Find the user by userId
            user = userServiceImpl.getUserById(userId);

            if (user == null) {
                String errorMessage = "User not found with userId: " + userId;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }

            // Update the user's balance
            user.setBalance(newBalance);

            // Update the user's balance using the service method
            userServiceImpl.updateUserBalance(userId, newBalance);

            String message = "Balance updated successfully for User with userId: " + userId;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            String errorMessage = "Balance not updated for User with userId: " + userId + " - " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
}
