package edu.kit.ifv.trafficspvisualizer.model.settings;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FileFormatTest {
    @ParameterizedTest
    @ValueSource(strings = { "png", "PnG", "PNG"})
    void testFromStringTrue(String string) {
        assertEquals(FileFormat.PNG, FileFormat.fromString(string));
    }

    @ParameterizedTest
    @ValueSource(strings = { "png ", "HTML", "SVG"})
    void testFromStringFalse(String string) {
        assertNull(FileFormat.fromString(string));
    }
}