package com.sa.client_service.reviews.infrastructure.persistenceadapter.mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sa.client_service.reviews.domain.Rating;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.models.ReviewEntity;

@Mapper
public interface ReviewsRepositoryMapper {

    ReviewsRepositoryMapper INSTANCE = Mappers.getMapper(ReviewsRepositoryMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "roomRating", source = "roomRating.value")
    @Mapping(target = "movieRating", source = "movieRating.value")
    ReviewEntity toEntity(Review review);

    default Review toDomain(ReviewEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Review(
                entity.getId(),
                entity.getClientId(),
                entity.getCinemaId(),
                entity.getRoomId(),
                entity.getMovieId(),
                Rating.of(entity.getRoomRating()),
                Rating.of(entity.getMovieRating()),
                entity.getComment(),
                entity.getCreatedAt());
    }

    default List<Review> toDomain(List<ReviewEntity> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
