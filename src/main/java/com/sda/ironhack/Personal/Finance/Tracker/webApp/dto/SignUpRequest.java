package com.sda.ironhack.Personal.Finance.Tracker.webApp.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String email;
}
