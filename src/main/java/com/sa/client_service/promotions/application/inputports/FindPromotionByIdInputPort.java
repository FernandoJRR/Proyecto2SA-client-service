package com.sa.client_service.promotions.application.inputports;

import java.util.UUID;

import com.sa.client_service.promotions.domain.Promotion;

import jakarta.ws.rs.NotFoundException;

public interface FindPromotionByIdInputPort {
    public Promotion handle(UUID id) throws NotFoundException;
}
