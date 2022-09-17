package com.example.redit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserRegisterController {
    private final UserRegisterService userRegisterService;


    @PostMapping("/user/register")
    public ResponseEntity<?> register(@Valid  @RequestBody UserRegisterRequest userRegisterRequest) {
        User register = userRegisterService.register(userRegisterRequest);
        return ResponseEntity.ok(register);
    }
}

