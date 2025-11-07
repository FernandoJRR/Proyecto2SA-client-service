package com.sa.client_service.reviews.application.usecases;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.inputports.ExportTopCommentedRoomsReportPort;
import com.sa.client_service.reviews.application.inputports.GetTopCommentedRoomsReportPort;
import com.sa.client_service.reviews.application.outputports.ExportPdfTopCommentedRoomsReportPort;
import com.sa.client_service.reviews.domain.TopCommentedRoomsReviews;

import lombok.RequiredArgsConstructor;

/**
 * Caso de uso encargado de la exportación del reporte de las salas más
 * comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
@Component
@RequiredArgsConstructor
public class ExportTopCommentedRoomsReportUseCase implements ExportTopCommentedRoomsReportPort {

    private final GetTopCommentedRoomsReportPort getTopCommentedRoomsReportPort;
    private final ExportPdfTopCommentedRoomsReportPort exportPdfPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] exportTopCommentedRoomsReport(LocalDate startDate, LocalDate endDate, UUID roomId, Integer limit) {
        TopCommentedRoomsReviews report = getTopCommentedRoomsReportPort.getTopCommentedRoomsReport(startDate, endDate,
                roomId, limit);
        return exportPdfPort.exportPdf(report, startDate, endDate);
    }
}
