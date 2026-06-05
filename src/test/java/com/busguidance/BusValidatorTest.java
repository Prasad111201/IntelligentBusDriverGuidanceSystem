package com.busguidance;

import com.busguidance.model.Bus;
import com.busguidance.model.Driver;
import com.busguidance.validation.BusValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusValidatorTest {

    BusValidator validator = new BusValidator();

    @Test
    void validBusIDShouldReturnTrue() {
        assertTrue(validator.isValidBusID("12345678"));
    }

    @Test
    void busIDWithLettersShouldReturnFalse() {
        assertFalse(validator.isValidBusID("1234ABCD"));
    }

    @Test
    void busIDWithWrongLengthShouldReturnFalse() {
        assertFalse(validator.isValidBusID("12345"));
    }

    @Test
    void decreasingCapacityShouldBeAllowed() {
        Bus bus = new Bus("12345678", 50, 80, "Diesel");
        assertTrue(validator.canUpdateCapacity(bus, 40));
    }

    @Test
    void increasingCapacityShouldNotBeAllowed() {
        Bus bus = new Bus("12345678", 50, 80, "Diesel");
        assertFalse(validator.canUpdateCapacity(bus, 60));
    }

    @Test
    void sameCapacityShouldBeAllowed() {
        Bus bus = new Bus("12345678", 50, 80, "Diesel");
        assertTrue(validator.canUpdateCapacity(bus, 50));
    }

    @Test
    void driverOlderThan50CannotDriveLargeBus() {
        Driver driver = new Driver();
        Bus bus = new Bus("12345678", 50, 80, "Diesel");
        assertFalse(validator.canDriverOperateCapacity(driver, bus, 51));
    }

    @Test
    void driverAge50CanDriveLargeBus() {
        Driver driver = new Driver();
        Bus bus = new Bus("12345678", 50, 80, "Diesel");
        assertTrue(validator.canDriverOperateCapacity(driver, bus, 50));
    }

    @Test
    void olderDriverCanDriveSmallBus() {
        Driver driver = new Driver();
        Bus bus = new Bus("12345678", 40, 80, "Diesel");
        assertTrue(validator.canDriverOperateCapacity(driver, bus, 60));
    }

    @Test
    void experiencedDriverCanDriveElectricBus() {
        Driver driver = new Driver("23@@45ABCD", "John", 5,
                "Heavy", "1|Street|City|State|Country", "01-01-1990");
        Bus bus = new Bus("12345678", 40, 80, "Electricity");
        assertTrue(validator.canDriverOperateElectricBus(driver, bus));
    }

    @Test
    void inexperiencedDriverCannotDriveElectricBus() {
        Driver driver = new Driver("23@@45ABCD", "John", 4,
                "Heavy", "1|Street|City|State|Country", "01-01-1990");
        Bus bus = new Bus("12345678", 40, 80, "Electricity");
        assertFalse(validator.canDriverOperateElectricBus(driver, bus));
    }

    @Test
    void dieselBusHasNoExperienceRestriction() {
        Driver driver = new Driver("23@@45ABCD", "John", 1,
                "Medium", "1|Street|City|State|Country", "01-01-1990");
        Bus bus = new Bus("12345678", 40, 80, "Diesel");
        assertTrue(validator.canDriverOperateElectricBus(driver, bus));
    }

    @Test
    void heavyLicenceCanDriveHybridBus() {
        Driver driver = new Driver("23@@45ABCD", "John", 5,
                "Heavy", "1|Street|City|State|Country", "01-01-1990");
        Bus bus = new Bus("12345678", 40, 80, "Hybrid");
        assertTrue(validator.hasValidLicenceForFuelType(driver, bus));
    }

    @Test
    void publicTransportLicenceCanDriveElectricBus() {
        Driver driver = new Driver("23@@45ABCD", "John", 5,
                "PublicTransport", "1|Street|City|State|Country", "01-01-1990");
        Bus bus = new Bus("12345678", 40, 80, "Electricity");
        assertTrue(validator.hasValidLicenceForFuelType(driver, bus));
    }

    @Test
    void mediumLicenceCannotDriveElectricBus() {
        Driver driver = new Driver("23@@45ABCD", "John", 5,
                "Medium", "1|Street|City|State|Country", "01-01-1990");
        Bus bus = new Bus("12345678", 40, 80, "Electricity");
        assertFalse(validator.hasValidLicenceForFuelType(driver, bus));
    }
}