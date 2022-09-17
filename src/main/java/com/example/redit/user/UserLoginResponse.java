package com.example.redit.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public final class UserLoginResponse {
    private final Long id;
    private final String username;
    private final String password;
    private String name;
    private Role role;

}
