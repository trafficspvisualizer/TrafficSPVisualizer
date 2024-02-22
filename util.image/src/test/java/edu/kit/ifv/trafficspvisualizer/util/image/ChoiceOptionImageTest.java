package edu.kit.ifv.trafficspvisualizer.util.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceOptionImageTest {
    private ChoiceOptionImage choiceOptionImage;

    @BeforeEach
    void setup() {
        choiceOptionImage = new ChoiceOptionImage();
    }

    @Test
    void setAndGetImage() {
        BufferedImage image = Mockito.mock(BufferedImage.class);
        choiceOptionImage.setImage(image);
        assertEquals(image, choiceOptionImage.getImage());
    }

    @Test
    void addAndGetInfos() {
        choiceOptionImage.add("TestString");
        assertTrue(choiceOptionImage.getInfos().contains("TestString"));
    }

    @Test
    void setAndGetTitle() {
        choiceOptionImage.setTitle("TestTitle");
        assertEquals("TestTitle", choiceOptionImage.getTitle());
    }

    @Test
    void getScenarioNumber() {
    }

    @Test
    void setBlockNumber() {
    }

    @Test
    void setScenarioNumber() {
    }
}