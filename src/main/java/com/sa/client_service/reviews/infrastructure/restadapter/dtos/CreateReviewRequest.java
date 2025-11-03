package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateReviewRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private UUID clientId;

    @NotNull(message = "El ID del cine es obligatorio")
    private UUID cinemaId;

    @NotNull(message = "El ID de la sala es obligatorio")
    private UUID roomId;

    @NotNull(message = "El ID de la película es obligatorio")
    private UUID movieId;

    @Min(value = 1, message = "El rating debe ser al menos 1")
    @Max(value = 5, message = "El rating no puede ser mayor a 5")
    private int roomRating;

    @Min(value = 1, message = "El rating debe ser al menos 1")
    @Max(value = 5, message = "El rating no puede ser mayor a 5")
    private int movieRating;

    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres")
    private String comment;
}
