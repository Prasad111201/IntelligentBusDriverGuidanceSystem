package com.busguidance.repository;

import com.busguidance.model.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and manages Bus records.
 */
public class BusRepository {

    private final List<Bus> buses = new ArrayList<>();

    public boolean add(Bus bus) {

        for (Bus b : buses) {
            if (b.getBusID().equals(bus.getBusID())) {
                return false;
            }
        }

        buses.add(bus);
        return true;
    }

    public Bus retrieve(String busID) {

        for (Bus b : buses) {
            if (b.getBusID().equals(busID)) {
                return b;
            }
        }

        return null;
    }

    public boolean update(Bus updatedBus) {

        for (int i = 0; i < buses.size(); i++) {

            if (buses.get(i).getBusID()
                    .equals(updatedBus.getBusID())) {

                buses.set(i, updatedBus);
                return true;
            }
        }

        return false;
    }

    public int count() {
        return buses.size();
    }

    public List<Bus> getAllBuses() {
        return buses;
    }
}