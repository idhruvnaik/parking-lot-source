package main.java.strategies;

import main.java.models.Gate;
import main.java.models.ParkingSpot;
import main.java.models.enums.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(VehicleType vehicleType, Gate gate);
}
