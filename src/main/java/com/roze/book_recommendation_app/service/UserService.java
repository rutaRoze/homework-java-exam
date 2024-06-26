package com.roze.book_recommendation_app.service;

import com.roze.book_recommendation_app.dto.response.UserResponse;
import com.roze.book_recommendation_app.mapper.UserMapper;
import com.roze.book_recommendation_app.persistance.UserRepository;
import com.roze.book_recommendation_app.persistance.entity.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse findUserById(UUID id) {
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found by ID: " + id));

        return userMapper.userToUserResponse(user);
    }
}
