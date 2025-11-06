package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.FindPromotionsByDateAndHotelOutputPort;
import com.sa.client_service.promotions.domain.EstablishmentTypeEnum;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.mappers.PromotionsRepositoryMapper;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.repositories.PromotionsRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPromotionsByDateAndHotelAdapter implements FindPromotionsByDateAndHotelOutputPort {

    private static final PromotionsRepositoryMapper MAPPER = PromotionsRepositoryMapper.INSTANCE;

    private final PromotionsRepository promotionsRepository;

    @Override
    public List<Promotion> findByStartDateAndHotel(LocalDate startDate, String hotelId) {
        UUID establishmentId = UUID.fromString(hotelId);
        return MAPPER.toDomain(
                promotionsRepository.findActiveByDateAndEstablishment(
                        startDate,
                        establishmentId,
                        EstablishmentTypeEnum.HOTEL));
    }
}
