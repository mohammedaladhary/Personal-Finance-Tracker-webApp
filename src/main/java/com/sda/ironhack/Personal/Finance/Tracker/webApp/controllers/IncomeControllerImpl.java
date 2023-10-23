package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.IncomeServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncomeControllerImpl {

    @Autowired
    private IncomeServiceImpl incomeServiceImpl;

    @GetMapping("/users/incomes")
    @ResponseStatus(HttpStatus.OK)
    public List<Income> getAllIncomes(){
        return incomeServiceImpl.getAllIncomes();
    }

    @GetMapping("/users/incomes/findById/{userId}")
    public List<Income> getAllIncomesById(@PathVariable User userId){
        return incomeServiceImpl.getAllIncomesById(userId);
    }
    @PostMapping("/users/incomes/add/{userId}")
    public String addIncomeByUserId(@PathVariable int userId, @RequestBody @Valid Income income) {
        return incomeServiceImpl.addIncomeByUserId(userId,income);
    }
}