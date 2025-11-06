package com.sa.client_service.reviews.infrastructure.persistenceadapter.adapters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.reviews.application.outputports.CreateReviewOutputPort;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.mappers.ReviewsRepositoryMapper;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.repositories.ReviewsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateReviewAdapter implements CreateReviewOutputPort{

    private static final ReviewsRepositoryMapper MAPPER = ReviewsRepositoryMapper.INSTANCE;

    private final ReviewsRepository reviewsRepository;

    @Override
    @Transactional
    public Review createReview(Review review) {
        ReviewEntity toPersist = MAPPER.toEntity(review);
        ReviewEntity saved = reviewsRepository.save(toPersist);
        return MAPPER.toDomain(saved);
    }

}
