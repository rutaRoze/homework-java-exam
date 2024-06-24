package com.roze.book_recommendation_app.security.mapper;

import com.roze.book_recommendation_app.persistance.entity.User;
import com.roze.book_recommendation_app.security.model.SecurityUserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUserDetailsMapper {

    public SecurityUserDetails mapToSecurityUserDetails(User userEntity) {
        return SecurityUserDetails.builder()
                .uuid(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();
    }
}
