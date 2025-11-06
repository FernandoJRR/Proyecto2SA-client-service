package com.sa.client_service.common.infrastructure.web;

import java.util.UUID;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.sa.client_service.common.domain.UserView;

import lombok.RequiredArgsConstructor;

/**
 * Cliente HTTP encargado de la comunicación con el microservicio de usuarios.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-08-15
 */
@RequiredArgsConstructor
@Component
public class UserServiceClient {

    private final WebClient.Builder webClientBuilder;

    /**
     * Obtiene la información básica de un usuario a partir de su identificador
     * único.
     * 
     * @param userId identificador único del usuario
     * @return un objeto {@link UserView} con los datos básicos del usuario
     */
    public UserView getUserById(UUID userId) {
        return webClientBuilder.build()
                .method(HttpMethod.GET)
                .uri("lb://GATEWAY/api/v1/users/{id}", userId)
                .retrieve()
                .bodyToMono(UserView.class) // Solo se valida la existencia del usuario
                .block();
    }
}
