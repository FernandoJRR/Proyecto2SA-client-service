package com.sa.client_service.promotions.application.outputports;

import com.sa.client_service.promotions.domain.Promotion;

public interface CreatePromotionOutputPort {
    public Promotion createPromotion(Promotion promotion);
}
