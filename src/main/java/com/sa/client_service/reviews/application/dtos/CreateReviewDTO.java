package com.sa.client_service.reviews.application.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateReviewDTO {
    @NotNull(message = "El ID del cliente es obligatorio")
    private UUID clientId;

    @NotNull(message = "El ID del cine es obligatorio")
    private UUID cinemaId;

    @NotNull(message = "El ID de la sala es obligatorio")
    private UUID roomId;

    @NotNull(message = "El ID de la pelicula es obligatorio")
    private UUID movieId;

    @Min(value = 1, message = "El rating debe ser de al menos 1")
    @Max(value = 5, message = "El rating debe de ser de maximo 5")
    private int roomRating;

    @Min(value = 1, message = "El rating debe ser de al menos 1")
    @Max(value = 5, message = "El rating debe de ser de maximo 5")
    private int movieRating;

    @Size(max = 500, message = "Comment cannot exceed 500 characters")
    private String comment;
}
