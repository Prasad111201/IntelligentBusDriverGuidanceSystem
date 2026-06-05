package com.busguidance.service;

import com.busguidance.model.Bus;
import com.busguidance.repository.BusRepository;
import com.busguidance.validation.BusValidator;

public class BusService {

    private BusRepository repository;
    private BusValidator validator;

    public BusService() {
        this.repository = new BusRepository();
        this.validator = new BusValidator();
    }

    public boolean addBus(Bus bus) {
        if (!validator.isValidBus(bus)) {
            return false;
        }

        return repository.add(bus);
    }

    public Bus retrieveBus(String busID) {
        return repository.retrieve(busID);
    }

    public boolean updateBus(Bus updatedBus) {
        Bus existingBus = repository.retrieve(updatedBus.getBusID());

        if (existingBus == null) {
            return false;
        }

        if (!validator.canUpdateCapacity(existingBus, updatedBus.getCapacity())) {
            return false;
        }

        return repository.update(updatedBus);
    }

    public int countBuses() {
        return repository.count();
    }
}