package com.example.redit.post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class PostRegisterRequest {

    private final String title;
    private final String content;

    public Post toEntity(Long userId) {
        return new Post(title, content, userId);
    }
}
