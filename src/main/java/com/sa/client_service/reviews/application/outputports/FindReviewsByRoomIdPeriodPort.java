package com.sa.client_service.reviews.application.outputports;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.sa.client_service.reviews.domain.Review;

public interface FindReviewsByRoomIdPeriodPort {

    public List<Review> findReviewsByRoomIdPeriod(
            LocalDate starDate,
            LocalDate enDate,
            UUID roomId);
}
