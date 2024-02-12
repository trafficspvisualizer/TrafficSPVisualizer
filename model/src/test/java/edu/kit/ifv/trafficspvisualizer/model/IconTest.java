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

    @ParameterizedTest
    @ValueSource (strings = { "path/test", "", "123", "."})
    void testGetFilePath(String path) {
        Path testPath = Paths.get(path);
        Icon icon = new SVGIcon(testPath, 100);
        assertEquals(testPath.resolve("100.svg"), icon.getIconPath());
    }

    @Test
    void testEqualsAndHashCode() {
        Icon icon = new SVGIcon(Paths.get("path/test"), 0);
        Icon other = new SVGIcon(icon.getIconPath().getParent(), icon.getIdentifier());
        assertEquals(icon, other);
        assertEquals(icon.hashCode(), other.hashCode());
        other = new SVGIcon(Paths.get("path/other"), 0);
        assertNotEquals(icon, other);
        assertNotEquals(icon.hashCode(), other.hashCode());
    }
}