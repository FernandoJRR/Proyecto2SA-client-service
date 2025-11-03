package com.sa.client_service.promotions.application.inputports;

import java.util.List;

import com.sa.client_service.promotions.domain.PromotionType.PromotionTypeInfo;

public interface FindAllPromotionTypesInputPort {
    public List<PromotionTypeInfo> handle();
}
