package com.worldline.interview.strategy;

import com.worldline.interview.engine.SteamEngine;
import com.worldline.interview.util.FuelType;

public class SteamEngineStrategy implements ProductionStrategy {
    private final int BATCH = 2;
    private final SteamEngine engine;

    public SteamEngineStrategy(SteamEngine engine) {
        this.engine = engine;
    }

    @Override
    public double produceWidgets(int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException("Negative quantity is unacceptable, please enter an valid value.");

        engine.start();
        double cost = 0;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private double produce(int quantity) {
        double batchCount = (double) quantity / BATCH + (quantity % BATCH == 0 ? 0 : 1);
        double costPerBatch = switch (engine.getFuelType()) {
            case WOOD -> 4.35;
            case COAL -> 5.65;
            default -> throw new IllegalStateException(
                    String.format("Fuel type can only be either %s or %s", FuelType.WOOD, FuelType.COAL));
        };

        return costPerBatch * batchCount;
    }
}
