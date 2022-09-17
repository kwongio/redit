package com.example.redit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserLoginController {


    private final UserLoginService userLoginService;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequest loginRequest, HttpServletResponse response) {
        Map<String, Object> result = userLoginService.login(loginRequest);
        UserLoginResponse userLoginResponse = (UserLoginResponse) result.get("user");
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + result.get("token"));
        return ResponseEntity.ok().body(userLoginResponse);
    }
}
