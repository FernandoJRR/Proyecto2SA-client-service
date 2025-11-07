package com.sa.client_service.reviews.application.inputports;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.client_service.reviews.domain.Review;

public interface ExportCommentReportPort {

    /**
     * Exporta a PDF el reporte de comentarios de salas de cine dentro del intervalo
     * de
     * fechas especificado.
     *
     * @param starDate fecha de inicio del intervalo del reporte
     * @param enDate   fecha de finalización del intervalo del reporte
     * @param roomId   identificador único de la sala de cine a filtrar (opcional)
     * @return un objeto {@link Review} que contiene la información consolidada del
     *         reporte
     */
    public byte[] exportCinemaAdminCommentReport(
            LocalDate starDate,
            LocalDate enDate,
            UUID roomId);
}
