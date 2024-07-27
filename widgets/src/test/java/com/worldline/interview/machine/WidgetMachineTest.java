package com.worldline.interview.machine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.worldline.interview.engine.Engine;
import com.worldline.interview.engine.InternalCombustionEngine;
import com.worldline.interview.engine.SteamEngine;
import com.worldline.interview.util.FuelType;

@ExtendWith(MockitoExtension.class)
public class WidgetMachineTest {

    @Mock
    private Engine engine;

    @InjectMocks
    private WidgetMachine widgetMachine;

    @Test
    public void testProduceWigetsWhenEngineNotRunning() {
        when(engine.isRunning()).thenReturn(false);

        double cost = widgetMachine.produceWidgets(16);

        verify(engine).start();
        verify(engine).stop();
        assertEquals(0, cost);
    }

    @Test
    public void testInvaildEngine() {
        widgetMachine = new WidgetMachine(engine);
        when(engine.isRunning()).thenReturn(true);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> widgetMachine.produceWidgets(16));

        assertEquals("Unexpected value: engine", exception.getMessage());
    }

    @Test
    public void testNegativeQuantity() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> widgetMachine.produceWidgets(-1));

        assertEquals("Negative quantity is unacceptable, please enter an valid value.", exception.getMessage());
    }

    @Nested
    class InternalCombustionEngineNestedTest {
        @BeforeEach
        public void setup() {
            engine = mock(InternalCombustionEngine.class);
            widgetMachine = new WidgetMachine(engine);
        }

        @Test
        public void testProduceWigetsWhenEngineRunningWithPetrol() {
            when(engine.getFuelType()).thenReturn(FuelType.PETROL);
            when(engine.isRunning()).thenReturn(true);

            double cost = widgetMachine.produceWidgets(16);

            verify(engine).start();
            verify(engine).stop();
            assertEquals(18, cost);
        }

        @Test
        public void testProduceWigetsWhenEngineRunningWithDiesel() {
            when(engine.getFuelType()).thenReturn(FuelType.DIESEL);
            when(engine.isRunning()).thenReturn(true);

            double cost = widgetMachine.produceWidgets(16);

            verify(engine).start();
            verify(engine).stop();
            assertEquals(24, cost);
        }
    }

    @Nested
    class SteamEngineNestedTest {
        @BeforeEach
        public void setup() {
            engine = mock(SteamEngine.class);
            widgetMachine = new WidgetMachine(engine);
        }

        @Test
        public void testProduceWigetsWhenEngineRunningWithWood() {
            when(engine.getFuelType()).thenReturn(FuelType.WOOD);
            when(engine.isRunning()).thenReturn(true);

            double cost = widgetMachine.produceWidgets(16);

            verify(engine).start();
            verify(engine).stop();
            assertEquals(34.8, cost);
        }

        @Test
        public void testProduceWigetsWhenEngineRunningWithCoal() {
            when(engine.getFuelType()).thenReturn(FuelType.COAL);
            when(engine.isRunning()).thenReturn(true);

            double cost = widgetMachine.produceWidgets(16);

            verify(engine).start();
            verify(engine).stop();
            assertEquals(45.2, cost);
        }
    }

}
