package com.roze.book_recommendation_app.security.service;

import com.roze.book_recommendation_app.dto.request.UserRequest;
import com.roze.book_recommendation_app.dto.response.UserResponse;
import com.roze.book_recommendation_app.exception.UserAlreadyExist;
import com.roze.book_recommendation_app.mapper.UserMapper;
import com.roze.book_recommendation_app.persistance.UserRepository;
import com.roze.book_recommendation_app.persistance.entity.User;
import com.roze.book_recommendation_app.security.mapper.SecurityUserDetailsMapper;
import com.roze.book_recommendation_app.security.model.AuthenticationRequest;
import com.roze.book_recommendation_app.security.model.AuthenticationResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityUserDetailsMapper securityUserDetailsMapper;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserRequest registerRequest) {

        if (checkIfUserExistsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExist(String.format("User with email %s already exists", registerRequest.getEmail()));
        }

        User userToSave = userMapper.userRequestToUser(registerRequest);

        userToSave.setName(sanitizeData(registerRequest.getName()));
        userToSave.setSurname(sanitizeData(registerRequest.getSurname()));
        userToSave.setEmail(sanitizeData(registerRequest.getEmail()).toLowerCase(Locale.ROOT));
        userToSave.setPassword(passwordEncoder.encode(sanitizeData(registerRequest.getPassword())));
        userToSave.setDefaultRole();

        User savedUser = userRepository.save(userToSave);

        String jwtToken = jwtService.generateToken(securityUserDetailsMapper.mapToSecurityUserDetails(savedUser));
        UserResponse userResponse = userMapper.userToUserResponse(savedUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userResponse(userResponse)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticateRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(),
                        authenticateRequest.getPassword()
                )
        );

        User userEntity = userRepository.findByEmail(authenticateRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Entity not hound"));

        String jwtToken = jwtService.generateToken(securityUserDetailsMapper.mapToSecurityUserDetails(userEntity));
        UserResponse userResponse = userMapper.userToUserResponse(userEntity);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userResponse(userResponse)
                .build();
    }

    private boolean checkIfUserExistsByEmail(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    private String sanitizeData(String data) {
        return data.trim();
    }
}
