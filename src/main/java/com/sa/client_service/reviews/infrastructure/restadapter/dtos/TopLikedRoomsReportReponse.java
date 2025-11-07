package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import java.util.List;

import lombok.Value;

@Value
public class TopLikedRoomsReportReponse {

    private List<RoomRatingStatsResponse> ratingStats;
    private List<LikedRoomReportResponse> reviews;
}
