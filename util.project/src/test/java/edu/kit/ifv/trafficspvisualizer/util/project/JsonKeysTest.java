package edu.kit.ifv.trafficspvisualizer.util.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonKeysTest {
    @Test
    void getKey() {
        Assertions.assertEquals("imageHeight",JsonKeys.KEY_IMAGE_HEIGHT.getKey());
    }
}