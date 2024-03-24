package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.models.dtos.UserLoginDto;
import com.softuni.nextleveltechnologies.models.entities.User;
import com.softuni.nextleveltechnologies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/users/login")
    public String doLogin(UserLoginDto loginDto) {
        Optional<User> user = userService.login(loginDto);

        if (user.isPresent()) {
            return "redirect:/home";
        }

        return "user/login";
    }

    @GetMapping("/users/register")
    public String register() {
        return "user/register";
    }
}
