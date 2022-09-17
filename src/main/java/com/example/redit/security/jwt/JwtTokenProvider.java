package com.example.redit.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final Environment environment;
    private String secretKey;
    private Long expirationTime;

    public String createToken(String username) {
        secretKey = environment.getProperty("jwt.token.secretKey");
        expirationTime = Long.valueOf(environment.getProperty("jwt.token.expiration_time"));
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTime))
                .sign(Algorithm.HMAC512(secretKey));

    }



}
