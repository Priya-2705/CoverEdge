package com.humber.service;

import com.humber.model.Broker;
import com.humber.repository.BrokerRepository;
import java.util.List;

public class BrokerService {
    private final BrokerRepository brokerRepository = new BrokerRepository();
    
    public synchronized List<Broker> getAllBrokers() {
        return brokerRepository.getAllBrokers();
    }
}