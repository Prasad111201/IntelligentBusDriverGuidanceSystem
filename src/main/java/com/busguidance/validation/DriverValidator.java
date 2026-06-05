package com.busguidance.validation;

import com.busguidance.model.Driver;

/**
 * Validates driver data according to assignment rules D1-D5.
 */
public class DriverValidator {

    public boolean isValidDriverID(String driverID) {
        if (driverID == null || driverID.length() != 10) {
            return false;
        }

        if (!driverID.substring(0, 2).matches("[2-9]{2}")) {
            return false;
        }

        String middle = driverID.substring(2, 8);
        int specialCount = 0;

        for (char c : middle.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                specialCount++;
            }
        }

        if (specialCount < 2) {
            return false;
        }

        return driverID.substring(8, 10).matches("[A-Z]{2}");
    }

    public boolean isValidAddress(String address) {
        if (address == null) {
            return false;
        }

        String[] parts = address.split("\\|");

        return parts.length == 5
                && !parts[0].isBlank()
                && !parts[1].isBlank()
                && !parts[2].isBlank()
                && !parts[3].isBlank()
                && !parts[4].isBlank();
    }

    public boolean isValidBirthdate(String birthdate) {
        if (birthdate == null) {
            return false;
        }

        return birthdate.matches("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-\\d{4}");
    }

    public boolean canChangeLicense(Driver existingDriver, String newLicenseType) {
        if (existingDriver == null) {
            return false;
        }

        if (existingDriver.getExperienceYears() > 10) {
            return existingDriver.getLicenseType().equals(newLicenseType);
        }

        return true;
    }

    public boolean hasImmutableFieldsUnchanged(Driver existingDriver, Driver updatedDriver) {
        if (existingDriver == null || updatedDriver == null) {
            return false;
        }

        return existingDriver.getDriverID().equals(updatedDriver.getDriverID())
                && existingDriver.getName().equals(updatedDriver.getName());
    }

    public boolean isValidDriver(Driver driver) {
        if (driver == null) {
            return false;
        }

        return isValidDriverID(driver.getDriverID())
                && isValidAddress(driver.getAddress())
                && isValidBirthdate(driver.getBirthdate());
    }
}