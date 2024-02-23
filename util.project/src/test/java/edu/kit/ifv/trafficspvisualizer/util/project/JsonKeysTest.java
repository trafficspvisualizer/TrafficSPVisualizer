package edu.kit.ifv.trafficspvisualizer.util.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonKeysTest {
    @Test
    void getKey() {
        assertEquals("imageHeight",JsonKeys.KEY_IMAGE_HEIGHT.getKey());
    }
}