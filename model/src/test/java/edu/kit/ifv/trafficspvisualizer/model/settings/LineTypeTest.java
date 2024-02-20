package edu.kit.ifv.trafficspvisualizer.model.settings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class LineTypeTest {

    @ParameterizedTest
    @ValueSource(strings = { "solid", "Solid", "SOLID"})
    void testFromStringTrue(String string) {
        assertEquals(LineType.SOLID, LineType.fromString(string));
    }

    @ParameterizedTest
    @ValueSource(strings = { "dashed ", "S0LID", "das hed"})
    void testFromStringFalse(String string) {
        assertNull(LineType.fromString(string));
    }
}