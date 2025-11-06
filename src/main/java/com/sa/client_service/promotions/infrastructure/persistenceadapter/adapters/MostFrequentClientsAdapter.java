package com.sa.client_service.promotions.infrastructure.persistenceadapter.adapters;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sa.client_service.promotions.application.outputports.MostFrequentClientsOutputPort;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MostFrequentClientsAdapter implements MostFrequentClientsOutputPort {

    private final EntityManager entityManager;

    @Override
    public List<UUID> findMostFrequent(Integer limit) {
        int effectiveLimit = limit == null || limit <= 0 ? 10 : limit;
        return entityManager.createQuery("""
                        SELECT r.clientId
                        FROM ReviewEntity r
                        GROUP BY r.clientId
                        ORDER BY COUNT(r.id) DESC
                        """, UUID.class)
                .setMaxResults(effectiveLimit)
                .getResultList();
    }
}
