package com.roze.book_recommendation_app.persistance;

import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbnIgnoreCase(String isbn);
    List<Book> findByTitleIgnoreCase(String bookName);
    List<Book> findByCategory(Category category);
    List<Book> findAllByCategoryId(Long categoryId);
}
