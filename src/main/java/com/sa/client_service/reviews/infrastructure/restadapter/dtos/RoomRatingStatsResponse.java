package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import lombok.Data;

@Data
public class RoomRatingStatsResponse {
    String roomName;
    Double averageRating;
    Long reviewCount;
}
