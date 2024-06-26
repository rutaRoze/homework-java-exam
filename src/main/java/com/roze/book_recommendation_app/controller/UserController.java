package com.roze.book_recommendation_app.controller;

import com.roze.book_recommendation_app.dto.response.UserResponse;
import com.roze.book_recommendation_app.service.UserService;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @Min(1) @PathVariable UUID id) {
        UserResponse userResponse = userService.findUserById(id);

        return ResponseEntity.ok(userResponse);
    }
}
