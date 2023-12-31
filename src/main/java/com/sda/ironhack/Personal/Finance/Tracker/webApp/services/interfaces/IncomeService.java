package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;

import java.util.List;
import java.util.Map;

public interface IncomeService {
    List<Income> getAllIncomes();

    List<Income> getAllIncomesById(User userId);

    String addIncomeByUserId(int userId, Income amount);

    String updateUserIncomeInfo(int userId, Map<String, Object> income);

    Income getUserById(int userId);

    void updateUserIncome(int userId, double newIncome);

    String deleteIncome(int userId);
}
