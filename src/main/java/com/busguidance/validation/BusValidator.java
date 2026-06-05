package com.busguidance.validation;

import com.busguidance.model.Bus;
import com.busguidance.model.Driver;

/**
 * Validates bus data according to assignment rules B1-B5.
 */
public class BusValidator {

    public boolean isValidBusID(String busID) {
        return busID != null && busID.matches("\\d{8}");
    }

    public boolean canUpdateCapacity(Bus existingBus, int newCapacity) {
        if (existingBus == null) {
            return false;
        }

        return newCapacity <= existingBus.getCapacity();
    }

    public boolean canDriverOperateCapacity(Driver driver, Bus bus, int driverAge) {
        if (driver == null || bus == null) {
            return false;
        }

        if (driverAge > 50 && bus.getCapacity() >= 50) {
            return false;
        }

        return true;
    }

    public boolean canDriverOperateElectricBus(Driver driver, Bus bus) {
        if (driver == null || bus == null) {
            return false;
        }

        if (bus.getFuelType().equalsIgnoreCase("Electricity")) {
            return driver.getExperienceYears() >= 5;
        }

        return true;
    }

    public boolean hasValidLicenceForFuelType(Driver driver, Bus bus) {
        if (driver == null || bus == null) {
            return false;
        }

        String fuelType = bus.getFuelType();
        String licence = driver.getLicenseType();

        if (fuelType.equalsIgnoreCase("Electricity")
                || fuelType.equalsIgnoreCase("Hybrid")) {
            return licence.equalsIgnoreCase("Heavy")
                    || licence.equalsIgnoreCase("PublicTransport");
        }

        return true;
    }

    public boolean isValidBus(Bus bus) {
        if (bus == null) {
            return false;
        }

        return isValidBusID(bus.getBusID())
                && bus.getCapacity() > 0
                && bus.getFuelLevel() >= 0
                && bus.getFuelLevel() <= 100
                && bus.getFuelType() != null;
    }
}