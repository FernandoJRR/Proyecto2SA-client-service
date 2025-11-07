package com.sa.client_service.reviews.application.usecases;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.inputports.ExportCommentReportPort;
import com.sa.client_service.reviews.application.inputports.GetCinemaAdminCommentReportPort;
import com.sa.client_service.reviews.application.outputports.ExportPdfCommentReportPort;
import com.sa.client_service.reviews.domain.Review;

import lombok.RequiredArgsConstructor;

/**
 * Caso de uso encargado de exportar el reporte de comentarios de salas de cine
 * en formato PDF.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
@Component
@RequiredArgsConstructor
public class ExportCommentReportUseCase implements ExportCommentReportPort {

    private final GetCinemaAdminCommentReportPort getCinemaAdminCommentReportPort;
    private final ExportPdfCommentReportPort exportPdfPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] exportCinemaAdminCommentReport(LocalDate starDate, LocalDate enDate, UUID roomId) {
        List<Review> report = getCinemaAdminCommentReportPort.getCinemaAdminCommentReport(starDate, enDate, roomId);
        return exportPdfPort.exportPdf(report, starDate, enDate);
    }

}
