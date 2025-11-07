package com.sa.client_service.reviews.infrastructure.persistenceadapter.projections;

import java.util.UUID;

/**
 * Proyección que representa las estadísticas de comentarios agrupadas por sala.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
public record RoomCommentsStatsProjection(
                UUID roomId,
                Long reviewCount) {
}
