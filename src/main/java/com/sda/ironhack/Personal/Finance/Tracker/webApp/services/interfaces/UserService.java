package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
}