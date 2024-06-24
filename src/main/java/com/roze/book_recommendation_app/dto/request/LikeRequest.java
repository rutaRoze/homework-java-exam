package com.roze.book_recommendation_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequest {

    @NotNull
    private UUID userId;
    @NotNull
    private Long bookId;
}
