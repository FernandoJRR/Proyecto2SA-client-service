package com.sa.client_service.reviews.infrastructure.persistenceadapter.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.application.outputports.FindReviewsOutputPort;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.mappers.ReviewsRepositoryMapper;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.repositories.ReviewsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindReviewsAdapter implements FindReviewsOutputPort {

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

}
