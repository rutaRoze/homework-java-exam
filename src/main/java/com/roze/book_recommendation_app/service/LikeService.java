package com.roze.book_recommendation_app.service;

import com.roze.book_recommendation_app.dto.request.LikeRequest;
import com.roze.book_recommendation_app.dto.response.LikeResponse;
import com.roze.book_recommendation_app.mapper.LikeMapper;
import com.roze.book_recommendation_app.persistance.BookRepository;
import com.roze.book_recommendation_app.persistance.LikeRepository;
import com.roze.book_recommendation_app.persistance.UserRepository;
import com.roze.book_recommendation_app.persistance.entity.Book;
import com.roze.book_recommendation_app.persistance.entity.Like;
import com.roze.book_recommendation_app.persistance.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private BookRepository bookRepository;
    private LikeMapper likeMapper;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserRepository userRepository,
                       BookRepository bookRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.likeMapper = likeMapper;
    }

    public LikeResponse likeBook(LikeRequest likeRequest) {
        User user = getUserByIdOrThrow(likeRequest.getUserId());
        Book book = getBookByIdOrThrow(likeRequest.getBookId());

        Like likeToSave = likeMapper.requestToLike(user, book);
        Like savedLike = likeRepository.save(likeToSave);

        return likeMapper.likeToResponse(savedLike);
    }

    public List<LikeResponse> findLikesByBook(Long id) {
        return likeRepository.findByBookId(id).stream()
                .map(like -> likeMapper.likeToResponse(like))
                .collect(Collectors.toList());
    }

    public List<LikeResponse> findLikesByUser(UUID id) {
        return likeRepository.findByUserId(id).stream()
                .map(like -> likeMapper.likeToResponse(like))
                .collect(Collectors.toList());
    }

    @Transactional
    public void unlikeBook(LikeRequest likeRequest) {
        User user = getUserByIdOrThrow(likeRequest.getUserId());
        Book book = getBookByIdOrThrow(likeRequest.getBookId());

        likeRepository.deleteByBookIdAndUserId(book.getId(), user.getId());
    }

    private User getUserByIdOrThrow(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by ID: " + id));
    }

    private Book getBookByIdOrThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found by ID: " + id));
    }
}