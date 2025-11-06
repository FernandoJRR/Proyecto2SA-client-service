package com.sa.client_service.promotions.infrastructure.persistenceadapter.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import com.sa.client_service.promotions.domain.EstablishmentTypeEnum;
import com.sa.client_service.promotions.domain.PromotionTargetType;
import com.sa.client_service.promotions.domain.PromotionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PromotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(name = "establishment_id", nullable = false)
    private UUID establishmentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "establishment_type", nullable = false, length = 30)
    private EstablishmentTypeEnum establishmentType;

    @Column(name = "top_count", nullable = false)
    private Integer topCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "promotion_type", nullable = false, length = 40)
    private PromotionType promotionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "promotion_target_type", length = 40)
    private PromotionTargetType promotionTargetType;
}
