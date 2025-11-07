package com.sa.client_service.reviews.infrastructure.pdf;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.outputports.ExportPdfTopLikedRoomsReportPort;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.LikedRoomReportResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.mappers.ReviewReportResponseMapper;
import com.sap.common_lib.util.DateUtils;
import com.sap.common_lib.util.PdfPrinter;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 * Adaptador encargado de la exportación del reporte de salas más gustadas en
 * formato PDF.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
@RequiredArgsConstructor
@Component
public class ExportTopLikedRoomsPdfAdapter implements ExportPdfTopLikedRoomsReportPort {

    private final ReviewReportResponseMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] exportPdf(List<Review> report, LocalDate startDate, LocalDate endDate) {
        // Convierte las entidades del dominio en objetos de respuesta para el reporte
        List<LikedRoomReportResponse> rows = mapper.toTopLikedRoomsReportResponse(report);

        // Fuente de datos para JasperReports
        JRBeanArrayDataSource table = new JRBeanArrayDataSource(rows.toArray());

        // Parámetros del reporte
        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("startDate", startDate != null ? DateUtils.format(startDate) : "-");
        params.put("endDate", endDate != null ? DateUtils.format(endDate) : "-");

        // Genera el archivo PDF final
        return new PdfPrinter("/reports/").exportPdf("TopLikedRoomsReport", params);
    }
}
