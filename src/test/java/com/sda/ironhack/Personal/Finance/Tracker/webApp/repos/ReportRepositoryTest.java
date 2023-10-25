package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Report;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.ReportServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReportRepositoryTest {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ReportServiceImpl reportServiceImpl;
    private User user1;
    private Report report1;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalIncome;
    private double totalExpense;
    private double updatedBalance;

    @BeforeEach
    public void setUp() {
        // Create a user
        user1 = new User("John122", "password", "john@example.com", 0.0);
        userRepository.save(user1);

        LocalDate date1 = LocalDate.of(2023, 12, 11);
        LocalDate date2 = LocalDate.of(2023, 12, 12);
        LocalDate date3 = LocalDate.of(2023, 12, 13);

        // Create three income records linked to the user
        Income income1 = new Income(user1, "work", 200.50, date1);
        Income income2 = new Income(user1, "freelance", 300.75, date2);
        Income income3 = new Income(user1, "dividends", 150.25, date3);

        incomeRepository.save(income1);
        incomeRepository.save(income2);
        incomeRepository.save(income3);

        Expense expense1 = new Expense(user1, "Food", 230.12, date1);
        Expense expense2 = new Expense(user1, "Sport things", 121, date2);
        Expense expense3 = new Expense(user1, "Dunkin dounts", 12.12, date3);

        expenseRepository.save(expense1);
        expenseRepository.save(expense2);
        expenseRepository.save(expense3);

        // Create and save a report
        startDate = LocalDate.of(2023, 1, 1);
        endDate = LocalDate.of(2023, 1, 31);
        totalIncome = income1.getAmount() + income2.getAmount() + income3.getAmount();
        totalExpense = expense1.getAmount() + expense2.getAmount() + expense3.getAmount();
        updatedBalance = totalIncome - totalExpense;

        report1 = new Report(startDate, endDate, totalIncome, totalExpense, updatedBalance, user1);
        report1 = reportRepository.save(report1);
    }

        @AfterEach
    public void tearDown() {
        reportRepository.deleteById(2);
    }
    @Test
    public void getReport() {
        // Call the service method to generate a list of reports
        List<Report> reports = reportServiceImpl.generateReport(user1.getUserId(), report1);

        // Perform assertions
        assertNotNull(reports);
        assertFalse(reports.isEmpty());  // Ensure the list is not empty

        // Assuming you want to check the first report in the list
        Report savedReport = reports.get(0);

        // Perform assertions on the first report
        assertNotNull(savedReport);
        assertEquals(user1, savedReport.getUser());
        assertEquals(startDate, savedReport.getStartDate());
        assertEquals(totalIncome, savedReport.getTotalIncome());
        assertEquals(totalExpense, savedReport.getTotalExpense());
        assertEquals("651.5", savedReport.getUpdatedBalance());
    }
}