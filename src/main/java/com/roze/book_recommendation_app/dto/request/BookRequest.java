package com.roze.book_recommendation_app.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;
    @NotBlank
    @Size(min = 1, max = 300)
    private String description;
    @NotBlank
    @Size(min = 10, max = 20)
    private String isbn;
    @NotBlank
    private String pictureUrl;
    @Min(1)
    private int pageNumber;
    @NotNull
    private Long categoryId;
}
