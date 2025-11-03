package com.sa.client_service.promotions.application.usecases;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.sa.client_service.promotions.application.dtos.FindPromotionEligibilityDTO;
import com.sa.client_service.promotions.application.inputports.FindEligiblePromotionInputPort;
import com.sa.client_service.promotions.application.outputports.FindPromotionsByDateAndHotelOutputPort;
import com.sa.client_service.promotions.application.outputports.MostFrequentClientsOutputPort;
import com.sa.client_service.promotions.application.outputports.MostPopularRoomsOutputPort;
import com.sa.client_service.promotions.domain.Promotion;
import com.sa.client_service.promotions.domain.PromotionTargetType;
import com.sa.client_service.promotions.domain.PromotionType;

import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@Validated
@RequiredArgsConstructor
public class FindEligiblePromotionUseCase implements FindEligiblePromotionInputPort {

    private final FindPromotionsByDateAndHotelOutputPort findPromotionsByDateAndHotelOutputPort;
    private final MostFrequentClientsOutputPort mostFrequentClientsOutputPort;
    private final MostPopularRoomsOutputPort mostPopularRoomsOutputPort;

    @Override
    public Promotion handle(@Valid FindPromotionEligibilityDTO dto) throws NotFoundException {
        List<Promotion> foundPromotions = findPromotionsByDateAndHotelOutputPort
            .findByStartDateAndHotel(dto.getStartDate(), dto.getCinemaId());

        if (foundPromotions.isEmpty()) {
            throw new NotFoundException("No hay promociones aplicables");
        }

        List<Promotion> eligiblePromotions = new ArrayList<>();

        for (Promotion foundPromotion : foundPromotions) {
            if (foundPromotion.getPromotionType().equals(PromotionType.CLIENT_MOST_FREQUENT)) {
                //Tests for promotion based on frequent client
                List<UUID> mostFrequentClients = mostFrequentClientsOutputPort.findMostFrequent(foundPromotion.getTopCount());
                if (mostFrequentClients.contains(UUID.fromString(dto.getClientId()))) {
                    //Client is eligible for this promotion
                    //Check if the target is eligible
                    if (foundPromotion.getPromotionTargetType().equals(PromotionTargetType.ALL)) {
                        if (dto.getHasSnacks() || dto.getHasTickets()) {
                            eligiblePromotions.add(foundPromotion);
                        }
                    } else if (foundPromotion.getPromotionTargetType().equals(PromotionTargetType.SNACK)) {
                        if (dto.getHasSnacks()) {
                            eligiblePromotions.add(foundPromotion);
                        }
                    } else if (foundPromotion.getPromotionTargetType().equals(PromotionTargetType.TICKET)) {
                        if (dto.getHasTickets()) {
                            eligiblePromotions.add(foundPromotion);
                        }
                    }
                }
            } else if (foundPromotion.getPromotionType().equals(PromotionType.ROOM_MOST_POPULAR)) {
                //Tests for promotion based on popular room
                List<UUID> mostPopularRooms = mostPopularRoomsOutputPort.findMostPopular(dto.getCinemaId(), foundPromotion.getTopCount());

                if (mostPopularRooms.contains(UUID.fromString(dto.getRoomId()))) {
                    //Is eligible for this promotion
                    eligiblePromotions.add(foundPromotion);
                }
            } else if (foundPromotion.getPromotionType().equals(PromotionType.MOVIE_MOST_POPULAR)) {
                //Tests for promotion based on popular room
                List<UUID> mostPopularRooms = mostPopularRoomsOutputPort.findMostPopular(dto.getCinemaId(), foundPromotion.getTopCount());

                if (mostPopularRooms.contains(UUID.fromString(dto.getRoomId()))) {
                    //Is eligible for this promotion
                    eligiblePromotions.add(foundPromotion);
                }
            }
        }

        if (eligiblePromotions.isEmpty()) {
            throw new NotFoundException("No hay promociones aplicables para esta reservacion");
        }

        return eligiblePromotions.stream()
                .max(Comparator.comparing(Promotion::getPercentage))
                .orElseThrow(() -> new NotFoundException("No hay promociones aplicables para esta reservación"));
    }

}
