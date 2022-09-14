package com.example.redit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;


    public User register(UserRegisterRequest userRegisterRequest) {
        User user = userRegisterRequest.toEntity();
        return userRepository.save(user);


    }
}

