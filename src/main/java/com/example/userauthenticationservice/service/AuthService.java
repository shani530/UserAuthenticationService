package com.example.userauthenticationservice.service;

import com.example.userauthenticationservice.exception.UnAuthorizedException;
import com.example.userauthenticationservice.model.LoginResponse;
import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.repo.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {
    // Implement the methods from IAuthService interface

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private SecretKey secretKey;


    @Override
    public User signUp(String email, String password, String firstName, String lastName, String username) throws Exception {
        // Check if the user already exists
        Optional<User> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new Exception("User already exists");
        }
        // Create a new user
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);

        // Save the user to the database
        return userRepo.save(user);
    }

    @Override
    public LoginResponse login(String email, String password) throws Exception {
        // Implementation for logging in a user
        Optional<User> user = userRepo.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        if (user.isPresent() && !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new UnAuthorizedException("Invalid password");
        }
        // Generate a token for the user
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setEmail(user.get().getEmail());
        loginResponse.setId(user.get().getId());
        loginResponse.setToken(generateToken(user.get()));

        return loginResponse;

    }

    // generate token method

    private String generateToken(User user) {
        // Implement token generation logic here
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("startTime", System.currentTimeMillis());
        claims.put("endTime", System.currentTimeMillis() + 3600000); // 1 hour expiration
        claims.put("userId", user.getId());
        claims.put("issuer","scaler");
        claims.put("role",user.getRole());
        claims.put("status",user.getStatus());
        // Here you would typically use a library like JWT to generate a token
        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        return token;
    }

    @Override
    public String logout(String token) throws Exception {
        return null;
    }



    @Override
    public Boolean validateToken(String token) {
        // Implementation for validating a token

             try {
                 JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
                 token = token.trim();
                 Claims claims = jwtParser.parseSignedClaims(token).getPayload();

                 // Validate expiration time
                // Map<String, Object> claims = jwtParser.parseClaimsJws(token).getBody();
                 Long endTime = (Long) claims.get("endTime");
                 Long currentTime = System.currentTimeMillis();
                 if(endTime < currentTime) {
                     throw new RuntimeException("Token expired");
                 }
                 String newToken = Jwts.builder().claims(claims).signWith(secretKey).compact();
                 // Check if the token is valid
                 if(!newToken.equals(token)) {
                     throw new RuntimeException("Invalid token");
                 }



                 // Additional claim validations (if needed)
                 String issuer = claims.getIssuer();
                 if (!"scaler".equals(issuer)) {
                     throw new RuntimeException("Invalid token issuer");
                 }

                 return true; // Token is valid
             } catch (io.jsonwebtoken.ExpiredJwtException e) {
                 throw new RuntimeException("Token expired", e);
             } catch (io.jsonwebtoken.SignatureException e) {
                 throw new RuntimeException("Invalid token signature", e);
             } catch (Exception e) {
                 throw new RuntimeException("Invalid token", e);
             }
        // Parse the token and validate it

    }
}
