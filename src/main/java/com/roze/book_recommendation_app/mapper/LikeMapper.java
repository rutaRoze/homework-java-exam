package com.roze.book_recommendation_app.mapper;

import com.roze.book_recommendation_app.dto.response.LikeResponse;
import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Like;
import com.roze.book_recommendation_app.persistance.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {
    public Like requestToLike(User user, Book book) {
        return Like.builder()
                .user(user)
                .book(book)
                .build();
    }

    public LikeResponse likeToResponse(Like like) {
        return LikeResponse.builder()
                .id(like.getId())
                .userName(like.getUser().getName())
                .bookTitle(like.getBook().getTitle())
                .build();
    }
}