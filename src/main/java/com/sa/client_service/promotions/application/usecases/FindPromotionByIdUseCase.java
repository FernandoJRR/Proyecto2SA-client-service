package com.sa.client_service.promotions.application.usecases;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.inputports.FindPromotionByIdInputPort;
import com.sa.client_service.promotions.application.outputports.FindPromotionByIdOutputPort;
import com.sa.client_service.promotions.domain.Promotion;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPromotionByIdUseCase implements FindPromotionByIdInputPort {

    private final FindPromotionByIdOutputPort findPromotionByIdOutputPort;

    @Override
    public Promotion handle(UUID id) throws NotFoundException {
        return findPromotionByIdOutputPort.findById(id.toString())
            .orElseThrow(() -> new NotFoundException("La promocion buscada no existe."));
    }

}
