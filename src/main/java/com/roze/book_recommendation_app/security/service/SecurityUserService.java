package com.roze.book_recommendation_app.security.service;

import com.roze.book_recommendation_app.persistance.UserRepository;
import com.roze.book_recommendation_app.security.mapper.SecurityUserDetailsMapper;
import com.roze.book_recommendation_app.security.model.SecurityUserDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityUserDetailsMapper securityUserDetailsMapper;


    public SecurityUserDetails findUserByEmail(String email) {
        return securityUserDetailsMapper
                .mapToSecurityUserDetails(userRepository.findByEmail(email)
                        .orElseThrow(() -> new EntityNotFoundException("User not found")));
    }
}
