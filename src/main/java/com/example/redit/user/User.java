package com.example.redit.user;


import lombok.AccessLevel;
import lombok.Getter;

import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private String name;


    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }


    public void changeRole(Role role) {
        this.role = role;
    }

    public void setBcryptPassword(String password) {
        this.password = password;
    }
}
