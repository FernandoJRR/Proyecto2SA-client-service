package com.sa.client_service.promotions.application.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.inputports.FindPromotionsInputPort;
import com.sa.client_service.promotions.application.outputports.FindPromotionsOutputPort;
import com.sa.client_service.promotions.domain.Promotion;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPromotionsUseCase implements FindPromotionsInputPort {

    private final FindPromotionsOutputPort findPromotionsOutputPort;

    @Override
    public List<Promotion> handle() {
        return findPromotionsOutputPort.findAll();
    }

}
