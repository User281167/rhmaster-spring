package com.rhmaster.rhmaster.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotBlank(message = "Username is required!")
    @Size(min = 3, message = "Username must have at least 3 characters!")
    @Size(max = 101, message = "Username can have have at most 20 characters!")
    private String userName;

    @NotBlank(message = "Name is required!")
    @Size(min = 3, message = "Name must have at least 3 characters!")
    @Size(max = 50, message = "Name can have have at most 50 characters!")
    private String name;

    @Size(min = 3, message = "LastName must have at least 3 characters!")
    @Size(max = 50, message = "LastName can have have at most 50 characters!")
    private String lastName;

    @Email(message = "Email is not in valid format!")
    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    @Size(min = 8, message = "Password must have atleast 8 characters!")
    @Size(max = 20, message = "Password can have have atmost 20 characters!")
    private String password;

    private Set<String> roles;

    @Autowired
    public SignUpRequestDto(String userName, String name, String lastName, String email, String password) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = null;
    }
}