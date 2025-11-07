package com.sa.client_service.reviews.application.outputports;

import java.time.LocalDate;
import java.util.List;

import com.sa.client_service.reviews.domain.Review;

/**
 * Puerto de salida encargado de la exportación del reporte de comentarios
 * de salas de cine en formato PDF.
 *
 * @author
 * @version 1.0
 * @since 2025-11-06
 */
public interface ExportPdfCommentReportPort {

    /**
     * Genera un reporte PDF que consolida los comentarios y calificaciones
     * registrados por los usuarios.
     *
     * @param report lista de entidades {@link Review} que contienen la información
     *               de las reseñas a incluir en el reporte.
     * @return un arreglo de bytes representando el archivo PDF generado.
     */
    byte[] exportPdf(List<Review> report, LocalDate starDate,
            LocalDate enDate);
}
