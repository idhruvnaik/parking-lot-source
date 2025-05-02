package main.java.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import main.java.models.ParkingLot;

public class ParkingLotRepository {
    private Map<Long, ParkingLot> parkingLotMap = new HashMap<>();

    public Optional<ParkingLot> getParkingLotById(Long id) {
        if (parkingLotMap.containsKey(id)) {
            return Optional.of(parkingLotMap.get(id));
        }
        return Optional.empty();
    }
}
