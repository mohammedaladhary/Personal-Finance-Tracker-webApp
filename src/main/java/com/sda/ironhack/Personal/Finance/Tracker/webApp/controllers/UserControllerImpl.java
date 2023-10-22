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

    @GetMapping("/welcome")
    public String greetingUser(){
        return "Welcome my friend...";
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping("/users/add")
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
    }
}
