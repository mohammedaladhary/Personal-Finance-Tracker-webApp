package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Income;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
    List<Income> findAllByUser(User userId);

    List<Income> findAllByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);

//    User findByUserId(int userId);
}