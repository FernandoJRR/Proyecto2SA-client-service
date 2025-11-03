package com.sa.client_service.promotions.application.outputports;

import java.time.LocalDate;
import java.util.List;

import com.sa.client_service.promotions.domain.Promotion;

public interface FindPromotionsByDateAndHotelOutputPort {
    public List<Promotion> findByStartDateAndHotel(LocalDate startDate, String hotelId);
}
