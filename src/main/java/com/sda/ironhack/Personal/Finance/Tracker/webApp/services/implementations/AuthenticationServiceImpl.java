package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.JwtAuthenticationRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.SignInRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.SignUpRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.Role;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.repos.UserRepository;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.AuthenticationService;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Override
    public User signUp(SignUpRequest signUpRequest) {
        User user = new User();

        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
    public JwtAuthenticationRequest signIn(SignInRequest signInRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signInRequest.getUsername(), signInRequest.getPassword()));
        var user = userRepository.findByUsername(signInRequest.getUsername());
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        var token = jwtService.generateToken(user);
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();

        jwtAuthenticationRequest.setToken(token);
        return jwtAuthenticationRequest;
    }
}
