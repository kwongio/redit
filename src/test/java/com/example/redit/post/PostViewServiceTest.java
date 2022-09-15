package com.example.redit.post;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostViewServiceTest {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostRepositoryCustomImpl postRepositoryCustom;


    @Test
    @Order(1)
    void d() {
        postRepository.findAll();
        postRepositoryCustom.getPostList();
    }

    @Test
    @Order(2)

    void getPostList() {
        postRepositoryCustom.getPostList();

    }


    @Test
    @Order(3)

    void getList() {
        List<Post> all = postRepository.findAll();
        all.sort((o1, o2) -> (int) (o2.getId() - o1.getId()));
    }


}