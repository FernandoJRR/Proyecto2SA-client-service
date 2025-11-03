package com.sa.client_service.reviews.infrastructure.restadapter.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sa.client_service.reviews.application.dtos.CreateReviewDTO;
import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.CreateReviewRequest;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.FindReviewsRequest;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.ReviewResponse;

@Mapper
public interface ReviewsRestMapper {

    ReviewsRestMapper INSTANCE = Mappers.getMapper(ReviewsRestMapper.class);

    CreateReviewDTO toCreateReviewDTO(CreateReviewRequest request);

    FindReviewsDTO toFindReviewsDTO(FindReviewsRequest request);

    @Mapping(target = "roomRating", source = "roomRating.value")
    @Mapping(target = "movieRating", source = "movieRating.value")
    ReviewResponse toReviewResponse(Review review);

    List<ReviewResponse> toReviewResponses(List<Review> reviews);
}
