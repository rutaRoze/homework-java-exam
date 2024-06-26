package com.roze.book_recommendation_app.controller;

import com.roze.book_recommendation_app.dto.request.LikeRequest;
import com.roze.book_recommendation_app.dto.response.LikeResponse;
import com.roze.book_recommendation_app.service.LikeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/like")
@CrossOrigin(origins = "http://localhost:5173")
public class LikeController {

    private LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<LikeResponse> likeBook(
            @Valid @RequestBody LikeRequest likeRequest) {
        LikeResponse likeResponse = likeService.likeBook(likeRequest);

        return new ResponseEntity<>(likeResponse, HttpStatus.CREATED);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<List<LikeResponse>> getUserLikesByBook(
            @Min(1) @PathVariable Long id) {
        List<LikeResponse> likeList = likeService.findLikesByBook(id);

        return ResponseEntity.ok(likeList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<LikeResponse>> getLikedBooksByUser(
            @PathVariable UUID id) {
        List<LikeResponse> likesList = likeService.findLikesByUser(id);

        return ResponseEntity.ok(likesList);
    }

    @GetMapping("/book/{id}/count")
    public ResponseEntity<Integer> countLikesByBook(@PathVariable Long id) {
        int likeCount = likeService.countLikesByBook(id);
        return ResponseEntity.ok(likeCount);
    }

    @DeleteMapping
    public ResponseEntity<String> unlikeBook(
            @Valid @RequestBody LikeRequest likeRequest) {
        likeService.unlikeBook(likeRequest);

        return ResponseEntity.ok(String.format("Book by ID %d was successfully unliked", likeRequest.getBookId()));
    }
}
