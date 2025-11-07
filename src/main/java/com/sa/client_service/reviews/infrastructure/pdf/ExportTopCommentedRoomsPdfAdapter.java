package com.sa.client_service.reviews.infrastructure.pdf;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.outputports.ExportPdfTopCommentedRoomsReportPort;
import com.sa.client_service.reviews.domain.TopCommentedRoomsReviews;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.CommentReportResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.RoomCommentsStatsResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.mappers.ReviewReportResponseMapper;
import com.sap.common_lib.util.DateUtils;
import com.sap.common_lib.util.PdfPrinter;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/**
 * Adaptador de infraestructura encargado de la exportación a PDF del reporte
 * de las salas más comentadas.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-07
 */
@RequiredArgsConstructor
@Component
public class ExportTopCommentedRoomsPdfAdapter implements ExportPdfTopCommentedRoomsReportPort {

    private final ReviewReportResponseMapper mapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] exportPdf(TopCommentedRoomsReviews report, LocalDate startDate, LocalDate endDate) {
        List<CommentReportResponse> rows = mapper.toReviewResponse(report.getReviews());
        List<RoomCommentsStatsResponse> stats = mapper.toRoomCommentsStatsResponses(report.getTopStats());

        JRBeanArrayDataSource table = new JRBeanArrayDataSource(rows.toArray());
        JRBeanArrayDataSource statsArrayDataSource = new JRBeanArrayDataSource(stats.toArray());

        Map<String, Object> params = new HashMap<>();
        params.put("table", table);
        params.put("stats", statsArrayDataSource);
        params.put("startDate", startDate != null ? DateUtils.format(startDate) : "-");
        params.put("endDate", endDate != null ? DateUtils.format(endDate) : "-");

        return new PdfPrinter("/reports/").exportPdf("MostCommentedRoomsReport", params);
    }
}
