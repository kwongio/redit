package com.example.redit.user;

import com.example.redit.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserViewController {

    private final UserRepository userRepository;
    @GetMapping("/user")
    public ResponseEntity<?> select() {
        User select = userRepository.select();
        return ResponseEntity.ok(select);
    }


    @GetMapping("/posts/{userId}")
    public ResponseEntity<?> post(@PathVariable Long userId) {
        List<Post> postList = userRepository.getPostList(userId);
        return ResponseEntity.ok(postList);
    }
}
