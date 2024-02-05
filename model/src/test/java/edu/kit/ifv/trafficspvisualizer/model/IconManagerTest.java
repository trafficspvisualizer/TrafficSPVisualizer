package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class IconManagerTest {

    @Test
    void initDefaultIcons() {
        try {
            IconManager iconManager = new IconManager(Files.createTempDirectory("ICONTEST"));
            iconManager.initDefaultIcons();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}