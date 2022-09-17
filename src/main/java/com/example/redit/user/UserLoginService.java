package com.example.redit.user;

import com.example.redit.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Map<String, Object> login(UserLoginRequest loginRequest) {
        log.info("[로그인 시도중 username : {}  password: {} ]", loginRequest.getUsername(), loginRequest.getPassword());
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new UsernameNotFoundException("존재하는 아이디가 없습니다"));

        if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String jwtToken = jwtTokenProvider.createToken(user.getUsername());
            Map<String, Object> response = new HashMap<>();
            response.put("user", new UserLoginResponse(user.getId(), user.getUsername(), user.getPassword(), user.getName(), user.getRole()));
            response.put("token", jwtToken);
            return response;
        } else {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");

        }
    }

}
