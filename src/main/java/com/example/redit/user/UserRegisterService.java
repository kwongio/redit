package com.example.redit.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRegisterService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(UserRegisterRequest userRegisterRequest) {
        User user = userRegisterRequest.toEntity();

        Optional<User> DuplicateUser = userRepository.findByUsername(user.getUsername());

        if (DuplicateUser.isPresent()) {
            throw new UserDuplicateException("이미 존재하는 아이디입니다.");
        }
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setBcryptPassword(password);

        return userRepository.save(user);


    }
}

