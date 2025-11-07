package com.sa.client_service.reviews.infrastructure.pdf;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sa.client_service.reviews.application.outputports.ExportPdfCommentReportPort;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.CommentReportResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.mappers.ReviewReportResponseMapper;
import com.sap.common_lib.util.DateUtils;
import com.sap.common_lib.util.PdfPrinter;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

@RequiredArgsConstructor
@Component
public class ExportPdfAdapter implements ExportPdfCommentReportPort {

    private final ReviewReportResponseMapper mapper;

    @Override
    public byte[] exportPdf(List<Review> report, LocalDate starDate,
            LocalDate enDate) {

        List<CommentReportResponse> responsesReport = mapper.toReviewResponse(report);

        // convierte la lista a un JRBeanArrayDataSource para
        JRBeanArrayDataSource table = new JRBeanArrayDataSource(
                responsesReport.toArray());

        // crear el mapa de params para el reporte
        Map<String, Object> params = new HashMap<>();

        params.put("table", table);

        params.put("startDate", starDate != null ? DateUtils.format(starDate) : "-");
        params.put("endDate", enDate != null ? DateUtils.format(enDate) : "-");

        return new PdfPrinter("/reports/").exportPdf("CommentsReport", params);
    }

}
