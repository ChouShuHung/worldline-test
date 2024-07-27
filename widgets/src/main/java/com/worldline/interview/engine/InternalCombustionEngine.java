package com.worldline.interview.engine;

import com.worldline.interview.util.FuelType;

public class InternalCombustionEngine
        extends Engine {

    public InternalCombustionEngine(FuelType requiredFuelType) {
        super(requiredFuelType);
    }

    @Override
    public void fill(FuelType fuelType, int fuelLevel) {
        if (fuelLevel >= 0) {
            this.fuelLevel = Math.min(fuelLevel, 100);
        } else {
            this.fuelLevel = 0;
        }
        this.filledFuelType = fuelType;
    }
}
