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
                .description("Bilbo Baggins is a hobbit who enjoys a comfortable, unambitious life, " +
                        "rarely traveling any farther than his pantry or cellar. " +
                        "But his contentment is disturbed when the wizard Gandalf and a company of " +
                        "dwarves arrive on his doorstep one day to whisk him away on an adventure.")
                .isbn("9780547928227")
                .pictureUrl("https://m.media-amazon.com/images/I/61Ng-W9EhBL.jpg")
                .pageNumber(300)
                .category(category1)
                .build();
        bookRepository.save(book1);

        final Book book2 = Book.builder()
                .title("To Kill a Mockingbird")
                .description("A gripping, heart-wrenching, and wholly remarkable tale of coming-of-age in a " +
                        "South poisoned by virulent prejudice, it views a world of great beauty and savage inequities " +
                        "through the eyes of a young girl, as her father—a crusading local lawyer—risks everything to " +
                        "defend a black man unjustly accused of a terrible crime.")
                .isbn("9780061120084")
                .pictureUrl("https://m.media-amazon.com/images/I/81aY1lxk+9L._SL1500_.jpg")
                .pageNumber(336)
                .category(category2)
                .build();
        bookRepository.save(book2);

        final Book book3 = Book.builder()
                .title("1984")
                .description("Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. " +
                        "With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes " +
                        "those who dare to commit thoughtcrimes. " +
                        "But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching...")
                .isbn("9780451524935")
                .pictureUrl("https://m.media-amazon.com/images/I/71rpa1-kyvL._SL1500_.jpg")
                .pageNumber(328)
                .category(category4)
                .build();
        bookRepository.save(book3);

        final Book book4 = Book.builder()
                .title("Pride and Prejudice")
                .description("Few have failed to be charmed by the witty and independent spirit of Elizabeth Bennet in Austen’s beloved classic Pride and Prejudice. ")
                .isbn("9780141439518")
                .pictureUrl("https://m.media-amazon.com/images/I/91eKRbuhgaL._SL1500_.jpg")
                .pageNumber(416)
                .category(category3)
                .build();
        bookRepository.save(book4);

        final Book book5 = Book.builder()
                .title("The Catcher in the Rye")
                .description("Holden narrates the story of a couple of days in his sixteen-year-old life, just after he's been expelled from prep school.")
                .isbn("9780316769488")
                .pictureUrl("https://m.media-amazon.com/images/I/614LavjvoLL._SL1000_.jpg")
                .pageNumber(224)
                .category(category2)
                .build();
        bookRepository.save(book5);

        final Book book6 = Book.builder()
                .title("Dune")
                .description("Set on the desert planet Arrakis, Dune is the story of the boy Paul Atreides, " +
                        "heir to a noble family tasked with ruling an inhospitable world where the only thing " +
                        "of value is the “spice” melange, " +
                        "a drug capable of extending life and enhancing consciousness. " +
                        "Coveted across the known universe, melange is a prize worth killing for...")
                .isbn("9780316769487")
                .pictureUrl("https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1555447414i/44767458.jpg")
                .pageNumber(700)
                .category(category1)
                .build();
        bookRepository.save(book6);
    }
}
