package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeparatorLineTest {

    @Test
    void testIsAndSetActive() {
        SeparatorLine separatorLine = new SeparatorLine();
        assertTrue(separatorLine.isActive());
        separatorLine.setActive(false);
        assertFalse(separatorLine.isActive());
    }

    @Test
    void hasValues() {
        SeparatorLine separatorLine = new SeparatorLine();
        assertFalse(separatorLine.hasValues());
    }
}