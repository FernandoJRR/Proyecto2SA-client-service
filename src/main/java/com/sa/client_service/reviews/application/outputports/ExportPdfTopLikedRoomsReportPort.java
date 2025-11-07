package com.sa.client_service.reviews.application.outputports;

import java.time.LocalDate;
import java.util.List;

import com.sa.client_service.reviews.domain.Review;

/**
 * Puerto de salida encargado de la exportación del reporte de salas más
 * gustadas
 * a formato PDF.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
public interface ExportPdfTopLikedRoomsReportPort {

    /**
     * Exporta a formato PDF la información del reporte de salas más gustadas
     * dentro del rango de fechas especificado.
     * 
     * @param report    lista de objetos {@link Review} que representan las
     *                  calificaciones obtenidas
     * @param startDate fecha inicial del intervalo del reporte
     * @param endDate   fecha final del intervalo del reporte
     * @return un arreglo de bytes que representa el archivo PDF generado
     */
    byte[] exportPdf(List<Review> report, LocalDate startDate, LocalDate endDate);
}
