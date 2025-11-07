package com.sa.client_service.reviews.application.outputports;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.domain.TopLikedRoomsReviews;

/**
 * Puerto de salida encargado de la obtención de reseñas y calificaciones
 * asociadas a las salas de cine más gustadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
public interface FindTopLikedRoomsReviewsPort {

    /**
     * Recupera las reseñas con mejores calificaciones dentro del rango de fechas
     * especificado.
     * 
     * @param startDate fecha inicial del intervalo de búsqueda
     * @param endDate   fecha final del intervalo de búsqueda
     * @param roomId    identificador opcional de la sala a filtrar (puede ser
     *                  {@code null})
     * @param limit     número máximo de resultados a devolver
     * @return una lista de objetos {@link Review} que representan las reseñas más
     *         destacadas
     */
    TopLikedRoomsReviews findTopLikedRoomsReviews(LocalDate startDate, LocalDate endDate, UUID roomId, Integer limit);
}
