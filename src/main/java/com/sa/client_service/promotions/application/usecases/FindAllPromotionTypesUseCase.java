package com.sa.client_service.promotions.application.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.inputports.FindAllPromotionTypesInputPort;
import com.sa.client_service.promotions.domain.PromotionType;
import com.sa.client_service.promotions.domain.PromotionType.PromotionTypeInfo;

@Component
public class FindAllPromotionTypesUseCase implements FindAllPromotionTypesInputPort {

    @Override
    public List<PromotionTypeInfo> handle() {
        return PromotionType.getAll();
    }

}
