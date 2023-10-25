package com.sda.ironhack.Personal.Finance.Tracker.webApp.controllers;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.JwtAuthenticationRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.SignInRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.dto.SignUpRequest;
import com.sda.ironhack.Personal.Finance.Tracker.webApp.services.implementations.AuthenticationServiceImpl;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/financeTracker/auth")
@NoArgsConstructor
public class AuthControllerImpl {
    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping(value = "/dashBoard/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest){
        try{
            authenticationService.signUp(signUpRequest);
            String message = "your account added successfully";
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        catch (Exception e){
            String message = "account not added\n" + e.getMessage();
            return ResponseEntity.badRequest().body(message);
        }
    }

    @PostMapping(value = "/dashBoard/signIn")
    public ResponseEntity<JwtAuthenticationRequest> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }
}
