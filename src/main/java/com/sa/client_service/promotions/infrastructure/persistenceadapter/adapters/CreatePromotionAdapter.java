package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sa.client_service.promotions.application.outputports.CreatePromotionOutputPort;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.mappers.PromotionsRepositoryMapper;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.models.PromotionEntity;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.repositories.PromotionsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePromotionAdapter implements CreatePromotionOutputPort {

    private static final PromotionsRepositoryMapper MAPPER = PromotionsRepositoryMapper.INSTANCE;

    private final PromotionsRepository promotionsRepository;

    @Override
    @Transactional
    public Promotion createPromotion(Promotion promotion) {
        PromotionEntity toPersist = MAPPER.toEntity(promotion);
        PromotionEntity saved = promotionsRepository.save(toPersist);
        return MAPPER.toDomain(saved);
    }
}
