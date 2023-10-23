//package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;
//
//import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
//import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.IncomeRepository;
//import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.IncomeServiceImpl;
//import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class IncomeControllerImpl {
//
//    @Autowired
//    private IncomeServiceImpl incomeServiceImpl;
//
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//    @Autowired
//    private IncomeRepository incomeRepository; // You need to inject IncomeRepository
//
//    @PutMapping("/users/updateBalance/{userId}")
//    public ResponseEntity<String> updateBalanceForUser(@PathVariable int userId, @RequestParam int newBalance) {
//        try {
//            // Find the user by userId
//            User user = userServiceImpl.getUserById(userId);
//
//            if (user == null) {
//                String errorMessage = "User not found with userId: " + userId;
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
//            }
//
//            // Set the user's balance to the newBalance value
//            user.setBalance(newBalance);
//
//            // Update the user's balance using the service method
//            userServiceImpl.updateUserBalance(user, newBalance);
//
//            String message = "Balance updated successfully for User with userId: " + userId;
//            return ResponseEntity.status(HttpStatus.OK).body(message);
//        } catch (Exception e) {
//            String errorMessage = "Balance not updated for User with userId: " + userId + " - " + e.getMessage();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//        }
//    }
//}