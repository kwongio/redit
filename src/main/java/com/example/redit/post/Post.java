package com.example.redit.post;


import com.example.redit.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
@Getter
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;

    private String content;
    @JoinColumn()
    private Long userId;

    public Post(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }
}
