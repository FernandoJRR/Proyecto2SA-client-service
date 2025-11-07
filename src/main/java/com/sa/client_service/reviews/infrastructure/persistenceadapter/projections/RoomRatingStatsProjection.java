package com.sa.client_service.reviews.infrastructure.persistenceadapter.projections;

import java.util.UUID;

/**
 * Proyección que representa las métricas de una sala en el top de salas más
 * gustadas.
 *
 * Incluye el identificador de la sala, su promedio de calificación y la
 * cantidad de reseñas.
 */
public record RoomRatingStatsProjection(
        UUID roomId,
        Double averageRating,
        Long reviewCount) {
}