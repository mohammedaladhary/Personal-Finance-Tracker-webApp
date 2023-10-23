package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    String deleteUser(int userId);
    String updateUser(int userId, User user);
    User getUserById(int userId);
//    void updateUserBalance(User user, int newBalance);
}