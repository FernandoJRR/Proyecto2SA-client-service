package com.sa.client_service.promotions.application.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindPromotionEligibilityDTO {
    @NotBlank(message = "El ID del cliente es obligatorio.")
    private String clientId;

    @NotBlank(message = "El ID del cine es obligatorio.")
    private String cinemaId;

    @NotBlank(message = "El ID de la sala es obligatorio.")
    private String roomId;

    @NotNull(message = "La fecha de inicio de la reservacion es obligatoria.")
    private LocalDate startDate;

    @NotNull(message = "La fecha de fin de la reservacion es obligatoria.")
    private LocalDate endDate;

    @NotNull(message = "La indicacion de snacks es obligatoria")
    private Boolean hasSnacks;

    @NotNull(message = "La indicacion de boletos es obligatoria")
    private Boolean hasTickets;
}
