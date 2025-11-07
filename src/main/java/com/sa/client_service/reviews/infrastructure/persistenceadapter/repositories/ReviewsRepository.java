package com.sa.client_service.reviews.infrastructure.persistenceadapter.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.projections.RoomRatingStatsProjection;

public interface ReviewsRepository extends JpaRepository<ReviewEntity, UUID> {

  @Query("""
          SELECT r FROM ReviewEntity r
          WHERE (COALESCE(:roomId, r.roomId) = r.roomId)
            AND (
                  (CAST(:startDate AS date) IS NOT NULL AND CAST(:endDate AS date) IS NULL AND r.createdAt >= :startDate)
               OR (CAST(:startDate AS date) IS NULL AND CAST(:endDate AS date) IS NOT NULL AND r.createdAt <= :endDate)
               OR (CAST(:startDate AS date) IS NOT NULL AND CAST(:endDate AS date) IS NOT NULL AND r.createdAt BETWEEN :startDate AND :endDate)
               OR (CAST(:startDate AS date) IS NULL AND CAST(:endDate AS date) IS NULL)
            )
          ORDER BY r.createdAt DESC
      """)
  List<ReviewEntity> findByRoomIdAndDateRange(
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate,
      @Param("roomId") UUID roomId);

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

  @Query("""
          SELECT new com.sa.client_service.reviews.infrastructure.persistenceadapter.projections.RoomRatingStatsProjection(
              r.roomId,
              AVG(r.roomRating),
              COUNT(r.id)
          )
          FROM ReviewEntity r
          WHERE (
                (CAST(:startDate AS date) IS NOT NULL AND CAST(:endDate AS date) IS NULL AND r.createdAt >= :startDate)
             OR (CAST(:startDate AS date) IS NULL AND CAST(:endDate AS date) IS NOT NULL AND r.createdAt <= :endDate)
             OR (CAST(:startDate AS date) IS NOT NULL AND CAST(:endDate AS date) IS NOT NULL AND r.createdAt BETWEEN :startDate AND :endDate)
             OR (CAST(:startDate AS date) IS NULL AND CAST(:endDate AS date) IS NULL)
          )
            AND (COALESCE(:roomId, r.roomId) = r.roomId)
          GROUP BY r.roomId
          ORDER BY AVG(r.roomRating) DESC
      """)
  List<RoomRatingStatsProjection> findTopRoomsByAverageRating(
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate,
      @Param("roomId") UUID roomId,
      Pageable pageable);

  @Query("""
          SELECT r FROM ReviewEntity r
          WHERE r.roomId IN :roomIds
            AND (
                  (CAST(:startDate AS date) IS NOT NULL AND CAST(:endDate AS date) IS NULL AND r.createdAt >= :startDate)
               OR (CAST(:startDate AS date) IS NULL AND CAST(:endDate AS date) IS NOT NULL AND r.createdAt <= :endDate)
               OR (CAST(:startDate AS date) IS NOT NULL AND CAST(:endDate AS date) IS NOT NULL AND r.createdAt BETWEEN :startDate AND :endDate)
               OR (CAST(:startDate AS date) IS NULL AND CAST(:endDate AS date) IS NULL)
            )
          ORDER BY r.createdAt DESC
      """)
  List<ReviewEntity> findByRoomIdsAndDateRange(
      @Param("roomIds") List<UUID> roomIds,
      @Param("startDate") LocalDateTime startDate,
      @Param("endDate") LocalDateTime endDate);
}
