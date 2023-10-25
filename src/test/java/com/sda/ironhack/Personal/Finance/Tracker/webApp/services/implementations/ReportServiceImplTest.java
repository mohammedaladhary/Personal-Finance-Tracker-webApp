package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Report;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.ExpenseRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.IncomeRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.ReportRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ReportServiceImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IncomeRepository incomeRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ReportServiceImpl reportService;

    private User user1;
    private Report report1;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalIncome;
    private double totalExpense;
    private double updatedBalance;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // Create a user
        user1 = new User("Humood", "password", "john@example.com", 0.0);
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
        userRepository.deleteAll();
    }

    @Test
    void getAllReportsTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/financeTracker/dashBoard/users/reports"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        // Assert the response content here for expected report data
        assertTrue(result.getResponse().getContentAsString().contains("Food"));
    }


}