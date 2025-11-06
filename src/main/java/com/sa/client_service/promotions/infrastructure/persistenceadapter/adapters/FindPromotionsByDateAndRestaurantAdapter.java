package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.FindPromotionsByDateAndRestaurantOutputPort;
import com.sa.client_service.promotions.domain.EstablishmentTypeEnum;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.mappers.PromotionsRepositoryMapper;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.repositories.PromotionsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPromotionsByDateAndRestaurantAdapter
        implements FindPromotionsByDateAndRestaurantOutputPort {

    private static final PromotionsRepositoryMapper MAPPER = PromotionsRepositoryMapper.INSTANCE;

    private final PromotionsRepository promotionsRepository;

    @Override
    public List<Promotion> findByStartDateAndRestaurant(LocalDate startDate, String restaurantId) {
        UUID establishmentId = UUID.fromString(restaurantId);
        return MAPPER.toDomain(
                promotionsRepository.findActiveByDateAndEstablishment(
                        startDate,
                        establishmentId,
                        EstablishmentTypeEnum.RESTAURANT));
    }
}
