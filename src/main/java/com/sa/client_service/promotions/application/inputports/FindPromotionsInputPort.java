package com.sa.client_service.promotions.application.inputports;

import java.util.List;

import com.sa.client_service.promotions.domain.Promotion;

public interface FindPromotionsInputPort {
    public List<Promotion> handle();
}
