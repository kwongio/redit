package com.example.redit.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public final class UserRegisterRequest {


    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;

    public User toEntity() {
        return new User(username, password, name);
    }
}
