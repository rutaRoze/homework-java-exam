package com.roze.book_recommendation_app.persistance;

import com.roze.book_recommendation_app.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByEmailIgnoreCase(String email);
    Optional<User> findByEmail(String email);
}
