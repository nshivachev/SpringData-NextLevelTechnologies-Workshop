package com.softuni.nextleveltechnologies.services;

import com.softuni.nextleveltechnologies.models.dtos.UserLoginDto;
import com.softuni.nextleveltechnologies.models.entities.User;
import com.softuni.nextleveltechnologies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> login(UserLoginDto loginDto) {
        return userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
    }
}
