package com.roze.book_recommendation_app.mapper;

import com.roze.book_recommendation_app.dto.request.UserRequest;
import com.roze.book_recommendation_app.dto.response.UserResponse;
import com.roze.book_recommendation_app.persistance.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User userRequestToUser(UserRequest userRequest) {
        return User.builder()
                .name(userRequest.getName())
                .surname(userRequest.getSurname())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }

    public UserResponse userToUserResponse(User user) {
        return UserResponse.builder()
                .uuid(user.getUuid())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
