package main.java.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import main.java.models.Vehicle;

public class VehicleRepository {
    private Map<String, Vehicle> vehicleMap = new HashMap<>();

    public Optional<Vehicle> findVehicleByVehicleNo(String vehicleNo) {
        if (vehicleMap.containsKey(vehicleNo)) {
            return Optional.of(vehicleMap.get(vehicleNo));
        } else {
            return Optional.empty();
        }
    }

    public void save(Vehicle vehicle) {
        vehicleMap.put(vehicle.getNumber(), vehicle);
    }
}
