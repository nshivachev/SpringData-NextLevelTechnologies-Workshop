package com.softuni.nextleveltechnologies.services;

import com.softuni.nextleveltechnologies.models.dtos.users.UserLoginDto;
import com.softuni.nextleveltechnologies.models.dtos.users.UserRegisterDto;
import com.softuni.nextleveltechnologies.models.entities.User;
import com.softuni.nextleveltechnologies.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<User> login(UserLoginDto loginDto) {
        return userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
    }

    @Override
    public Optional<User> register(UserRegisterDto userRegisterDto) {
        Optional<User> user = userRepository.findByUsernameOrEmail(userRegisterDto.getUsername(), userRegisterDto.getEmail());

        if (user.isEmpty()) {
            userRepository.saveAndFlush(modelMapper.map(userRegisterDto, User.class));
        }

        return user;
    }
}
