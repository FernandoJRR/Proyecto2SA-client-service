package com.sa.client_service.common.infrastructure.web;

import java.util.UUID;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sa.client_service.common.domain.CinemaHallView;

import lombok.RequiredArgsConstructor;

/**
 * Cliente HTTP encargado de la comunicación con el microservicio de cines.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-11-06
 */
@RequiredArgsConstructor
@Component
public class CinemaServiceClient {

    private final WebClient.Builder webClientBuilder;

    /**
     * Obtiene la información básica de una sala de cine a partir de su
     * identificador único.
     * 
     * @param cinemaHallId identificador único de la sala de cine
     * @return un objeto {@link CinemaHallView} con los datos básicos de la sala
     */
    public CinemaHallView getCinemaHallById(UUID cinemaHallId) {
        return webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("lb://GATEWAY/api/v1/halls/public/{id}", cinemaHallId)
                .retrieve()
                .bodyToMono(CinemaHallView.class) // Solo se valida la existencia de la compañía
                .block();
    }

}
