package com.sa.client_service.promotions.application.outputports;

import java.util.List;
import java.util.UUID;

public interface MostFrequentClientsOutputPort {
    public List<UUID> findMostFrequent(Integer limit);
}
