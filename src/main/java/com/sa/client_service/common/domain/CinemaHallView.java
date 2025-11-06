package com.sa.client_service.common.domain;

import java.util.UUID;

import lombok.Value;

/**
 * Representa una vista inmutable de una sala de cine (Cinema Hall) utilizada
 * para proyecciones o referencias en consultas de solo lectura.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-08-15
 */
@Value
public class CinemaHallView {

    private UUID id;
    private String name;
}
