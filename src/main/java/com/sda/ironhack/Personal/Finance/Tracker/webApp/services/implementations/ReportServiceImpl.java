package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Report;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.ExpenseRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.IncomeRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.ReportRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.UserRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public Report generateReport(User user, LocalDate startDate, LocalDate endDate) {
        // Implement your logic to calculate the total income and expense for this user within the specified date range
        double totalIncome = calculateTotalIncome(user, startDate, endDate);
        double totalExpense = calculateTotalExpense(user, startDate, endDate);

        // Calculate the updated balance
        double updatedBalance = user.getBalance()+totalIncome - totalExpense;

        // Create a report for this user
        Report report = new Report(startDate, endDate, totalIncome, totalExpense, updatedBalance, user);

        // Save the generated report to the database (if needed)

        return report;
    }

    public double calculateTotalIncome(User user, LocalDate startDate, LocalDate endDate) {
        List<Income> userIncomes = incomeRepository.findAllByUserAndDateBetween(user, startDate, endDate);
        double totalIncome = 0.0;

        for (Income income : userIncomes) {
            totalIncome += income.getAmount();
        }

        return totalIncome;
    }

    public double calculateTotalExpense(User user, LocalDate startDate, LocalDate endDate) {
        List<Expense> userExpenses = expenseRepository.findAllByUserAndDateBetween(user, startDate, endDate);
        double totalExpense = 0.0;

        for (Expense expense : userExpenses) {
            totalExpense += expense.getAmount();
        }

        return totalExpense;
    }
}