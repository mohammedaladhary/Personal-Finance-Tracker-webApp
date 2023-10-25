package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.IncomeRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.IncomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

    @Override
    public String updateUserIncomeInfo(int userId, Map<String, Object> incomeInfo) {
        Optional<Income> optionalIncome = incomeRepository.findById(userId);

        if (optionalIncome.isPresent()) {
            Income income = optionalIncome.get();

            if (incomeInfo != null && !incomeInfo.isEmpty()) {
                updateIncomeAttributes(income, incomeInfo);
                // Save the changes to the database
                incomeRepository.save(income);
                return "Income source updated successfully";
            } else {
                return "Income data is null or empty.";
            }
        } else {
            return "Income not found";
        }
    }

    @Override
    public Income getUserById(int userId) {
        return incomeRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserIncome(int userId, double newIncome) {
        // Find the user by userId
        Optional<Income> optionalIncome = incomeRepository.findById(userId);

        if (optionalIncome.isPresent()) {
            Income income = optionalIncome.get();

            // Update the user's balance
            income.setAmount(newIncome);

            // Save the updated User object to the database
            incomeRepository.save(income);
        } else {
            throw new IllegalArgumentException("Income not found with userId: " + userId);
        }
    }

    @Override
    public String deleteIncome(int userId) {
        if (incomeRepository.existsById(userId)) {
            incomeRepository.deleteById(userId);
            return "Income deleted successfully for this user.";
        }
        else {
            return "Income not found for this user.";
        }
    }
    private void updateIncomeAttributes(Income income, Map<String, Object> incomeInfo) {
        for (Map.Entry<String, Object> entry : incomeInfo.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (key.equals("newIncomeSource")) {
                income.setSource(value.toString());
            } else {
                throw new IllegalArgumentException("Invalid attribute: " + key);
            }
        }
    }
}