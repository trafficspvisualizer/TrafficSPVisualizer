package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class IconTest {

    @ParameterizedTest
    @ValueSource (ints = { 0, 1, -1, 1000 })
    void testGetIdentifier(int id) {
        Icon icon = new SVGIcon(Path.of("test"), id);
        assertEquals(id, icon.getIdentifier());

    }

    @Test
    void testGetFilePath() {
        Path testPath = Path.of("path/test");
        Icon icon = new SVGIcon(testPath, 100);
        assertEquals(Paths.get("path/test/100.svg"), icon.getIconPath());
    }
}