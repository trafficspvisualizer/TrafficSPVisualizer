package edu.kit.ifv.trafficspvisualizer.util.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ChoiceOptionImageTest {
    private ChoiceOptionImage choiceOptionImage;
    private BufferedImage image;


    @BeforeEach
    void setup() {
        image = mock(BufferedImage.class);
        choiceOptionImage = new ChoiceOptionImage(
                "TestTitle",
                image,
                1,
                2,
                3
        );
    }

    @Test
    void testGetImage() {
        assertEquals(image, choiceOptionImage.image());
    }

    @Test
    void testGetTitle() {
        assertEquals("TestTitle", choiceOptionImage.title());
    }

    @Test
    void testGetSituationNumber() {
        assertEquals(2, choiceOptionImage.situationNumber());
    }

    @Test
    void testGetBlockNumber() {
        assertEquals(1, choiceOptionImage.blockNumber());
    }

    @Test
    void setChoiceOptionNumberTest() {
        assertEquals(3, choiceOptionImage.choiceOptionNumber());
    }

    @Test
    void testAddAndGetAdditionalField() {
        choiceOptionImage.addAdditionalField(4);
        assertEquals(List.of(4), choiceOptionImage.additionalFields());
    }
}