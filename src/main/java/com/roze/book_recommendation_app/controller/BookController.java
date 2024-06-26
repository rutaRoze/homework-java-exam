package com.roze.book_recommendation_app.controller;

import com.roze.book_recommendation_app.dto.request.BookRequest;
import com.roze.book_recommendation_app.dto.response.BookResponse;
import com.roze.book_recommendation_app.service.BookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.saveBook(bookRequest);

        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(
            @Min(1) @PathVariable Long id) {
        BookResponse bookResponse = bookService.findBookById(id);

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> bookList = bookService.findAllBooks();

        return ResponseEntity.ok(bookList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBookById(
            @Min(1) @PathVariable Long id,
            @Valid @RequestBody BookRequest bookRequest) {
        BookResponse bookResponse = bookService.updateBookById(id, bookRequest);

        return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(
            @Min(1) @PathVariable Long id) {
        bookService.deleteBookById(id);

        return ResponseEntity.ok(String.format("Book by ID %d was successfully deleted from data base", id));
    }

    @GetMapping("/category")
    public ResponseEntity<List<BookResponse>> getBooksByCategory(
            @RequestParam("category") @NotBlank String categoryName) {
        List<BookResponse> bookListByCategory = bookService.findBooksByCategory(categoryName);

        return ResponseEntity.ok(bookListByCategory);
    }

    @GetMapping("/title")
    public ResponseEntity<List<BookResponse>> getBooksByTitle(
            @RequestParam("title") @NotBlank String bookTitle) {
        List<BookResponse> bookListByName = bookService.findBooksByTitle(bookTitle);

        return ResponseEntity.ok(bookListByName);
    }
}
