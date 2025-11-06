package com.sa.client_service.reviews.application.usecases;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.reviews.application.inputports.GetCinemaAdminCommentReportPort;
import com.sa.client_service.reviews.application.outputports.FindReviewsByRoomIdPeriodPort;
import com.sa.client_service.reviews.domain.Review;

import lombok.RequiredArgsConstructor;

/**
 * Caso de uso encargado de generar el reporte de comentarios realizados sobre
 * salas de cine administradas por un usuario administrador de cine.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-08-15
 */
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GetCinemaAdminCommentReportUseCase implements GetCinemaAdminCommentReportPort {

    private final FindReviewsByRoomIdPeriodPort byRoomIdPeriodPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Review> getCinemaAdminCommentReport(LocalDate starDate, LocalDate enDate, UUID roomId) {
        return byRoomIdPeriodPort.findReviewsByRoomIdPeriod(starDate, enDate, roomId);
    }
}
