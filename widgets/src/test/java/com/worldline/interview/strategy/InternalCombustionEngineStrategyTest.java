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

import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.util.FuelType;

@ExtendWith(MockitoExtension.class)
public class InternalCombustionEngineStrategyTest {
    @Mock
    private InternalCombustionEngine engine;
    @InjectMocks
    private InternalCombustionEngineStrategy strategy;

    @BeforeEach
    public void setup() {
        strategy = new InternalCombustionEngineStrategy(engine);
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
    public void testNegativeQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> strategy.produceWidgets(-1));

        assertEquals("Negative quantity is unacceptable, please enter an valid value.", exception.getMessage());
    }

    @Test
    public void testProduceWigetsWhenEngineRunningWithPetrol() {
        when(engine.getFuelType()).thenReturn(FuelType.PETROL);
        when(engine.isRunning()).thenReturn(true);

        double cost = strategy.produceWidgets(16);

        verify(engine).start();
        verify(engine).stop();
        assertEquals(18, cost);
    }

    @Test
    public void testProduceWigetsWhenEngineRunningWithDiesel() {
        when(engine.getFuelType()).thenReturn(FuelType.DIESEL);
        when(engine.isRunning()).thenReturn(true);

        double cost = strategy.produceWidgets(16);

        verify(engine).start();
        verify(engine).stop();
        assertEquals(24, cost);
    }

    @Test
    public void testProduceWigetsWhenEngineRunningWithInpropreicateFuelType() {
        when(engine.getFuelType()).thenReturn(FuelType.COAL);
        when(engine.isRunning()).thenReturn(true);

        Exception exception = assertThrows(IllegalStateException.class, () -> strategy.produceWidgets(16));

        assertEquals("Fuel type can only be either PETROL or DIESEL", exception.getMessage());
    }

}
