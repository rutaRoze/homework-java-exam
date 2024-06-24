package com.roze.book_recommendation_app.exception;

public class CategoryAlreadyExist extends RuntimeException {
    public CategoryAlreadyExist(String message) {
        super(message);
    }
}
