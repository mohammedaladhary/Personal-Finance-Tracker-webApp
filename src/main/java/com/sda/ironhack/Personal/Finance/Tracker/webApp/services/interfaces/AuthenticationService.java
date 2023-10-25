package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.JwtAuthenticationRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.SignInRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.SignUpRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.entities.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationRequest signIn(SignInRequest signInRequest);
}
