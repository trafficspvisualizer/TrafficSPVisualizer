package edu.kit.ifv.trafficspvisualizer.model;

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
        assertThrows(IllegalArgumentException.class, () -> FileFormat.fromString(string));
    }
}

