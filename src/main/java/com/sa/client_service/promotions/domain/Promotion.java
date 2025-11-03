package com.sa.client_service.promotions.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
    private BigDecimal percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private UUID cinemaId;
    private Integer topCount;
    private PromotionType promotionType;
    private PromotionTargetType promotionTargetType;

    public Promotion(UUID id, BigDecimal percentage, LocalDate startDate, LocalDate endDate,
            String name, UUID establishmentId, Integer topCount, PromotionType promotionType,
            PromotionTargetType promotionTargetType) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
        this.promotionType = promotionType;
        this.name = name;
        this.cinemaId = establishmentId;
        this.topCount = topCount;
    }

    public static Promotion create(BigDecimal percentage, LocalDate startDate, LocalDate endDate,
            String name, UUID establishmentId, Integer topCount, PromotionType promotionType,
            PromotionTargetType promotionTargetType) {
        return new Promotion(UUID.randomUUID(), percentage, startDate, endDate,
        name, establishmentId, topCount, promotionType, promotionTargetType);
    }
}
