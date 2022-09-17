package com.example.redit.exception;

import com.example.redit.user.UserDuplicateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class UserExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> registerException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        System.out.println(errors.entrySet());
        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFound(UsernameNotFoundException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("username", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> loginValidation(BadCredentialsException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("password", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }


    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<?> usernameDuplicate(UserDuplicateException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("username", e.getMessage());
        return ResponseEntity.badRequest().body(errors);
    }


}
