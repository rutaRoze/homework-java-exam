package com.roze.book_recommendation_app.controller;

import com.roze.book_recommendation_app.dto.request.CategoryRequest;
import com.roze.book_recommendation_app.dto.response.CategoryResponse;
import com.roze.book_recommendation_app.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse userResponse = categoryService.saveCategory(categoryRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @Min(1) @PathVariable Long id) {
        CategoryResponse userResponse = categoryService.findCategoryById(id);

        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategoryById(
            @Min(1) @PathVariable Long id,
            @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse userResponse = categoryService.updateCategoryById(id, categoryRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(
            @Min(1) @PathVariable Long id) {
        categoryService.deleteCategoryById(id);

        return ResponseEntity.ok(String.format("Category by ID %d was successfully deleted from data base", id));
    }
}
