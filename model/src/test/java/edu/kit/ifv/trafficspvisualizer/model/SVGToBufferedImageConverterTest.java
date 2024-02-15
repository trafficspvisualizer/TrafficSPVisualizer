package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SVGToBufferedImageConverterTest {
    static SVGToBufferedImageConverter converter;

    @BeforeAll
    static void setup() {
        converter = new SVGToBufferedImageConverter();
        File svg1 = new File(Objects.requireNonNull(
            SVGToBufferedImageConverter.class.getResource("/testIcons/test.svg")
        ).getFile());

        File svg2 = new File(Objects.requireNonNull(
            SVGToBufferedImageConverter.class.getResource("/testIcons/test.svg")
        ).getFile());
    }

    @Test
    void convert() {
    }
}