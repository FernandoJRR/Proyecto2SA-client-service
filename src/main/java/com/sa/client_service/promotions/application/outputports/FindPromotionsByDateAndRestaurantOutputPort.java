package com.sa.client_service.promotions.application.outputports;

import java.time.LocalDate;
import java.util.List;

import com.sa.client_service.promotions.domain.Promotion;


public interface FindPromotionsByDateAndRestaurantOutputPort {
    public List<Promotion> findByStartDateAndRestaurant(LocalDate startDate, String restaurantId);
}
