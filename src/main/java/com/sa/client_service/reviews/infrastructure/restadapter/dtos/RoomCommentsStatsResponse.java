package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import lombok.Data;

/**
 * DTO de salida que representa las estadísticas de comentarios por sala.
 *
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
@Data
public class RoomCommentsStatsResponse {

    /**
     * Nombre de la sala de cine.
     */
    String roomName;

    /**
     * Cantidad total de comentarios realizados sobre la sala.
     */
    Long reviewCount;
}
