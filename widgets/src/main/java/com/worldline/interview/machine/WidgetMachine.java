package com.worldline.interview.machine;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.engine.SteamEngine;

public class WidgetMachine {
    private final Engine engine;

    public WidgetMachine(Engine engine) {
        this.engine = engine;
    }

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
        int batch = switch (engine) {
            case InternalCombustionEngine ice -> 8;
            case SteamEngine se -> 2;
            default -> throw new IllegalArgumentException("Unexpected value: " + engine);
        };
        double batchCount = (double) quantity / batch + (quantity % batch == 0 ? 0 : 1);
        double costPerBatch = switch (engine.getFuelType()) {
            case PETROL -> 9.0;
            case DIESEL -> 12.0;
            case WOOD -> 4.35;
            case COAL -> 5.65;
        };

        return batchCount * costPerBatch;
    }
}
