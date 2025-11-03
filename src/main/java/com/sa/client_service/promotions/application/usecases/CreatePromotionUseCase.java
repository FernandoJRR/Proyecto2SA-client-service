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

        PromotionTargetType promotionTargetType;
        //There only has to be promotion target if the promotion type is client
        if (promotionType.equals(PromotionType.CLIENT_MOST_FREQUENT)
            && createPromotionDTO.getPromotionTargetType() != null) {
            try {
                promotionTargetType = PromotionTargetType.valueOf(createPromotionDTO.getPromotionTargetType());
            } catch (IllegalArgumentException e) {
                throw new InvalidParameterException("El tipo de promocion ingresada no existe");
            }
        } else {
            throw new InvalidParameterException("El objetivo de la promocion solo es aplicable para promociones de clientes");
        }

        System.out.println("AQUIIII");
        Promotion createdPromotion = Promotion.create(
            createPromotionDTO.getPercentage(),
            createPromotionDTO.getStartDate(),
            createPromotionDTO.getEndDate(),
            createPromotionDTO.getName(),
            UUID.fromString(createPromotionDTO.getEstablishmentId()),
            createPromotionDTO.getTopCount(),
            promotionType,
            promotionTargetType);
        System.out.println("PAASAAAA");

        return createPromotionOutputPort.createPromotion(createdPromotion);
    }

}
