package edu.kit.ifv.trafficspvisualizer.model.settings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ExportTypeTest {

    @Test
    void testFromStringTrue() {
        assertEquals(ExportType.HTML, ExportType.fromString("HtML"));
        assertEquals(ExportType.CHOICE_OPTION, ExportType.fromString("choiceoption"));
        assertEquals(ExportType.SITUATION, ExportType.fromString("SITUATION"));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Choicoption", "", "Hmtl"})
    void testFromStringFalse(String string) {
        assertNull(ExportType.fromString(string));
    }
}