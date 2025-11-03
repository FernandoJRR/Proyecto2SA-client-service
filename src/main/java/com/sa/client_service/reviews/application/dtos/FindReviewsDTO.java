package com.sa.client_service.reviews.application.dtos;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindReviewsDTO {
    private UUID cinemaId;
    private UUID roomId;
    private UUID movieId;
    private UUID clientId;
}
