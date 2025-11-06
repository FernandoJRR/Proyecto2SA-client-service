package com.sa.client_service.promotions.infrastructure.persistenceadapter.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sa.client_service.promotions.domain.EstablishmentTypeEnum;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.models.PromotionEntity;

public interface PromotionsRepository extends JpaRepository<PromotionEntity, UUID> {

    @Query("""
            SELECT p FROM PromotionEntity p
            WHERE p.establishmentId = :establishmentId
              AND p.establishmentType = :establishmentType
              AND p.startDate <= :date
              AND p.endDate >= :date
            """)
    List<PromotionEntity> findActiveByDateAndEstablishment(
            @Param("date") LocalDate date,
            @Param("establishmentId") UUID establishmentId,
            @Param("establishmentType") EstablishmentTypeEnum establishmentType);
}
