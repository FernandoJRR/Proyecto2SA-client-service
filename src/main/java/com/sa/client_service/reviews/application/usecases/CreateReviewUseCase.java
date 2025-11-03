package com.sa.client_service.reviews.application.usecases;

import com.sa.client_service.reviews.application.dtos.CreateReviewDTO;
import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.application.inputports.CreateReviewInputPort;
import com.sa.client_service.reviews.application.outputports.CreateReviewOutputPort;
import com.sa.client_service.reviews.application.outputports.FindReviewsOutputPort;
import com.sa.client_service.reviews.domain.Review;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.security.InvalidParameterException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Validated
public class CreateReviewUseCase implements CreateReviewInputPort {

    private final CreateReviewOutputPort createReviewOutputPort;
    private final FindReviewsOutputPort findReviewsOutputPort;

    @Override
    public Review handle(@Valid CreateReviewDTO createReviewDTO) throws InvalidParameterException {
        validateRating(createReviewDTO.getRoomRating());
        validateRating(createReviewDTO.getMovieRating());

        List<Review> existingReviews = findReviewsOutputPort.findReviews(FindReviewsDTO.builder()
                .clientId(createReviewDTO.getClientId())
                .cinemaId(createReviewDTO.getCinemaId())
                .roomId(createReviewDTO.getRoomId())
                .movieId(createReviewDTO.getMovieId())
                .build());

        if (!existingReviews.isEmpty()) {
            throw new InvalidParameterException("Ya existe una reseña para este cliente y recurso");
        }

        Review createdReview = Review.create(
                createReviewDTO.getClientId(),
                createReviewDTO.getCinemaId(),
                createReviewDTO.getRoomId(),
                createReviewDTO.getMovieId(),
                createReviewDTO.getRoomRating(),
                createReviewDTO.getMovieRating(),
                createReviewDTO.getComment());

        return createReviewOutputPort.createReview(createdReview);
    }

    private void validateRating(int value) {
        if (value < 1 || value > 5) {
            throw new InvalidParameterException("El rating debe estar entre 1 y 5");
        }
    }
}
