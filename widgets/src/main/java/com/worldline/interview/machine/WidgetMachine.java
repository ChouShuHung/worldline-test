package com.worldline.interview.machine;

import com.worldline.interview.strategy.ProductionStrategy;

public class WidgetMachine {
    private final ProductionStrategy strategy;

    public WidgetMachine(ProductionStrategy strategy) {
        this.strategy = strategy;
    }

    public double produceWidgets(int quantity) {
        return strategy.produceWidgets(quantity);
    }
}
