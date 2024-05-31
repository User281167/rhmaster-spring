package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.ApiResponseDto;
import com.rhmaster.rhmaster.dtos.SignInRequestDto;
import com.rhmaster.rhmaster.dtos.SignUpRequestDto;
import com.rhmaster.rhmaster.exceptions.RoleNotFoundException;
import com.rhmaster.rhmaster.exceptions.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;

    ResponseEntity<ApiResponseDto<?>> signInUser(SignInRequestDto signInRequestDto);
}
