package com.busguidance.repository;

import com.busguidance.model.Driver;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages Driver records.
 */
public class DriverRepository {

    private final List<Driver> drivers = new ArrayList<>();

    public boolean add(Driver driver) {

        for (Driver d : drivers) {
            if (d.getDriverID().equals(driver.getDriverID())) {
                return false;
            }
        }

        drivers.add(driver);
        return true;
    }

    public Driver retrieve(String driverID) {

        for (Driver d : drivers) {
            if (d.getDriverID().equals(driverID)) {
                return d;
            }
        }

        return null;
    }

    public boolean update(Driver updatedDriver) {

        for (int i = 0; i < drivers.size(); i++) {

            if (drivers.get(i).getDriverID()
                    .equals(updatedDriver.getDriverID())) {

                drivers.set(i, updatedDriver);
                return true;
            }
        }

        return false;
    }

    public int count() {
        return drivers.size();
    }

    public List<Driver> getAllDrivers() {
        return drivers;
    }
}