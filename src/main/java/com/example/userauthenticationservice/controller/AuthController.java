package com.example.userauthenticationservice.controller;

import com.example.userauthenticationservice.dtos.LogOutResponseDto;
import com.example.userauthenticationservice.dtos.LoginRequestDto;
import com.example.userauthenticationservice.dtos.LoginResponseDto;
import com.example.userauthenticationservice.dtos.SignupRequestDto;
import com.example.userauthenticationservice.dtos.SignUpResponseDto;
import com.example.userauthenticationservice.model.LoginResponse;
import com.example.userauthenticationservice.model.User;
import com.example.userauthenticationservice.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    @Autowired
    private IAuthService iAuthService;

    // add signup , logout and login methods here
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) throws Exception {
        String email = signupRequestDto.getEmail();
        String password = signupRequestDto.getPassword();
        String firstName = signupRequestDto.getFirstName();
        String lastName = signupRequestDto.getLastName();
        String username = signupRequestDto.getUsername();
        User user = iAuthService.signUp(email, password, firstName, lastName, username);
        SignUpResponseDto userDto = new SignUpResponseDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        return ResponseEntity.ok(userDto);
    }



    // add a method to signout the user
    @PostMapping("/logout")
    public LogOutResponseDto logout(@RequestBody String token) throws Exception
    {
        // Implement the logout method
        // This method should invalidate the token and return a response
        // For now, we will just return a success message
        LogOutResponseDto logOutResponseDto = new LogOutResponseDto();
        logOutResponseDto.setMessage("User logged out successfully");
        return logOutResponseDto;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) throws Exception

        // Implement the login method
        // This method should authenticate the user and return a token
        // For now, we will just return a success message
        // You can use the IAuthService to handle the authentication logic
        // For example:
    {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();
            LoginResponse loginResponse = iAuthService.login(email, password);
            LoginResponseDto loginResponseDto = new LoginResponseDto();
            loginResponseDto.setId(loginResponse.getId());
            loginResponseDto.setEmail(loginResponse.getEmail());
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Authorization", "Bearer " + loginResponse.getToken());
            headers.add("Content-Type", "application/json");
            headers.add("Access-Control-Expose-Headers", "Authorization");
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            headers.add(HttpHeaders.COOKIE, "token=" + loginResponse.getToken());


            return new ResponseEntity<>(loginResponseDto, headers, HttpStatus.OK);
        }
        catch (Exception e) {
            // Handle the exception and return an appropriate response
            return new ResponseEntity<>(null, null, HttpStatus.UNAUTHORIZED);
        }

    }
    // post api for validate token generated during login process
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestBody String token)
    {
        try{
            // Call the validateToken method from IAuthService
            Boolean result = iAuthService.validateToken(token);
            return new ResponseEntity<> (result, null, 201);
        } catch (Exception e) {
            // Handle the exception and return an appropriate response
            return new ResponseEntity<>(false, null, 401);
        }

    }



}
