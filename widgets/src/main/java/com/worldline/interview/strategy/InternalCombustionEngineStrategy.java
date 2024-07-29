package com.worldline.interview.strategy;

import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.util.FuelType;

public class InternalCombustionEngineStrategy implements ProductionStrategy {
    private final int BATCH = 8;
    private final InternalCombustionEngine engine;

    public InternalCombustionEngineStrategy(InternalCombustionEngine engine) {
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
            case PETROL -> 9.0;
            case DIESEL -> 12.0;
            default -> throw new IllegalStateException(
                    String.format("Fuel type can only be either %s or %s", FuelType.PETROL, FuelType.DIESEL));
        };

        return batchCount * costPerBatch;
    }

}
