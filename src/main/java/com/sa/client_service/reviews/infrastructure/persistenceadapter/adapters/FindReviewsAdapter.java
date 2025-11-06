package com.sa.client_service.reviews.infrastructure.persistenceadapter.adapters;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.application.outputports.FindReviewsByRoomIdPeriodPort;
import com.sa.client_service.reviews.application.outputports.FindReviewsOutputPort;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.mappers.ReviewsRepositoryMapper;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.repositories.ReviewsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindReviewsAdapter implements FindReviewsOutputPort, FindReviewsByRoomIdPeriodPort {

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

}
