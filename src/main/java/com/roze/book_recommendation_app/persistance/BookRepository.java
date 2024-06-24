package com.roze.book_recommendation_app.persistance;

import com.roze.book_recommendation_app.persistance.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByIsbnIgnoreCase(String isbn);
}
