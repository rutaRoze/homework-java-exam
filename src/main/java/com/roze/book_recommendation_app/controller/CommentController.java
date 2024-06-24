package com.roze.book_recommendation_app.controller;

import com.roze.book_recommendation_app.dto.request.CommentRequest;
import com.roze.book_recommendation_app.dto.response.CommentResponse;
import com.roze.book_recommendation_app.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @Valid @RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.saveComment(commentRequest);

        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }
}
