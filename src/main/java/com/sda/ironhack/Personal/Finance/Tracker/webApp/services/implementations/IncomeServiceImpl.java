//package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;
//
//import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
//import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.IncomeRepository;
//import jakarta.validation.Valid;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class IncomeServiceImpl {
//    @Autowired
//    private IncomeRepository incomeRepository;
//
//    public void addIncome(@Valid Income income) {
//        // You can add any additional validation or business logic here before saving the income
//        // For example, check if the income amount is valid, set the user, etc.
//
//        // Check if the income's amount is greater than 0
//        if (income.getAmount() <= 0) {
//            // Handle the case where the income amount is not valid.
//            // You can throw an exception, return an error message, or take other actions.
//            throw new IllegalArgumentException("Income amount must be greater than 0");
//        }
//
//        // Save the income record to the database
//        incomeRepository.save(income);
//    }
//}
