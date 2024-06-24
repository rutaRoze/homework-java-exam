package com.roze.book_recommendation_app.controller;

import com.roze.book_recommendation_app.dto.request.CommentRequest;
import com.roze.book_recommendation_app.dto.response.CommentResponse;
import com.roze.book_recommendation_app.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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

    @GetMapping("/book/{id}")
    public ResponseEntity<List<CommentResponse>> getCommentsByBook(
            @Min(1) @PathVariable Long id) {
        List<CommentResponse> commentResponse = commentService.findCommentsByBook(id);

        return ResponseEntity.ok(commentResponse);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CommentResponse>> getCommentByUser(
            @PathVariable UUID id) {
        List<CommentResponse> commentResponse = commentService.findCommentsByUser(id);

        return ResponseEntity.ok(commentResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(
            @Min(1) @PathVariable Long id) {
        commentService.deleteCommentById(id);

        return ResponseEntity.ok(String.format("Comment by ID %d was successfully deleted", id));
    }
}
