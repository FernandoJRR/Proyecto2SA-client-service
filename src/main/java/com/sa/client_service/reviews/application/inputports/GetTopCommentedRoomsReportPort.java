package com.sa.client_service.reviews.application.inputports;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.client_service.reviews.domain.TopCommentedRoomsReviews;

/**
 * Puerto de entrada encargado de obtener la información necesaria
 * para generar el reporte de las salas más comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
public interface GetTopCommentedRoomsReportPort {

    /**
     * Obtiene los datos del reporte de salas más comentadas dentro del intervalo de
     * fechas indicado.
     *
     * @param startDate fecha inicial del intervalo del reporte (inclusive)
     * @param endDate   fecha final del intervalo del reporte (inclusive)
     * @param roomId    identificador único de la sala a filtrar (opcional)
     * @param limit     número máximo de salas a incluir en el reporte (por defecto
     *                  5)
     * @return objeto {@link TopCommentedRoomsReviews} que contiene la información
     *         del reporte
     */
    TopCommentedRoomsReviews getTopCommentedRoomsReport(
            LocalDate startDate,
            LocalDate endDate,
            UUID roomId,
            Integer limit);
}
