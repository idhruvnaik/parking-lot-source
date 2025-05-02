package main.java.factory;

import main.java.models.enums.SpotAssignmentStrategyType;
import main.java.strategies.*;

public class SpotAssignmentFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategy(
            SpotAssignmentStrategyType spotAssignmentStrategyType) {
        if (spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.CHEAPEST)) {
            return new CheapestSpotAssignmentStrategy();
        } else if (spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)) {
            return new NearestSpotAssignmentStrategy();
        } else {
            return null;
        }
    }
}
