package com.sa.client_service.promotions.application.inputports;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.sa.client_service.promotions.application.dtos.FindPromotionEligibilityDTO;
import com.sa.client_service.promotions.domain.Promotion;

import jakarta.validation.Valid;

public interface FindEligiblePromotionInputPort {
    public Promotion handle(@Valid FindPromotionEligibilityDTO dto) throws NotFoundException;
}
