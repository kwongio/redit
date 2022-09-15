package com.example.redit.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.redit.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<Post> getPostList() {
        return jpaQueryFactory.selectFrom(post).orderBy(post.id.desc()).fetch();
    }
}
