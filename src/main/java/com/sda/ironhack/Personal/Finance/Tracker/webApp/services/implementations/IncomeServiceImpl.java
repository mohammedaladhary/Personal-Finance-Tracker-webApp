package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.IncomeRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    @Override
    public List<Income> getAllIncomesById(User userId) {
        return incomeRepository.findAllByUser(userId);
    }

    @Override
    public String addIncomeByUserId(int userId, Income income) {
        try {
            // Retrieve the user by their userId
            User user = userServiceImpl.getUserById(userId);

            if (user == null) {
                return "User not found with userId: " + userId;
            }

            // Associate the income with the user
            income.setUser(user);

            // Save the income with the updated association
            incomeRepository.save(income);

            return "Income added successfully for User with userId: " + userId;
        } catch (Exception e) {
            return "Income not added successfully for User with userId: " + userId + " - " + e.getMessage();
        }
    }
}
