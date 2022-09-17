package com.example.redit.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.redit.user.PrincipalDetails;
import com.example.redit.user.User;
import com.example.redit.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private UserRepository userRepository;
    private Environment environment;

    public JwtAuthorizationFilter( UserRepository userRepository, Environment environment) {
        this.userRepository = userRepository;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization_token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isTokenValid(authorization_token)) {
            setAuthentication(authorization_token); // 이동할 때 마다 유효한 토큰인지 검사하고 securityContextHolder에 넣어줌 세션과 동일한 방식임
        }
        chain.doFilter(request, response);
    }

    private void setAuthentication(String authorization_token) {
        String username = getTokenSubject(authorization_token);
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("토큰이 만료되었습니다."));
        PrincipalDetails principalDetails = new PrincipalDetails(user);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private String getTokenSubject(String authorization_token) {
        String token = authorization_token.replace("Bearer ", "");
        String username = JWT.require(Algorithm.HMAC512(environment.getProperty("jwt.token.secretKey")))
                .build()
                .verify(token)
                .getSubject();
        if (username == null) {
            throw new UsernameNotFoundException("토큰이 만료되었습니다");
        }
        return username;
    }

    private boolean isTokenValid(String authorization_token) {
        return authorization_token != null && authorization_token.startsWith("Bearer ");
    }
}
