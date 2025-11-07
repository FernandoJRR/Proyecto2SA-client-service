package com.sa.client_service.reviews.application.usecases;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.reviews.application.inputports.GetTopLikedRoomsReportPort;
import com.sa.client_service.reviews.application.outputports.FindTopLikedRoomsReviewsPort;
import com.sa.client_service.reviews.domain.TopLikedRoomsReviews;

import lombok.RequiredArgsConstructor;

/**
 * Caso de uso encargado de obtener la información del reporte de salas más
 * gustadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Component
public class GetTopLikedRoomsReportUseCase implements GetTopLikedRoomsReportPort {

    private final FindTopLikedRoomsReviewsPort findTopLikedRoomsReviewsPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public TopLikedRoomsReviews getTopLikedRoomsReport(LocalDate startDate, LocalDate endDate, UUID roomId,
            Integer limit) {
        return findTopLikedRoomsReviewsPort.findTopLikedRoomsReviews(startDate, endDate, roomId, limit);
    }
}
