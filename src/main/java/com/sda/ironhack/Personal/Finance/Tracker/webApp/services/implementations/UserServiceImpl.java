package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.UserRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(int userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "User deleted successfully";
        }
        else {
            return "User not found";
        }
    }

    @Override
    public String updateUser(int userId, User user) {
        Optional<User> userFound = userRepository.findById(userId);
        if(userFound.isPresent()){
            //2. Update the userName
            user.setUsername(user.getUsername());
            //3. Save it in the Database
            userRepository.save(user);
            return "User Details Updated";
        }
        else {
            return "User with ID: " + userId + " is not found";
        }
    }
    @Override
    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserDetails userDetails = userRepository.findByUsername(username);
                if (userDetails == null) {
                    throw new UsernameNotFoundException("User not found");
                }
                return userDetails;
            }
        };
    }

    public void updateUserBalance(int userId, double newBalance) {
        // Find the user by userId
        User user = userRepository.findByUserId(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found with userId: " + userId);
        }

        // Perform any necessary validation or additional logic here

        // Update the user's balance
        user.setBalance(newBalance);

        // Save the updated User object to the database
        userRepository.save(user);
    }
}

//    @Override
//    public void updateUserBalance(User user, int newBalance) {
//        // Perform balance update logic, e.g., validate the new balance
//        user.setBalance(newBalance); // Update the user's balance
//        userRepository.save(user); // Save the updated user to the database
//    }