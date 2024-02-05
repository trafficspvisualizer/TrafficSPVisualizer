package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class IconTest {

    @ParameterizedTest
    @ValueSource (ints = { 0, 1, -1, 1000 })
    void getIdentifier(int id) {
        Icon icon = new SVGIcon(Path.of("test"), id);
        assertEquals(id, icon.getIdentifier());

    }

    @Test
    void getFilePath() {
        Path testPath = Path.of("path/test");
        Icon icon = new SVGIcon(testPath, 100);
        assertEquals("path/test/100.svg", icon.getFilePath().toString());
    }
}