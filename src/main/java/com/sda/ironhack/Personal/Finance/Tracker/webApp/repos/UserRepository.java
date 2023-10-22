package com.sda.ironhack.Personal.Finance.Tracker.webApp.repos;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
    User findByUsername(String username);
    User findByUserID(int userID);
}
