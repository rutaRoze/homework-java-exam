package com.roze.book_recommendation_app.dto.response;

import com.roze.book_recommendation_app.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID uuid;
    private String name;
    private String surname;
    private String email;
    private Role role;
}
