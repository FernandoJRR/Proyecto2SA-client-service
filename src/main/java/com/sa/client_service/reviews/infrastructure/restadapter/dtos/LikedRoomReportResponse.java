package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO utilizado para representar los datos del reporte de salas más gustadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikedRoomReportResponse {

    private String roomName;

    private String clientEmail;

    private Integer rating;

    private String createdAt;
}
