package com.worldline.interview.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.worldline.interview.engine.SteamEngine;
import com.worldline.interview.util.FuelType;

@ExtendWith(MockitoExtension.class)
public class SteamEngineStrategyTest {

    @Mock
    SteamEngine engine;

    @InjectMocks
    SteamEngineStrategy strategy;

    @BeforeEach
    public void setup() {
        strategy = new SteamEngineStrategy(engine);
    }

    @Test
    public void testProduceWigetsWhenEngineRunningWithWood() {
        when(engine.getFuelType()).thenReturn(FuelType.WOOD);
        when(engine.isRunning()).thenReturn(true);

        double cost = strategy.produceWidgets(16);

        verify(engine).start();
        verify(engine).stop();
        assertEquals(34.8, cost);
    }

    @Test
    public void testProduceWigetsWhenEngineRunningWithCoal() {
        when(engine.getFuelType()).thenReturn(FuelType.COAL);
        when(engine.isRunning()).thenReturn(true);

        double cost = strategy.produceWidgets(16);

        verify(engine).start();
        verify(engine).stop();
        assertEquals(45.2, cost);
    }

    @Test
    public void testProduceWigetsWhenEngineNotRunning() {
        when(engine.isRunning()).thenReturn(false);

        double cost = strategy.produceWidgets(16);

        verify(engine).start();
        verify(engine).stop();
        assertEquals(0, cost);
    }

    @Test
    public void testProduceWigetsWhenEngineRunningWithInapproriatedFuelType() {
        when(engine.getFuelType()).thenReturn(FuelType.DIESEL);
        when(engine.isRunning()).thenReturn(true);

        Exception exception = assertThrows(IllegalStateException.class, () -> strategy.produceWidgets(16));

        assertEquals("Fuel type can only be either WOOD or COAL", exception.getMessage());
    }

    @Test
    public void testNegativeQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> strategy.produceWidgets(-1));

        assertEquals("Negative quantity is unacceptable, please enter an valid value.", exception.getMessage());
    }
}
