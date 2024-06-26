package com.roze.book_recommendation_app.persistance;

import com.roze.book_recommendation_app.persistance.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByBookId(Long bookId);
    List<Like> findByUserId(UUID userId);
    void deleteByBookIdAndUserId(Long bookId, UUID userId);
    boolean existsByBookIdAndUserId(Long bookId, UUID userId);
    int countByBookId(Long bookId);
}
