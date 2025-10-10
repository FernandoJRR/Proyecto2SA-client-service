package com.sa.client_service.promotions.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Promotion {
    private BigDecimal percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String name;
    private UUID cinemaId;
    private Integer topCount;
    private PromotionType promotionType;
}
