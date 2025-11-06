package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.FindPromotionByIdOutputPort;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.mappers.PromotionsRepositoryMapper;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.repositories.PromotionsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPromotionByIdAdapter implements FindPromotionByIdOutputPort {

    private static final PromotionsRepositoryMapper MAPPER = PromotionsRepositoryMapper.INSTANCE;

    private final PromotionsRepository promotionsRepository;

    @Override
    public Optional<Promotion> findById(String id) {
        UUID promotionId;
        try {
            promotionId = UUID.fromString(id);
        } catch (IllegalArgumentException exception) {
            return Optional.empty();
        }

        return promotionsRepository.findById(promotionId)
                .map(MAPPER::toDomain);
    }
}
