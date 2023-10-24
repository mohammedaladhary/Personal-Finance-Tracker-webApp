package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Expense;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExpenseRepositoryTest {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    private Expense expense1;
    private Expense expense2;
    private Expense expense3;
    private User user1;
    @Autowired
    private IncomeRepository incomeRepository;

    @BeforeEach
    void setUp() {
        user1 = new User("Nawaf", "Changan", "alotayf@legend.com", 10);
        userRepository.save(user1);

        LocalDate date1 = LocalDate.of(2023, 12, 11);
        LocalDate date2 = LocalDate.of(2023, 12, 12);
        LocalDate date3 = LocalDate.of(2023, 12, 13);

        Expense expense1 = new Expense(user1,"Food",230.12,date1);
        Expense expense2 = new Expense(user1,"Sport things",121,date2);
        Expense expense3 = new Expense(user1,"Dunkin dounts",12.12,date3);

        expenseRepository.save(expense1);
        expenseRepository.save(expense2);
        expenseRepository.save(expense3);
    }
    @AfterEach
    void tearDown(){
        incomeRepository.deleteAll();
    }
    @Test
    public void getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAll();
    }

    @Test
    public void getAllExpensesByUserId(){
        // Retrieve a user from the database based on userIdToTest
        User userFromDb = userRepository.findByUserId(user1.getUserId());

        // Retrieve incomes for the specified user
        List<Expense> expenses = expenseRepository.findAllByUser(userFromDb);

        // Add your assertions here to validate the expected results
        assertNotNull(expenses);
        assertEquals(expenses, expenseRepository.findAllByUser(userFromDb));
    }
}