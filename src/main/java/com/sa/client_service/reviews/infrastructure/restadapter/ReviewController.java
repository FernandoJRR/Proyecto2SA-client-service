package com.sa.client_service.reviews.infrastructure.restadapter;

import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sa.client_service.reviews.application.dtos.CreateReviewDTO;
import com.sa.client_service.reviews.application.dtos.FindReviewsDTO;
import com.sa.client_service.reviews.application.inputports.CreateReviewInputPort;
import com.sa.client_service.reviews.application.inputports.FindReviewsInputPort;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.CreateReviewRequest;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.FindReviewsRequest;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.ReviewResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.mappers.ReviewsRestMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping(path = "/api/v1/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Tag(name = "Reseñas", description = "Operaciones para la gestión de reseñas")
public class ReviewController {

    private final CreateReviewInputPort createReviewInputPort;
    private final FindReviewsInputPort findReviewsInputPort;

    @Operation(summary = "Crear una nueva reseña")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Reseña creada correctamente")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponse createReview(@Valid @RequestBody CreateReviewRequest request) {
        CreateReviewDTO dto = ReviewsRestMapper.INSTANCE.toCreateReviewDTO(request);
        return ReviewsRestMapper.INSTANCE.toReviewResponse(createReviewInputPort.handle(dto));
    }

    @Operation(summary = "Listar reseñas por filtros opcionales")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reseñas recuperadas correctamente")
    })
    @GetMapping
    public List<ReviewResponse> findReviews(@ParameterObject FindReviewsRequest request) {
        FindReviewsDTO filters = ReviewsRestMapper.INSTANCE.toFindReviewsDTO(request);
        return ReviewsRestMapper.INSTANCE.toReviewResponses(findReviewsInputPort.handle(filters));
    }
}
