package com.roze.book_recommendation_app.persistance.entity;

import com.roze.book_recommendation_app.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likeList = new ArrayList<>();

    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment) {
        commentList.remove(comment);
        comment.setUser(null);
    }

    public void addLike(Like like) {
        likeList.add(like);
        like.setUser(this);
    }

    public void removeLike(Like like) {
        likeList.remove(like);
        like.setUser(null);
    }

    public void setDefaultRole() {
        role = Role.USER;
    }
}
