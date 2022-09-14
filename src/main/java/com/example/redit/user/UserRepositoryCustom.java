package com.example.redit.user;

import com.example.redit.post.Post;

import java.util.List;

public interface UserRepositoryCustom {

    User select();

    List<Post> getPostList(Long userId);
}
