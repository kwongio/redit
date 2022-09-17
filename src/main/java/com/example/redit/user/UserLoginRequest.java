package com.example.redit.user;


import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;


@Getter
@AllArgsConstructor
public final class UserLoginRequest {
    @NotBlank(message = "아이디를 입력하시오")
    private final String username;
    @NotBlank(message = "패스워드를 입력하시오")
    private final String password;
}
