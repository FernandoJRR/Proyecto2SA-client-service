package com.sa.client_service.reviews.application.usecases;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.inputports.ExportTopLikedRoomsReportPort;
import com.sa.client_service.reviews.application.inputports.GetTopLikedRoomsReportPort;
import com.sa.client_service.reviews.application.outputports.ExportPdfTopLikedRoomsReportPort;
import com.sa.client_service.reviews.domain.TopLikedRoomsReviews;

import lombok.RequiredArgsConstructor;

/**
 * Caso de uso encargado de la exportación del reporte de salas más gustadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
@Component
@RequiredArgsConstructor
public class ExportTopLikedRoomsReportUseCase implements ExportTopLikedRoomsReportPort {

    private final GetTopLikedRoomsReportPort getTopLikedRoomsReportPort;
    private final ExportPdfTopLikedRoomsReportPort exportPdfPort;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] exportTopLikedRoomsReport(LocalDate startDate, LocalDate endDate, UUID roomId, Integer limit) {
        // Obtiene las reseñas y calificaciones de las salas más gustadas
        TopLikedRoomsReviews topLikedRoomsReviews = getTopLikedRoomsReportPort.getTopLikedRoomsReport(startDate,
                endDate, roomId, limit);

        // Exporta la información obtenida a un archivo PDF
        return exportPdfPort.exportPdf(topLikedRoomsReviews, startDate, endDate);
    }
}
