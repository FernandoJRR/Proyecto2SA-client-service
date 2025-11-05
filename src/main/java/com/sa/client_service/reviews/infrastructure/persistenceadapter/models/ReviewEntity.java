package com.sa.client_service.reviews.infrastructure.persistenceadapter.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "client_id", nullable = false, updatable = false)
    private UUID clientId;

    @Column(name = "cinema_id", nullable = false, updatable = false)
    private UUID cinemaId;

    @Column(name = "room_id", nullable = false, updatable = false)
    private UUID roomId;

    @Column(name = "movie_id", nullable = false, updatable = false)
    private UUID movieId;

    @Column(name = "room_rating", nullable = false)
    private Integer roomRating;

    @Column(name = "movie_rating", nullable = false)
    private Integer movieRating;

    @Column(name = "comment", length = 500)
    private String comment;
}
