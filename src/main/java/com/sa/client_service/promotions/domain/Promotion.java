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
    private UUID id;
    private BigDecimal percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private UUID establishmentId;
    private EstablishmentTypeEnum establishmentType;
    private Integer topCount;
    private PromotionType promotionType;
    private PromotionTargetType promotionTargetType;

    public static Promotion create(BigDecimal percentage, LocalDate startDate, LocalDate endDate,
            String name, UUID establishmentId, EstablishmentTypeEnum establishmentType, Integer topCount,
            PromotionType promotionType, PromotionTargetType promotionTargetType) {
        return new Promotion(UUID.randomUUID(), percentage, startDate, endDate,
                name, establishmentId, establishmentType, topCount, promotionType, promotionTargetType);
    }
}
