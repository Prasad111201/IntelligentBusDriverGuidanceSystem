package com.busguidance;

import com.busguidance.model.Driver;
import com.busguidance.repository.DriverRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DriverRepositoryIntegrationTest {

    @Test
    void validDriverShouldBeStoredCorrectly() {
        DriverRepository repository = new DriverRepository();

        Driver driver = new Driver("23@@45ABCD", "John Smith", 5,
                "Heavy", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        assertTrue(repository.add(driver));
        assertEquals(driver, repository.retrieve("23@@45ABCD"));
    }

    @Test
    void duplicateDriverShouldBeRejected() {
        DriverRepository repository = new DriverRepository();

        Driver driver1 = new Driver("23@@45ABCD", "John Smith", 5,
                "Heavy", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        Driver driver2 = new Driver("23@@45ABCD", "James Brown", 3,
                "Medium", "20|Queen Street|Melbourne|VIC|Australia", "11-04-1995");

        assertTrue(repository.add(driver1));
        assertFalse(repository.add(driver2));
    }

    @Test
    void driverUpdateShouldBeStoredCorrectly() {
        DriverRepository repository = new DriverRepository();

        Driver driver = new Driver("23@@45ABCD", "John Smith", 5,
                "Medium", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        repository.add(driver);

        Driver updatedDriver = new Driver("23@@45ABCD", "John Smith", 6,
                "Heavy", "20|Queen Street|Melbourne|VIC|Australia", "12-05-1990");

        assertTrue(repository.update(updatedDriver));
        assertEquals("Heavy", repository.retrieve("23@@45ABCD").getLicenseType());
        assertEquals(6, repository.retrieve("23@@45ABCD").getExperienceYears());
    }

    @Test
    void driverCountShouldUpdateCorrectly() {
        DriverRepository repository = new DriverRepository();

        Driver driver1 = new Driver("23@@45ABCD", "John Smith", 5,
                "Heavy", "10|Main Street|Melbourne|VIC|Australia", "12-05-1990");

        Driver driver2 = new Driver("24##56WXYZ", "Mary Jones", 3,
                "Medium", "20|Queen Street|Melbourne|VIC|Australia", "11-04-1995");

        repository.add(driver1);
        repository.add(driver2);

        assertEquals(2, repository.count());
    }
}