package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.IncomeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
}