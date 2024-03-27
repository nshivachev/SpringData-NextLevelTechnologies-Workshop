package com.softuni.nextleveltechnologies.controllers;

import com.softuni.nextleveltechnologies.models.dtos.users.UserLoginDto;
import com.softuni.nextleveltechnologies.models.dtos.users.UserRegisterDto;
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

    @PostMapping("/users/register")
    public String doRegister(UserRegisterDto userRegisterDto) {
        String userName = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String confirmPassword = userRegisterDto.getConfirmPassword();
        String email = userRegisterDto.getEmail();

        if (!userName.isBlank()
                && !password.isBlank()
                && !email.isBlank()
                && password.equals(confirmPassword)
                && userService.register(userRegisterDto).isEmpty()) {

            return "redirect:/";
        }

        return "user/register";
    }

    @GetMapping("logout")
    public String logout() {
        return "redirect:/";
    }
}
