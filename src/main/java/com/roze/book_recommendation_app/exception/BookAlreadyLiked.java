package com.roze.book_recommendation_app.exception;

public class BookAlreadyLiked extends RuntimeException {
    public BookAlreadyLiked(String message) {
        super(message);
    }
}
