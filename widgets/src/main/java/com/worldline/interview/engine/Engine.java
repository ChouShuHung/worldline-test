package com.worldline.interview.engine;

import com.worldline.interview.util.FuelType;

public abstract class Engine {
    protected int fuelLevel;
    protected FuelType filledFuelType;
    private boolean running;
    protected FuelType requiredFuelType;

    public Engine(FuelType requiredFuelType) {
        this.running = false;
        this.fuelLevel = 0;
        this.requiredFuelType = requiredFuelType;
    }

    public void start() {
        if (fuelLevel > 0 && null != requiredFuelType
                && requiredFuelType.equals(filledFuelType)) {
            running = true;
        } else {
            throw new IllegalStateException("Not able to start engine.");
        }
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public FuelType getFuelType() {
        return this.requiredFuelType;
    };

    protected void setRequireFuelType(FuelType fuelType) {
        this.requiredFuelType = fuelType;
    }

    public abstract void fill(FuelType filledFuelType, int fuelLevel);

}
