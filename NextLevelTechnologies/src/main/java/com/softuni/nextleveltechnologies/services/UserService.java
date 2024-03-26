package com.softuni.nextleveltechnologies.services;

import com.softuni.nextleveltechnologies.models.dtos.users.UserLoginDto;
import com.softuni.nextleveltechnologies.models.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> login(UserLoginDto loginDto);
}
