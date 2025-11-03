package com.sa.client_service.reviews.infrastructure.restadapter.dtos;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FindReviewsRequest {

    @Schema(description = "Identificador del cine", format = "uuid")
    private UUID cinemaId;

    @Schema(description = "Identificador de la sala", format = "uuid")
    private UUID roomId;

    @Schema(description = "Identificador de la película", format = "uuid")
    private UUID movieId;

    @Schema(description = "Identificador del cliente", format = "uuid")
    private UUID clientId;
}
