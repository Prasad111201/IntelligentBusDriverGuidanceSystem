package com.busguidance.service;

import com.busguidance.model.Driver;
import com.busguidance.repository.DriverRepository;
import com.busguidance.validation.DriverValidator;

public class DriverService {

    private DriverRepository repository;
    private DriverValidator validator;

    public DriverService() {
        this.repository = new DriverRepository();
        this.validator = new DriverValidator();
    }

    public boolean addDriver(Driver driver) {
        if (!validator.isValidDriver(driver)) {
            return false;
        }

        return repository.add(driver);
    }

    public Driver retrieveDriver(String driverID) {
        return repository.retrieve(driverID);
    }

    public boolean updateDriver(Driver updatedDriver) {
        Driver existingDriver = repository.retrieve(updatedDriver.getDriverID());

        if (existingDriver == null) {
            return false;
        }

        if (!validator.hasImmutableFieldsUnchanged(existingDriver, updatedDriver)) {
            return false;
        }

        if (!validator.canChangeLicense(existingDriver, updatedDriver.getLicenseType())) {
            return false;
        }

        return repository.update(updatedDriver);
    }

    public int countDrivers() {
        return repository.count();
    }
}