package com.sa.client_service.reviews.application.inputports;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Puerto de entrada encargado de exportar el reporte de las salas más
 * comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
public interface ExportTopCommentedRoomsReportPort {

    /**
     * Exporta el reporte de salas más comentadas dentro del intervalo de fechas
     * indicado.
     *
     * @param startDate fecha inicial del intervalo del reporte (inclusive)
     * @param endDate   fecha final del intervalo del reporte (inclusive)
     * @param roomId    identificador único de la sala a filtrar (opcional)
     * @param limit     número máximo de salas a incluir en el reporte (por defecto
     *                  5)
     * @return arreglo de bytes que representa el archivo PDF generado
     */
    byte[] exportTopCommentedRoomsReport(
            LocalDate startDate,
            LocalDate endDate,
            UUID roomId,
            Integer limit);
}
