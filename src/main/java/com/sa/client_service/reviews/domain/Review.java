package com.sa.client_service.reviews.domain;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Review {
    private UUID clientId;
    private UUID cinemaId;
    private UUID roomId;
    private UUID movieId;
    private Rating roomRating;
    private Rating movieRating;
    private String comment;

    public Review(UUID id,
            UUID clientId,
            UUID cinemaId, UUID roomId, UUID movieId,
            Rating roomRating, Rating movieRating, String comment) {
        this.clientId = clientId;
        this.cinemaId = cinemaId;
        this.roomId = roomId;
        this.movieId = movieId;
        this.roomRating = roomRating;
        this.movieRating = movieRating;
        this.comment = comment == null ? "" : comment.trim();
    }

    public static Review create(UUID clientId, UUID cinemaId, UUID roomId, UUID movieId,
            int roomRating, int movieRating, String comment) {
        return new Review(UUID.randomUUID(), clientId, cinemaId, roomId, movieId,
                Rating.of(roomRating), Rating.of(movieRating), comment);
    }
}
