package com.softuni.nextleveltechnologies.models.dtos.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
