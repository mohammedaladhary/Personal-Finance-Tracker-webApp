package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

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
class IncomeRepositoryTest {
    @Autowired
    private IncomeRepository incomeRepository;
    @Autowired
    private UserRepository userRepository;
    private Income income1;
    private Income income2;
    private Income income3;
    private User user1;

    @BeforeEach
    void setUp() {
        // Create a user
        user1 = new User("John", "password", "john@example.com", 0);
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
    }
//    @AfterEach
//    void tearDown(){
//        incomeRepository.deleteAll();
//    }

    @Test
    public void getAllIncomes(){
        List<Income> incomes = incomeRepository.findAll();
    }

    @Test
    public void getAllIncomesByUserId() {
        // Retrieve a user from the database based on userIdToTest
        User userFromDb = userRepository.findByUserId(user1.getUserId());

        // Retrieve incomes for the specified user
        List<Income> incomes = incomeRepository.findAllByUser(userFromDb);

        // Add your assertions here to validate the expected results
        assertNotNull(incomes);
        assertEquals(incomes, incomeRepository.findAllByUser(userFromDb));
    }
}