package com.busguidance;

import com.busguidance.model.Bus;
import com.busguidance.repository.BusRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BusRepositoryIntegrationTest {

    @Test
    void validBusShouldBeStoredCorrectly() {
        BusRepository repository = new BusRepository();

        Bus bus = new Bus("12345678", 40, 80, "Diesel");

        assertTrue(repository.add(bus));
        assertEquals(bus, repository.retrieve("12345678"));
    }

    @Test
    void duplicateBusShouldBeRejected() {
        BusRepository repository = new BusRepository();

        Bus bus1 = new Bus("12345678", 40, 80, "Diesel");
        Bus bus2 = new Bus("12345678", 50, 90, "Hybrid");

        assertTrue(repository.add(bus1));
        assertFalse(repository.add(bus2));
    }

    @Test
    void busUpdateShouldBeStoredCorrectly() {
        BusRepository repository = new BusRepository();

        Bus bus = new Bus("12345678", 40, 80, "Diesel");
        repository.add(bus);

        Bus updatedBus = new Bus("12345678", 35, 90, "Hybrid");

        assertTrue(repository.update(updatedBus));
        assertEquals("Hybrid", repository.retrieve("12345678").getFuelType());
        assertEquals(35, repository.retrieve("12345678").getCapacity());
    }

    @Test
    void busCountShouldUpdateCorrectly() {
        BusRepository repository = new BusRepository();

        repository.add(new Bus("12345678", 40, 80, "Diesel"));
        repository.add(new Bus("87654321", 50, 90, "Hybrid"));

        assertEquals(2, repository.count());
    }
}