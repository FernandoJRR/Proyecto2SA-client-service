package com.sa.client_service.reviews.domain;

import java.util.List;

import com.sa.client_service.reviews.infrastructure.persistenceadapter.projections.RoomCommentsStatsProjection;

import lombok.Value;

/**
 * Entidad de dominio que representa la información del reporte
 * de las salas más comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
@Value
public class TopCommentedRoomsReviews {

    /**
     * Estadísticas de las salas más comentadas, incluyendo el total de comentarios
     * por sala.
     */
    List<RoomCommentsStatsProjection> topStats;

    /**
     * Listado de comentarios asociados a las salas incluidas en el reporte.
     */
    List<Review> reviews;
}
