package com.example.redit.post;

import com.example.redit.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostRegisterService {

    private final PostRepository postRepository;


    public Post register(PostRegisterRequest postRegisterRequest, Long userId) {
        Post post = postRegisterRequest.toEntity(userId);
        Post save = postRepository.save(post);
        return save;


    }
}

