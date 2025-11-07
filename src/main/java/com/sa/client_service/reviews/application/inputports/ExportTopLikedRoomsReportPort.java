package com.sa.client_service.reviews.application.inputports;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Puerto de entrada encargado de la exportación del reporte de salas más
 * gustadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
public interface ExportTopLikedRoomsReportPort {

    /**
     * Exporta el reporte de las salas de cine más gustadas dentro de un rango de
     * fechas.
     * 
     * @param startDate fecha inicial del intervalo a evaluar
     * @param endDate   fecha final del intervalo a evaluar
     * @param roomId    identificador opcional de la sala a filtrar (puede ser
     *                  {@code null})
     * @param limit     número máximo de resultados a incluir en el reporte
     * @return un arreglo de bytes que representa el archivo PDF generado
     */
    byte[] exportTopLikedRoomsReport(LocalDate startDate, LocalDate endDate, UUID roomId, Integer limit);
}
