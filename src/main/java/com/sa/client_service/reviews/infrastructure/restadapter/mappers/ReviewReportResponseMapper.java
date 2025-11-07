package com.sa.client_service.reviews.infrastructure.restadapter.mappers;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.sa.client_service.common.domain.CinemaHallView;
import com.sa.client_service.common.domain.UserView;
import com.sa.client_service.common.infrastructure.web.CinemaServiceClient;
import com.sa.client_service.common.infrastructure.web.UserServiceClient;
import com.sa.client_service.reviews.domain.Review;
import com.sa.client_service.reviews.infrastructure.persistenceadapter.projections.RoomRatingStatsProjection;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.CommentReportResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.RoomRatingStatsResponse;
import com.sa.client_service.reviews.infrastructure.restadapter.dtos.LikedRoomReportResponse;
import com.sap.common_lib.util.DateUtils;
import com.sap.common_lib.util.SafeFetcher;

/**
 * Mapper encargado de convertir objetos del dominio {@link Review} a
 * respuestas REST {@link CommentReportResponse} para reportes de comentarios.
 * 
 * @author Luis Monterroso
 * @version 1.0
 * @since 2025-08-15
 */
@Mapper(componentModel = "spring")
public abstract class ReviewReportResponseMapper {

        @Autowired
        private CinemaServiceClient cinemaServiceClient;

        @Autowired
        private UserServiceClient userServiceClient;

        public abstract List<CommentReportResponse> toReviewResponse(List<Review> reviews);

        public abstract List<LikedRoomReportResponse> toTopLikedRoomsReportResponse(List<Review> reviews);

        public abstract List<RoomRatingStatsResponse> toRoomRatingStatsResponses(
                        List<RoomRatingStatsProjection> reviews);

        /**
         * Convierte una entidad de dominio {@link Review} en un DTO de respuesta
         * {@link CommentReportResponse}.
         * 
         * @param review entidad del dominio a convertir
         * @return respuesta de reporte de comentario
         */
        @Mapping(target = "clientEmail", ignore = true)
        @Mapping(target = "roomName", ignore = true)
        public abstract CommentReportResponse toReviewResponse(Review review);

        @Mapping(target = "roomName", ignore = true)
        @Mapping(target = "clientEmail", ignore = true)
        @Mapping(target = "rating", expression = "java(review.getRoomRating() != null ? review.getRoomRating().getValue() : null)")
        @Mapping(target = "createdAt", ignore = true)
        public abstract LikedRoomReportResponse toTopLikedRoomsReportResponse(Review review);

        @Mapping(target = "roomName", ignore = true)
        public abstract RoomRatingStatsResponse toTopLikedRoomsReportResponse(RoomRatingStatsProjection projection);

        /**
         * Enriquecimiento de datos posterior al mapeo.
         * 
         * @param review   entidad original del dominio
         * @param response objeto destino ya mapeado
         */
        @AfterMapping
        protected void afterMapping(Review review, @MappingTarget CommentReportResponse response) {
                // Recuperar usuario de forma segura
                UserView user = SafeFetcher.run(
                                () -> userServiceClient.getUserById(review.getClientId()), // operación principal
                                () -> new UserView(null, "Desconocido") // fallback
                );

                // Recuperar room de forma segura
                CinemaHallView cinemaHallView = SafeFetcher.run(
                                () -> cinemaServiceClient.getCinemaHallById(review.getRoomId()), // operación principal
                                () -> new CinemaHallView(null, "Desconocido") // fallback
                );

                response.setCreatedAt(DateUtils.format(review.getCreatedAt()));
                response.setClientEmail(user.getEmail());
                response.setRoomName(cinemaHallView.getName());

        }

        @AfterMapping
        protected void after(Review review, @MappingTarget LikedRoomReportResponse target) {
                // Usuario
                UserView user = SafeFetcher.run(
                                () -> userServiceClient.getUserById(review.getClientId()),
                                () -> new UserView(null, "Desconocido"));
                // Sala
                CinemaHallView hall = SafeFetcher.run(
                                () -> cinemaServiceClient.getCinemaHallById(review.getRoomId()),
                                () -> new CinemaHallView(null, "Desconocido"));

                target.setClientEmail(user.getEmail());
                target.setRoomName(hall.getName());
                target.setCreatedAt(DateUtils.format(review.getCreatedAt()));
        }

        @AfterMapping
        protected void after(RoomRatingStatsProjection review, @MappingTarget RoomRatingStatsResponse target) {
                // Sala
                CinemaHallView hall = SafeFetcher.run(
                                () -> cinemaServiceClient.getCinemaHallById(review.roomId()),
                                () -> new CinemaHallView(null, "Desconocido"));

                target.setRoomName(hall.getName());
        }
}
