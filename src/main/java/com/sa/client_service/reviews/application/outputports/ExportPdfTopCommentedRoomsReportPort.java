package com.sa.client_service.reviews.application.outputports;

import java.time.LocalDate;

import com.sa.client_service.reviews.domain.TopCommentedRoomsReviews;

/**
 * Puerto de salida encargado de la exportación del reporte de las salas más
 * comentadas en formato PDF.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
public interface ExportPdfTopCommentedRoomsReportPort {

    /**
     * Exporta a PDF el reporte de salas más comentadas dentro del intervalo de
     * fechas indicado.
     *
     * @param report    objeto {@link TopCommentedRoomsReviews} que contiene la
     *                  información del reporte
     * @param startDate fecha inicial del intervalo del reporte (inclusive)
     * @param endDate   fecha final del intervalo del reporte (inclusive)
     * @return arreglo de bytes que representa el archivo PDF generado
     */
    byte[] exportPdf(TopCommentedRoomsReviews report, LocalDate startDate, LocalDate endDate);
}
