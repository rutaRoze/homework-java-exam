package com.roze.book_recommendation_app.mapper;

import com.roze.book_recommendation_app.dto.request.CategoryRequest;
import com.roze.book_recommendation_app.dto.request.UserRequest;
import com.roze.book_recommendation_app.dto.response.CategoryResponse;
import com.roze.book_recommendation_app.dto.response.UserResponse;
import com.roze.book_recommendation_app.persistance.entity.Category;
import com.roze.book_recommendation_app.persistance.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category categoryRequestToCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.getName())
                .build();
    }

    public CategoryResponse categoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
