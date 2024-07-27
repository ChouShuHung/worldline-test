package com.worldline.interview.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.worldline.interview.util.FuelType;

public class InternalCombustionEngineTest {

    Engine engine;

    @BeforeEach
    public void setUp() {
        engine = new InternalCombustionEngine(FuelType.PETROL);
    }

    @Test
    public void testStartEngineSuccessfully() {
        engine.fill(FuelType.PETROL, 50);
        engine.start();
        
        assertTrue(engine.isRunning());
    }

    @Test
    public void testStartEngineOverFuel() {
        engine.fill(FuelType.PETROL, 150);
        engine.start();
        
        assertTrue(engine.isRunning());
    }

    @Test
    public void testStartEngineNegativeFuel() {
        engine.fill(FuelType.PETROL, -1);
        
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            engine.start();
        });
        
        assertEquals("Not able to start engine.", exception.getMessage());
    }

    @Test
    public void testStartEngineWithIncorrectFuel() {
        engine.fill(FuelType.DIESEL, 50);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            engine.start();
        });
        
        assertEquals("Not able to start engine.", exception.getMessage());
    }

    @Test
    public void testStartEngineWithoutFillupFuel() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            engine.start();
        });
        
        assertEquals("Not able to start engine.", exception.getMessage());
    }

    @Test
    public void testEngineStop() {
        engine.fill(FuelType.PETROL, 50);
        engine.start();
        engine.stop();
        
        assertFalse(engine.isRunning());
    }

    @Test
    public void testGetFuelType() {
        assertEquals(FuelType.PETROL, engine.getFuelType());
    }
}
