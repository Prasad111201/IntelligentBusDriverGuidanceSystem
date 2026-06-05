package com.busguidance;

import com.busguidance.model.Driver;
import com.busguidance.validation.DriverValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverValidatorTest {

    DriverValidator validator = new DriverValidator();

    // D1: Driver ID rules

    @Test
    void validDriverIDShouldReturnTrue() {
        assertTrue(validator.isValidDriverID("23@@45ABCD"));
    }

    @Test
    void driverIDWithWrongLengthShouldReturnFalse() {
        assertFalse(validator.isValidDriverID("23@@AB"));
    }

    @Test
    void driverIDWithoutTwoSpecialCharactersShouldReturnFalse() {
        assertFalse(validator.isValidDriverID("23456789AB"));
    }

    // D2: Address format

    @Test
    void validAddressShouldReturnTrue() {
        assertTrue(validator.isValidAddress("10|Main Street|Melbourne|VIC|Australia"));
    }

    @Test
    void addressMissingCountryShouldReturnFalse() {
        assertFalse(validator.isValidAddress("10|Main Street|Melbourne|VIC"));
    }

    @Test
    void addressUsingWrongDelimiterShouldReturnFalse() {
        assertFalse(validator.isValidAddress("10,Main Street,Melbourne,VIC,Australia"));
    }

    // D3: Birthdate format

    @Test
    void validBirthdateShouldReturnTrue() {
        assertTrue(validator.isValidBirthdate("12-05-1990"));
    }

    @Test
    void birthdateWithWrongFormatShouldReturnFalse() {
        assertFalse(validator.isValidBirthdate("1990-05-12"));
    }

    @Test
    void birthdateWithInvalidMonthShouldReturnFalse() {
        assertFalse(validator.isValidBirthdate("12-15-1990"));
    }

    // D4: License update restriction

    @Test
    void driverWithMoreThanTenYearsCannotChangeLicense() {
        Driver driver = new Driver("23@@45ABCD", "John Smith", 11,
                "Heavy", "10|Main Street|Melbourne|VIC|Australia", "12-05-1980");

        assertFalse(validator.canChangeLicense(driver, "Medium"));
    }

    @Test
    void driverWithMoreThanTenYearsCanKeepSameLicense() {
        Driver driver = new Driver("23@@45ABCD", "John Smith", 15,
                "Heavy", "10|Main Street|Melbourne|VIC|Australia", "12-05-1980");

        assertTrue(validator.canChangeLicense(driver, "Heavy"));
    }

    @Test
    void driverWithTenYearsOrLessCanChangeLicense() {
        Driver driver = new Driver("23@@45ABCD", "John Smith", 10,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        assertTrue(validator.canChangeLicense(driver, "Heavy"));
    }

    // D5: Immutable fields

    @Test
    void unchangedDriverIDAndNameShouldReturnTrue() {
        Driver oldDriver = new Driver("23@@45ABCD", "John Smith", 5,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        Driver updatedDriver = new Driver("23@@45ABCD", "John Smith", 6,
                "Heavy", "20|Queen Street|Melbourne|VIC|Australia", "12-05-1990");

        assertTrue(validator.hasImmutableFieldsUnchanged(oldDriver, updatedDriver));
    }

    @Test
    void changedDriverIDShouldReturnFalse() {
        Driver oldDriver = new Driver("23@@45ABCD", "John Smith", 5,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        Driver updatedDriver = new Driver("24@@45ABCD", "John Smith", 5,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        assertFalse(validator.hasImmutableFieldsUnchanged(oldDriver, updatedDriver));
    }

    @Test
    void changedNameShouldReturnFalse() {
        Driver oldDriver = new Driver("23@@45ABCD", "John Smith", 5,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        Driver updatedDriver = new Driver("23@@45ABCD", "James Smith", 5,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        assertFalse(validator.hasImmutableFieldsUnchanged(oldDriver, updatedDriver));
    }
}