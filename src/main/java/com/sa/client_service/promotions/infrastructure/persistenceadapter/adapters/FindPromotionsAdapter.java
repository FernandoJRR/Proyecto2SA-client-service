package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.FindPromotionsOutputPort;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.mappers.PromotionsRepositoryMapper;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.repositories.PromotionsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPromotionsAdapter implements FindPromotionsOutputPort {

    private static final PromotionsRepositoryMapper MAPPER = PromotionsRepositoryMapper.INSTANCE;

    private final PromotionsRepository promotionsRepository;

    @Override
    public List<Promotion> findAll() {
        return MAPPER.toDomain(promotionsRepository.findAll());
    }
}
