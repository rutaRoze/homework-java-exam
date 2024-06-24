package com.roze.book_recommendation_app.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private String pictureUrl;
    private int pageNumber;
    private String categoryName;
}
