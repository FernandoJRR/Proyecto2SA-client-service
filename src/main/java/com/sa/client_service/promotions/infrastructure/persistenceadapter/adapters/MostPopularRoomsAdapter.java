package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.MostPopularRoomsOutputPort;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MostPopularRoomsAdapter implements MostPopularRoomsOutputPort {

    private final EntityManager entityManager;

    @Override
    public List<UUID> findMostPopular(String hotelId, Integer limit) {
        int effectiveLimit = limit == null || limit <= 0 ? 10 : limit;
        UUID cinemaId = UUID.fromString(hotelId);

        return entityManager.createQuery("""
                        SELECT r.roomId
                        FROM ReviewEntity r
                        WHERE r.cinemaId = :cinemaId
                        GROUP BY r.roomId
                        ORDER BY COUNT(r.id) DESC
                        """, UUID.class)
                .setParameter("cinemaId", cinemaId)
                .setMaxResults(effectiveLimit)
                .getResultList();
    }
}
