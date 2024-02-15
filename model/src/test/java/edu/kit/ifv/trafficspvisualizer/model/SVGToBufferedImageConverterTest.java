package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SVGToBufferedImageConverterTest {
    static SVGToBufferedImageConverter converter;
    static File testSVG2;

    @BeforeAll
    static void setup() {
        converter = new SVGToBufferedImageConverter();
    }

    private File loadFile(String name) {
        return new File(Objects.requireNonNull(
            SVGToBufferedImageConverter.class.getResource("/testIcons/" + name)
        ).getFile());
    }

    @Test
    void testConvertSVGViewbox() {
        File testSVG = loadFile("test.svg");
        BufferedImage image = converter.convert(testSVG, 100, 100);
        assertTrue(image.getHeight() == 100 || image.getWidth() == 100);
    }

    @Test
    void testConvertSVGHeightWidth() {
        File testSVG = loadFile("test2.svg");
        BufferedImage image = converter.convert(testSVG, 200, 100);
        assertTrue(image.getHeight() == 200 || image.getWidth() == 100);
    }

    @Test
    void testConvertSVGNoWidthHeight() {
        File testSVG = loadFile("test3.svg");
        BufferedImage image = converter.convert(testSVG, 200, 100);
        assertTrue(image.getHeight() == 200 || image.getWidth() == 100);
    }

    @Test
    void testConvertIllegalSVG() {
        File testSVG = loadFile("test4.svg");
        BufferedImage image = converter.convert(testSVG, 100, 100);
        assertNull(image);
    }
}