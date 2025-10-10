package com.sa.client_service.reviews.domain;

import java.util.UUID;

public class Review {
    private UUID clientId;
    private UUID targetId;
    private Rating roomRating;
    private Rating movieRating;
    private String comment;

    public Review(UUID clientId,
    UUID targetId,
    String sourceId, Rating roomRating, Rating movieRating, String comment) {
        this.clientId = clientId;
        this.targetId = targetId;
        this.roomRating = roomRating;
        this.movieRating = movieRating;
        this.comment = comment == null ? "" : comment.trim();
    }
}
