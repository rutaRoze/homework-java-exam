package com.roze.book_recommendation_app.mapper;

import com.roze.book_recommendation_app.dto.request.CommentRequest;
import com.roze.book_recommendation_app.dto.response.CommentResponse;
import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Comment;
import com.roze.book_recommendation_app.persistance.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment requestToComment(CommentRequest commentRequest, Book book, User user) {
        return Comment.builder()
                .user(user)
                .book(book)
                .userComment(commentRequest.getComment())
                .build();
    }

    public CommentResponse commentToResponse(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .userName(comment.getUser().getName())
                .bookTitle(comment.getBook().getTitle())
                .comment(comment.getUserComment())
                .build();
    }
}