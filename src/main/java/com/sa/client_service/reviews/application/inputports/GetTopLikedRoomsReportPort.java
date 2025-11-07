package com.sa.client_service.reviews.application.inputports;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.domain.TopLikedRoomsReviews;

/**
 * Puerto de entrada encargado de obtener la información necesaria para el
 * reporte de salas más gustadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
public interface GetTopLikedRoomsReportPort {

    /**
     * Obtiene la lista de reseñas y calificaciones correspondientes a las salas
     * más gustadas dentro del intervalo de fechas especificado.
     * 
     * @param startDate fecha inicial del intervalo de búsqueda
     * @param endDate   fecha final del intervalo de búsqueda
     * @param roomId    identificador opcional de la sala a filtrar (puede ser
     *                  {@code null})
     * @param limit     número máximo de resultados a devolver
     * @return una lista de objetos {@link Review} con la información de las salas
     *         más gustadas
     */
    TopLikedRoomsReviews getTopLikedRoomsReport(LocalDate startDate, LocalDate endDate, UUID roomId, Integer limit);
}
