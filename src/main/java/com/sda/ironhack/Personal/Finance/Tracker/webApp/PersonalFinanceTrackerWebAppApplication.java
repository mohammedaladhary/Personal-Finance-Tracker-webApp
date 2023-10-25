package com.sda.ironhack.Personal.Finance.Tracker.webApp;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Role;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PersonalFinanceTrackerWebAppApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceTrackerWebAppApplication.class, args);
	}

	@Override
	public void run(String... args) {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(adminAccount == null){
			User user = new User();

			user.setUsername("admin");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			user.setEmail("admin@gmail.com");
			user.setRole(Role.ADMIN);
			userRepository.save(user);
		}
	}
}