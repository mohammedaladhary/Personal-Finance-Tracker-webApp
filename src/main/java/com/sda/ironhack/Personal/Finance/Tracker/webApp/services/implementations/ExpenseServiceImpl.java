package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.ExpenseRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.ExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> getAllExpensesById(User userId) {
        return expenseRepository.findAllByUser(userId);
    }

    @Override
    public Expense addExpenseByUserId(int userId, Expense expense) {
        try {
            // Retrieve the user by their userId
            User user = userServiceImpl.getUserById(userId);

            if (user == null) {
                // Handle the case where the user is not found
                // You can return null or throw an exception
                return null;
            }

            // Associate the user with the expense
            expense.setUser(user);

            // Save the expense with the updated association
            return expenseRepository.save(expense);
        } catch (Exception e) {
            // Handle the exception
            return null;
        }
    }

    @Override
    public String deleteExpense(int userId) {
        if (expenseRepository.existsById(userId)) {
            expenseRepository.deleteById(userId);
            return "Expense deleted successfully for this user.";
        }
        else {
            return "Expense not found for this user.";
        }
    }
}
