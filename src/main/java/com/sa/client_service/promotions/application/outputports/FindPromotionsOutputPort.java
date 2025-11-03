package com.sa.client_service.promotions.application.outputports;

import java.util.List;

import com.sa.client_service.promotions.domain.Promotion;

public interface FindPromotionsOutputPort {
    public List<Promotion> findAll();
}
