package com.roze.book_recommendation_app.security.controller;

import com.roze.book_recommendation_app.dto.request.UserRequest;
import com.roze.book_recommendation_app.security.model.AuthenticationRequest;
import com.roze.book_recommendation_app.security.model.AuthenticationResponse;
import com.roze.book_recommendation_app.security.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @Valid @RequestBody UserRequest registerRequest
    ) {
        AuthenticationResponse authenticationResponse = authenticationService.register(registerRequest);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @Valid @RequestBody AuthenticationRequest authenticateRequest
    ) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticateRequest));

    }
}
