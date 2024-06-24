package com.roze.book_recommendation_app.service;

import com.roze.book_recommendation_app.dto.request.BookRequest;
import com.roze.book_recommendation_app.dto.response.BookResponse;
import com.roze.book_recommendation_app.exception.CategoryAlreadyExist;
import com.roze.book_recommendation_app.exception.NoChangesMadeException;
import com.roze.book_recommendation_app.mapper.BookMapper;
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
public class BookService {

    BookRepository bookRepository;
    CategoryService categoryService;
    CategoryRepository categoryRepository;
    BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, CategoryService categoryService,
                       CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }

    public BookResponse saveBook(BookRequest bookRequest) {

        if (checkIfIsbnExists(bookRequest.getIsbn())) {
            throw new CategoryAlreadyExist(String.format("Book by ISBN %s already exists", bookRequest.getIsbn()));
        }
        Category category = categoryService.getCategoryByIdOrThrow(bookRequest.getCategoryId());
        Book bookToSave = bookMapper.requestToBook(bookRequest, category);
        Book savedBook = bookRepository.save(bookToSave);

        return bookMapper.bookToResponse(savedBook);
    }

    public BookResponse findBookById(Long id) {
        Book book = getBookByIdOrThrow(id);
        return bookMapper.bookToResponse(book);
    }

    public List<BookResponse> findAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> bookMapper.bookToResponse(book))
                .collect(Collectors.toList());
    }

    public BookResponse updateBookById(Long id, BookRequest bookRequest) {
        Book existingBook = getBookByIdOrThrow(id);
        Category category = categoryService.getCategoryByIdOrThrow(bookRequest.getCategoryId());

        if (isChangesMade(existingBook, bookRequest)) {
            throw new NoChangesMadeException("Book entry was not updated as no changes of entry were made");
        }

        if (!existingBook.getIsbn().equals(bookRequest.getIsbn()) && (checkIfIsbnExists(bookRequest.getIsbn()))) {
                throw new CategoryAlreadyExist(String.format("Book by ISBN %s already exists", bookRequest.getIsbn()));
        }

        updateBookData(existingBook, bookRequest, category);
        Book savedBook = bookRepository.save(existingBook);

        return bookMapper.bookToResponse(savedBook);
    }

    public void deleteBookById(Long id) {
        getBookByIdOrThrow(id);
        bookRepository.deleteById(id);
    }

    private boolean checkIfIsbnExists(String isbn) {
        return bookRepository.existsByIsbnIgnoreCase(isbn);
    }

    private Book getBookByIdOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found by ID: " + id));
    }

    private boolean isChangesMade(Book existingBook, BookRequest bookRequest) {
        return existingBook.getTitle().equals(bookRequest.getTitle().trim()) &&
                existingBook.getDescription().equals(bookRequest.getDescription().trim()) &&
                existingBook.getIsbn().equals(bookRequest.getIsbn().trim()) &&
                existingBook.getPictureUrl().equals(bookRequest.getPictureUrl().trim()) &&
                existingBook.getPageNumber() == bookRequest.getPageNumber() &&
                existingBook.getCategory().getId().equals(bookRequest.getCategoryId());
    }

    private void updateBookData(Book existingBook, BookRequest bookRequest, Category category) {
        existingBook.setTitle(bookRequest.getTitle().trim());
        existingBook.setDescription(bookRequest.getDescription().trim());
        existingBook.setIsbn(bookRequest.getIsbn().trim());
        existingBook.setPictureUrl(bookRequest.getPictureUrl().trim());
        existingBook.setPageNumber(bookRequest.getPageNumber());
        existingBook.setCategory(category);
    }
}
