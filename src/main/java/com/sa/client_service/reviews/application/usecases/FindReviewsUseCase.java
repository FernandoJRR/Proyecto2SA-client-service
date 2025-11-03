package com.sa.client_service.reviews.application.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.application.inputports.FindReviewsInputPort;
import com.sa.client_service.reviews.application.outputports.FindReviewsOutputPort;
import com.sa.client_service.reviews.domain.Review;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindReviewsUseCase implements FindReviewsInputPort {

    private final FindReviewsOutputPort findReviewsOutputPort;

    @Override
    public List<Review> handle(FindReviewsDTO dto) {
        return findReviewsOutputPort.findReviews(dto);
    }

}
