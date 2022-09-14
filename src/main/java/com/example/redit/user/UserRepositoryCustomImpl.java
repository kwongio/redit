package com.example.redit.user;

import com.example.redit.post.Post;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.redit.post.QPost.*;
import static com.example.redit.user.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{


    private  final  JPAQueryFactory jpaQueryFactory;


    @Override
    public User select() {
        return jpaQueryFactory.selectFrom(user).where(user.id.eq(1L)).fetchOne();
    }

    @Override
    public List<Post> getPostList(Long userId) {
        return jpaQueryFactory.selectFrom(post).join(user).on(post.userId.eq(user.id)).where(user.id.eq(userId)).fetch();
    }

}

