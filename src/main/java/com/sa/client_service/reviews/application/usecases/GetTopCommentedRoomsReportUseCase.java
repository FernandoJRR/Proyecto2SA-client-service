package com.sa.client_service.reviews.application.usecases;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.reviews.application.inputports.GetTopCommentedRoomsReportPort;
import com.sa.client_service.reviews.application.outputports.FindTopCommentedRoomsReviewsPort;
import com.sa.client_service.reviews.domain.TopCommentedRoomsReviews;

import lombok.RequiredArgsConstructor;

/**
 * Caso de uso encargado de obtener la información del reporte de las salas más
 * comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class GetTopCommentedRoomsReportUseCase implements GetTopCommentedRoomsReportPort {

    private final FindTopCommentedRoomsReviewsPort findTopCommentedRoomsReviewsPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public TopCommentedRoomsReviews getTopCommentedRoomsReport(
            LocalDate startDate,
            LocalDate endDate,
            UUID roomId,
            Integer limit) {
        return findTopCommentedRoomsReviewsPort.findTopCommentedRoomsReviews(startDate, endDate, roomId, limit);
    }
}
