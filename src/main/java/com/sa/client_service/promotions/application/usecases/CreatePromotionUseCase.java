package com.sa.client_service.promotions.application.usecases;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.sa.client_service.promotions.application.dtos.CreatePromotionDTO;
import com.sa.client_service.promotions.application.inputports.CreatePromotionInputPort;
import com.sa.client_service.promotions.application.outputports.CreatePromotionOutputPort;
import com.sa.client_service.promotions.domain.EstablishmentTypeEnum;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.domain.PromotionTargetType;
import com.sa.client_service.promotions.domain.PromotionType;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Component
@Validated
@RequiredArgsConstructor
public class CreatePromotionUseCase implements CreatePromotionInputPort {

    private final CreatePromotionOutputPort createPromotionOutputPort;

    @Override
    @Transactional
    public Promotion handle(@Valid CreatePromotionDTO createPromotionDTO) throws InvalidParameterException {
        if (createPromotionDTO.getStartDate().isAfter(createPromotionDTO.getEndDate())) {
            throw new InvalidParameterException("Start date cannot be after end date.");
        }

        PromotionType promotionType;
        try {
            promotionType = PromotionType.valueOf(createPromotionDTO.getPromotionType());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException("El tipo de promocion ingresada no existe");
        }

        PromotionTargetType promotionTargetType = null;
        if (promotionType.equals(PromotionType.CLIENT_MOST_FREQUENT)) {
            if (createPromotionDTO.getPromotionTargetType() == null) {
                throw new InvalidParameterException("El objetivo de la promocion es obligatorio para promociones de clientes");
            }
            try {
                promotionTargetType = PromotionTargetType.valueOf(createPromotionDTO.getPromotionTargetType());
            } catch (IllegalArgumentException e) {
                throw new InvalidParameterException("El objetivo de promocion ingresado no existe");
            }
        }

        EstablishmentTypeEnum establishmentType;
        try {
            establishmentType = EstablishmentTypeEnum.valueOf(createPromotionDTO.getEstablishmentType());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException("El tipo de establecimiento ingresado no existe");
        }

        Promotion createdPromotion = Promotion.create(
            createPromotionDTO.getPercentage(),
            createPromotionDTO.getStartDate(),
            createPromotionDTO.getEndDate(),
            createPromotionDTO.getName(),
            UUID.fromString(createPromotionDTO.getEstablishmentId()),
            establishmentType,
            createPromotionDTO.getTopCount(),
            promotionType,
            promotionTargetType);

        return createPromotionOutputPort.createPromotion(createdPromotion);
    }

}
