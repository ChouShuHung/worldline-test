package com.worldline.interview.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.worldline.interview.util.FuelType;

public class SteamEngineTest {
    Engine engine;

    @BeforeEach
    public void setUp() {
        engine = new SteamEngine();
    }

    @Test
    public void testStarSteamtEngineSuccessfullyWithWood() {
        engine.setRequireFuelType(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 50);
        engine.start();
        
        assertTrue(engine.isRunning());
    }

    @Test
    public void testStarSteamtEngineSuccessfullyWithCoal() {
        engine.setRequireFuelType(FuelType.COAL);
        engine.fill(FuelType.COAL, 50);
        engine.start();
        
        assertTrue(engine.isRunning());
    }

    @Test
    public void testStartSteamEngineNegativeFuel() {
        engine.setRequireFuelType(FuelType.WOOD);
        engine.fill(FuelType.WOOD, -1);
        
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            engine.start();
        });
        
        assertEquals("Not able to start engine.", exception.getMessage());
    }

    @Test
    public void testSetIncorrectFuelType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            engine.setRequireFuelType(FuelType.DIESEL);
        });
        
        assertEquals("Fuel type can only be either Wood or Coal.", exception.getMessage());
    }

    @Test
    public void testStartSteamEngineWithoutFillWithAnyFuelType() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            engine.start();
        });
        
        assertEquals("Not able to start engine.", exception.getMessage());
    }

    @Test
    public void testEngineStop() {
        engine.setRequireFuelType(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 50);
        engine.start();
        engine.stop();
        
        assertFalse(engine.isRunning());
    }

    @Test
    public void testGetFuelType() {
        engine.setRequireFuelType(FuelType.WOOD);
        
        assertEquals(FuelType.WOOD, engine.getFuelType());
    }
}
