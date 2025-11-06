package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.MostPopularDishesOutputPort;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MostPopularDishesAdapter implements MostPopularDishesOutputPort {

    private final EntityManager entityManager;

    @Override
    public List<UUID> findMostPopular(String restaurantId, Integer limit) {
        int effectiveLimit = limit == null || limit <= 0 ? 10 : limit;
        UUID cinemaId = UUID.fromString(restaurantId);

        return entityManager.createQuery("""
                        SELECT r.movieId
                        FROM ReviewEntity r
                        WHERE r.cinemaId = :cinemaId
                        GROUP BY r.movieId
                        ORDER BY COUNT(r.id) DESC
                        """, UUID.class)
                .setParameter("cinemaId", cinemaId)
                .setMaxResults(effectiveLimit)
                .getResultList();
    }
}
