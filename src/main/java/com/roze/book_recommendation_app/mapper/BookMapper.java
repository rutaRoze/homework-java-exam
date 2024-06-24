package com.roze.book_recommendation_app.mapper;

import com.roze.book_recommendation_app.dto.request.BookRequest;
import com.roze.book_recommendation_app.dto.response.BookResponse;
import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book requestToBook(BookRequest bookRequest, Category category) {
        return Book.builder()
                .title(bookRequest.getTitle())
                .description(bookRequest.getDescription())
                .isbn(bookRequest.getIsbn())
                .pictureUrl(bookRequest.getPictureUrl())
                .pageNumber(bookRequest.getPageNumber())
                .category(category)
                .build();
    }

    public BookResponse bookToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .isbn(book.getIsbn())
                .pictureUrl(book.getPictureUrl())
                .pageNumber(book.getPageNumber())
                .categoryName(book.getCategory().getName())
                .build();
    }
}
