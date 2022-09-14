package com.example.redit.post;

import com.example.redit.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostRegisterController {
    private final PostRegisterService postRegisterService;


    @PostMapping("/post/{userId}")
    public ResponseEntity<?> register(@RequestBody PostRegisterRequest postRegisterRequest, @PathVariable Long userId) {
        Post register = postRegisterService.register(postRegisterRequest, userId);
        return ResponseEntity.ok(register);
    }
}

