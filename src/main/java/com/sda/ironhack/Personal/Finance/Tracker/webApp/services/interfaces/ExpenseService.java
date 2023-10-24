package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpenses();

    List<Expense> getAllExpensesById(User userId);

    Expense addExpenseByUserId(int userId, Expense expense);
}
