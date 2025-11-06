package com.sa.client_service.reviews.application.inputports;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.sa.client_service.reviews.domain.Review;

/**
 * Puerto de entrada encargado de obtener el reporte de comentarios de salas de
 * cine
 * administradas por un usuario administrador de cine.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-08-15
 */
public interface GetCinemaAdminCommentReportPort {

    /**
     * Obtiene el reporte de comentarios de salas de cine dentro del intervalo de
     * fechas especificado.
     *
     * @param starDate fecha de inicio del intervalo del reporte
     * @param enDate   fecha de finalización del intervalo del reporte
     * @param roomId   identificador único de la sala de cine a filtrar (opcional)
     * @return un objeto {@link Review} que contiene la información consolidada del
     *         reporte
     */
    public List<Review> getCinemaAdminCommentReport(
            LocalDate starDate,
            LocalDate enDate,
            UUID roomId);
}
