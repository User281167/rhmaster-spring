package com.rhmaster.rhmaster.Services;

import com.rhmaster.rhmaster.dtos.UserDto;
import com.rhmaster.rhmaster.exceptions.RoleNotFoundException;
import com.rhmaster.rhmaster.factories.RoleFactory;
import com.rhmaster.rhmaster.models.Role;
import com.rhmaster.rhmaster.models.User;
import com.rhmaster.rhmaster.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleFactory roleFactory;

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    public List<UserDto> getAllUser(){
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = users.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setName(user.getName());
                    userDto.setLastname(user.getLastname());
                    userDto.setEmail(user.getEmail());
                    userDto.setEnabled(user.isEnabled());
                    return userDto;
                })
                .toList();

        return userDtos;
    }

    public void setRole(UUID userId, String role) throws RoleNotFoundException {
        User user = userRepository.findById(userId).get();
        Role userRole = roleFactory.getInstance(role);

        user.setRol(userRole);
        userRepository.save(user);
    }
}