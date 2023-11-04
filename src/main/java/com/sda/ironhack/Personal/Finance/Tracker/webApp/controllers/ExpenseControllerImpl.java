package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.ExpenseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseControllerImpl {

    @Autowired
    private ExpenseServiceImpl expenseServiceImpl;

    @GetMapping("/financeTracker/auth/users/expenses")
    @ResponseStatus(HttpStatus.OK)
    public List<Expense> getAllExpenses() {
        return expenseServiceImpl.getAllExpenses();
    }

    @GetMapping("/financeTracker/dashBoard/users/expenses/findById/{userId}")
    public List<Expense> getAllExpensesById(@PathVariable User userId) {
        return expenseServiceImpl.getAllExpensesById(userId);
    }

    @PostMapping("/financeTracker/dashBoard/users/expenses/add/{userId}")
    public ResponseEntity<String> addExpense(@PathVariable int userId, @RequestBody @Valid Expense expense) {
        try {
            expenseServiceImpl.addExpenseByUserId(userId, expense);
            String message = "Expense added successfully";
            //return the response status
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            // Saving the error message in a variable and calling getMessage Method
            // to get a detailed error message from exception class
            String errorMessage = "Expense not added successfully" + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }
    @DeleteMapping("/financeTracker/dashBoard/users/expenses/delete/{userId}")
    public String deleteUserExpense(@PathVariable int userId){
        return expenseServiceImpl.deleteExpense(userId);
    }
}
