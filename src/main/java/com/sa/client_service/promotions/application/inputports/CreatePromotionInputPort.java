package com.sa.client_service.promotions.application.inputports;

import java.security.InvalidParameterException;

import com.sa.client_service.promotions.application.dtos.CreatePromotionDTO;
import com.sa.client_service.promotions.domain.Promotion;

import jakarta.validation.Valid;

public interface CreatePromotionInputPort {
    public Promotion handle(@Valid CreatePromotionDTO createPromotionDTO) throws InvalidParameterException;
}
