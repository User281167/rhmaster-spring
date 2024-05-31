package com.rhmaster.rhmaster.controller;

import com.rhmaster.rhmaster.Services.AuthService;
import com.rhmaster.rhmaster.dtos.ApiResponseDto;
import com.rhmaster.rhmaster.dtos.SignInRequestDto;
import com.rhmaster.rhmaster.dtos.SignUpRequestDto;
import com.rhmaster.rhmaster.exceptions.RoleNotFoundException;
import com.rhmaster.rhmaster.exceptions.UserAlreadyExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> signUpUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto<?>> signInUser(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        return authService.signInUser(signInRequestDto);
    }

}
