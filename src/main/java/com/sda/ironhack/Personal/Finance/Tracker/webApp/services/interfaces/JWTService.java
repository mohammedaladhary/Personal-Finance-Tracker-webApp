package com.sda.ironhack.Personal.Finance.Tracker.webApp.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JWTService {
    public String generateToken(UserDetails userDetails);
    public String extractUsername(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails);

}