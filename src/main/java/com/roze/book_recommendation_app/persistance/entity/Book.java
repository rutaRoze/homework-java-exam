package com.roze.book_recommendation_app.persistance.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length =  500, nullable = false)
    private String description;

    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @Column(name = "pictureUrl")
    private String pictureUrl;

    @Column(name = "pageNumber", nullable = false)
    private int pageNumber;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Like> likeList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setBook(this);
    }

    public void removeComment(Comment comment) {
        commentList.remove(comment);
        comment.setBook(null);
    }

    public void addLike(Like like) {
        likeList.add(like);
        like.setBook(this);
    }

    public void removeLike(Like like) {
        likeList.remove(like);
        like.setBook(null);
    }
}
