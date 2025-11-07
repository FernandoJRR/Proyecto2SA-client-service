package com.sa.client_service.reviews.infrastructure.persistenceadapter.adapters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.application.outputports.FindReviewsByRoomIdPeriodPort;
import com.sa.client_service.reviews.application.outputports.FindReviewsOutputPort;
import com.sa.client_service.reviews.application.outputports.FindTopLikedRoomsReviewsPort;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.domain.TopLikedRoomsReviews;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.mappers.ReviewsRepositoryMapper;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.projections.RoomRatingStatsProjection;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.repositories.ReviewsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindReviewsAdapter implements FindReviewsOutputPort, FindReviewsByRoomIdPeriodPort,
        FindTopLikedRoomsReviewsPort {

    private static final ReviewsRepositoryMapper MAPPER = ReviewsRepositoryMapper.INSTANCE;

    private final ReviewsRepository reviewsRepository;

    @Override
    public List<Review> findReviews(FindReviewsDTO dto) {
        List<ReviewEntity> entities = reviewsRepository.findByFilters(
                dto.getCinemaId(),
                dto.getRoomId(),
                dto.getMovieId(),
                dto.getClientId());
        return MAPPER.toDomain(entities);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<Review> findReviewsByRoomIdPeriod(LocalDate starDate, LocalDate enDate, UUID roomId) {

        LocalDateTime startDateTime = starDate != null ? starDate.atStartOfDay() : null;
        LocalDateTime endDateTime = enDate != null ? enDate.atTime(LocalTime.MAX) : null;

        return MAPPER.toDomain(reviewsRepository.findByRoomIdAndDateRange(startDateTime, endDateTime, roomId));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public TopLikedRoomsReviews findTopLikedRoomsReviews(LocalDate startDate, LocalDate endDate, UUID roomId,
            Integer limit) {

        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;

        // Obtener las métricas del top de salas
        List<RoomRatingStatsProjection> topStats = reviewsRepository.findTopRoomsByAverageRating(
                startDateTime, endDateTime, roomId, PageRequest.of(0, limit));

        if (topStats.isEmpty()) {
            return new TopLikedRoomsReviews(List.of(), List.of());
        }

        // Extraer solo los IDs de las salas
        List<UUID> topRoomIds = topStats.stream()
                .map(RoomRatingStatsProjection::roomId)
                .toList();

        // Obtener todas las reseñas de esas salas dentro del periodo
        List<ReviewEntity> entities = reviewsRepository.findByRoomIdsAndDateRange(topRoomIds, startDateTime,
                endDateTime);

        return new TopLikedRoomsReviews(topStats, MAPPER.toDomain(entities));
    }

}
