package com.worldline.interview.engine;

import com.worldline.interview.util.FuelType;

public class SteamEngine extends Engine {

    public SteamEngine() {
        super(null);
    }

    public void setRequireFuelType(FuelType requiredFuelType) {
        this.requiredFuelType = switch (requiredFuelType) {
            case WOOD, COAL -> requiredFuelType;
            default -> throw new IllegalArgumentException("Fuel type can only be either Wood or Coal.");
        };
    }

    @Override
    public void fill(FuelType fuelType, int fuelLevel) {
        this.filledFuelType = fuelType;
        this.fuelLevel = fuelLevel;
    }
}
