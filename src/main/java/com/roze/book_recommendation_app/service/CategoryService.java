package com.roze.book_recommendation_app.service;

import com.roze.book_recommendation_app.dto.request.CategoryRequest;
import com.roze.book_recommendation_app.dto.response.CategoryResponse;
import com.roze.book_recommendation_app.exception.CategoryAlreadyExist;
import com.roze.book_recommendation_app.exception.NoChangesMadeException;
import com.roze.book_recommendation_app.mapper.CategoryMapper;
import com.roze.book_recommendation_app.persistance.BookRepository;
import com.roze.book_recommendation_app.persistance.CategoryRepository;
import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    BookRepository bookRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper,
                           BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.bookRepository = bookRepository;
    }

    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        if (checkIfCategoryExists(categoryRequest.getName())) {
            throw new CategoryAlreadyExist(String.format("Category by name %s already exists", categoryRequest.getName()));
        }

        Category categoryToSave = categoryMapper.categoryRequestToCategory(categoryRequest);
        categoryToSave.setName(categoryToSave.getName().trim());
        Category savedCategory = categoryRepository.save(categoryToSave);

        return categoryMapper.categoryToCategoryResponse(savedCategory);
    }

    public CategoryResponse findCategoryById(Long id) {
        Category category = getCategoryByIdOrThrow(id);

        return categoryMapper.categoryToCategoryResponse(category);
    }

    public List<CategoryResponse> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.categoryToCategoryResponse(category))
                .collect(Collectors.toList());
    }

    public CategoryResponse updateCategoryById(Long id, CategoryRequest categoryRequest) {
        Category existingCategory = getCategoryByIdOrThrow(id);

        if (isDataEqual(existingCategory, categoryRequest)) {
            throw new NoChangesMadeException("Category entry was not updated as no changes of entry were made");
        }

        existingCategory.setName(categoryRequest.getName().trim());
        Category updatedCategory = categoryRepository.save(existingCategory);

        return categoryMapper.categoryToCategoryResponse(updatedCategory);
    }

    public void deleteCategoryById(Long id) {
        getCategoryByIdOrThrow(id);

        List<Book> bookList = bookRepository.findAllByCategoryId(id);
        bookList.forEach(book -> book.setCategory(null));
        bookRepository.saveAll(bookList);

        categoryRepository.deleteById(id);
    }

    public Category getCategoryByIdOrThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found by ID: " + id));
    }

    private boolean checkIfCategoryExists(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }

    private boolean isDataEqual(Category existingCategory, CategoryRequest categoryRequest) {
        return existingCategory.getName().equals(categoryRequest.getName().trim());
    }
}
