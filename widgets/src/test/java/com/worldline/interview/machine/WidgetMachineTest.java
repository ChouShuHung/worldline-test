package com.worldline.interview.machine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.worldline.interview.strategy.ProductionStrategy;

@ExtendWith(MockitoExtension.class)
public class WidgetMachineTest {

    @Mock
    private ProductionStrategy strategy;

    @InjectMocks
    private WidgetMachine widgetMachine;

    @Test
    public void testProduceWidget() {
        when(strategy.produceWidgets(16)).thenReturn((double) 18);
        widgetMachine = new WidgetMachine(strategy);

        double cost = widgetMachine.produceWidgets(16);

        assertEquals(18, cost);
    }
}
