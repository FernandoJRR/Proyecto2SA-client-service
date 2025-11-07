package com.sa.client_service.reviews.application.outputports;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.client_service.reviews.domain.TopCommentedRoomsReviews;

/**
 * Puerto de salida encargado de obtener los datos necesarios
 * para construir el reporte de las salas más comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
public interface FindTopCommentedRoomsReviewsPort {

    /**
     * Recupera la información de las salas más comentadas dentro del intervalo de
     * fechas indicado.
     *
     * @param startDate fecha inicial del intervalo del reporte (inclusive)
     * @param endDate   fecha final del intervalo del reporte (inclusive)
     * @param roomId    identificador único de la sala a filtrar (opcional)
     * @param limit     número máximo de salas a incluir en el resultado (por
     *                  defecto 5)
     * @return objeto {@link TopCommentedRoomsReviews} con la información recopilada
     */
    TopCommentedRoomsReviews findTopCommentedRoomsReviews(
            LocalDate startDate,
            LocalDate endDate,
            UUID roomId,
            Integer limit);
}
