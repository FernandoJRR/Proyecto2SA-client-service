package com.sa.client_service.promotions.infrastructure.persistenceadapter.mappers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.infrastructure.persistenceadapter.models.PromotionEntity;

@Mapper
public interface PromotionsRepositoryMapper {

    PromotionsRepositoryMapper INSTANCE = Mappers.getMapper(PromotionsRepositoryMapper.class);

    PromotionEntity toEntity(Promotion promotion);

    default Promotion toDomain(PromotionEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Promotion(
                entity.getId(),
                entity.getPercentage(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getName(),
                entity.getEstablishmentId(),
                entity.getEstablishmentType(),
                entity.getTopCount(),
                entity.getPromotionType(),
                entity.getPromotionTargetType());
    }

    default List<Promotion> toDomain(List<PromotionEntity> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
