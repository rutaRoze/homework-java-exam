package com.roze.book_recommendation_app.config;

import com.roze.book_recommendation_app.enums.Role;
import com.roze.book_recommendation_app.persistance.BookRepository;
import com.roze.book_recommendation_app.persistance.CategoryRepository;
import com.roze.book_recommendation_app.persistance.UserRepository;
import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Category;
import com.roze.book_recommendation_app.persistance.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitialData {

    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private BookRepository bookRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public InitialData(UserRepository userRepository, CategoryRepository categoryRepository,
                       BookRepository bookRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner initialDataRunner() {
        return runner -> {
            addUsers();
            addCategories();
            addBooks();
        };
    }

    private void addUsers() {
        final User user1 = User.builder()
                .name("John")
                .surname("Smith")
                .email("jhon@mail.com")
                .password(passwordEncoder.encode("Password1!"))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user1);

        final User user2 = User.builder()
                .name("Emily")
                .surname("Johnson")
                .email("emily@mail.com")
                .password(passwordEncoder.encode("Password1!"))
                .role(Role.USER)
                .build();
        userRepository.save(user2);

        final User user3 = User.builder()
                .name("David")
                .surname("Brown")
                .email("david@mail.com")
                .password(passwordEncoder.encode("Password1!"))
                .role(Role.USER)
                .build();
        userRepository.save(user3);

        final User user4 = User.builder()
                .name("Sophia")
                .surname("Williams")
                .email("sophia@mail.com")
                .password(passwordEncoder.encode("Password1!"))
                .role(Role.USER)
                .build();
        userRepository.save(user4);
    }

    private void addCategories() {
        final Category category1 = Category.builder()
                .name("Fantasy")
                .build();
        categoryRepository.save(category1);

        final Category category2 = Category.builder()
                .name("Drama")
                .build();
        categoryRepository.save(category2);

        final Category category3 = Category.builder()
                .name("Romantic")
                .build();
        categoryRepository.save(category3);

        final Category category4 = Category.builder()
                .name("Dystopia")
                .build();
        categoryRepository.save(category4);
    }

    private void addBooks() {
        Category category1 = categoryRepository.findById(1L).orElseThrow(() -> new RuntimeException("Category not found"));
        Category category2 = categoryRepository.findById(2L).orElseThrow(() -> new RuntimeException("Category not found"));
        Category category3 = categoryRepository.findById(3L).orElseThrow(() -> new RuntimeException("Category not found"));
        Category category4 = categoryRepository.findById(4L).orElseThrow(() -> new RuntimeException("Category not found"));

        final Book book1 = Book.builder()
                .title("The Hobbit")
                .description("Fantasy novel about Bilbo Baggins' adventure")
                .isbn("9780547928227")
                .pictureUrl("https://example.com/the-hobbit.jpg")
                .pageNumber(300)
                .category(category1)
                .build();
        bookRepository.save(book1);

        final Book book2 = Book.builder()
                .title("To Kill a Mockingbird")
                .description("Classic novel by Harper Lee")
                .isbn("9780061120084")
                .pictureUrl("https://example.com/to-kill-a-mockingbird.jpg")
                .pageNumber(336)
                .category(category2)
                .build();
        bookRepository.save(book2);

        final Book book3 = Book.builder()
                .title("1984")
                .description("Dystopian novel by George Orwell")
                .isbn("9780451524935")
                .pictureUrl("https://example.com/1984.jpg")
                .pageNumber(328)
                .category(category4)
                .build();
        bookRepository.save(book3);

        final Book book4 = Book.builder()
                .title("Pride and Prejudice")
                .description("Romantic novel by Jane Austen")
                .isbn("9780141439518")
                .pictureUrl("https://example.com/pride-and-prejudice.jpg")
                .pageNumber(416)
                .category(category3)
                .build();
        bookRepository.save(book4);

        final Book book5 = Book.builder()
                .title("The Catcher in the Rye")
                .description("Novel by J.D. Salinger")
                .isbn("9780316769488")
                .pictureUrl("https://example.com/the-catcher-in-the-rye.jpg")
                .pageNumber(224)
                .category(category2)
                .build();
        bookRepository.save(book5);
    }
}
