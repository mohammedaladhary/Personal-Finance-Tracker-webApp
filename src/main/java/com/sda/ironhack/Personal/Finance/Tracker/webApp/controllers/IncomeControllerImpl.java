package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.IncomeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/financeTracker")
public class IncomeControllerImpl {

    @Autowired
    private IncomeServiceImpl incomeServiceImpl;


    @GetMapping("/dashBoard/users/incomes")
    @ResponseStatus(HttpStatus.OK)
    public List<Income> getAllIncomes(){
        return incomeServiceImpl.getAllIncomes();
    }

    @GetMapping("/dashBoard/users/incomes/findById/{userId}")
    public List<Income> getAllIncomesById(@PathVariable User userId){
        return incomeServiceImpl.getAllIncomesById(userId);
    }
    @PostMapping("/dashBoard/users/incomes/add/{userId}")
    public String addIncomeByUserId(@PathVariable int userId, @RequestBody @Valid Income income) {
        return incomeServiceImpl.addIncomeByUserId(userId,income);
    }

    @PutMapping("/dashBoard/users/incomes/updateIncome/{userId}")
    public ResponseEntity<String> updateUserIncomeInfo(@PathVariable int userId, @RequestBody Income income) {
        try {
            // Extract the new amount from the User object provided in the request body
            double newIncome = income.getAmount();

            // Find the user by userId
            income = incomeServiceImpl.getUserById(userId);

            if (income == null) {
                String errorMessage = "User not found with userId: " + userId;
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }

            // Update the user's balance
            income.setAmount(newIncome);

            // Update the user's balance using the service method
            incomeServiceImpl.updateUserIncome(userId, newIncome);

            String message = "Income updated successfully for User with userId: " + userId;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            String errorMessage = "Income not updated for User with userId: " + userId + " - " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @DeleteMapping("/financeTracker/dashBoard/users/incomes/delete/{userId}")
    public String deleteUserIncome(@PathVariable int userId){
        return incomeServiceImpl.deleteIncome(userId);
    }
}
