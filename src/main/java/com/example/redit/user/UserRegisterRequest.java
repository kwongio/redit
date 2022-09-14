package com.example.redit.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UserRegisterRequest {

    private final String username;
    private final String password;
    private final String name;

    public User toEntity() {
        return new User(username, password, name);
    }
}
