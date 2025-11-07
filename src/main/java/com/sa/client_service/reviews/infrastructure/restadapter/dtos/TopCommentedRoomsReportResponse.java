package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import java.util.List;

import lombok.Value;

@Value
public class TopCommentedRoomsReportResponse {

    private List<RoomCommentsStatsResponse> commentStats;
    private List<CommentReportResponse> reviews;
}

