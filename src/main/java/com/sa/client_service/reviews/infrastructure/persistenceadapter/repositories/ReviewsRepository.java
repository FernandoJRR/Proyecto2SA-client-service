package com.sa.client_service.reviews.infrastructure.persistenceadapter.repositories;

import java.util.UUID;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;

public interface ReviewsRepository extends JpaRepository<ReviewEntity, UUID> {

    @Query("""
            SELECT r FROM ReviewEntity r
            WHERE (:cinemaId IS NULL OR r.cinemaId = :cinemaId)
              AND (:roomId IS NULL OR r.roomId = :roomId)
              AND (:movieId IS NULL OR r.movieId = :movieId)
              AND (:clientId IS NULL OR r.clientId = :clientId)
            """)
    List<ReviewEntity> findByFilters(
            @Param("cinemaId") UUID cinemaId,
            @Param("roomId") UUID roomId,
            @Param("movieId") UUID movieId,
            @Param("clientId") UUID clientId);
}
