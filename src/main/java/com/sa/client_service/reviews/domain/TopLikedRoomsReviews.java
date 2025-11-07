package com.sa.client_service.reviews.domain;

import java.util.List;

import com.sa.client_service.reviews.infrastructure.persistenceadapter.projections.RoomRatingStatsProjection;

import lombok.Value;

@Value
public class TopLikedRoomsReviews {
    List<RoomRatingStatsProjection> topStats;
    List<Review> reviews;
}
