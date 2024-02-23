package edu.kit.ifv.trafficspvisualizer.util.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SurveyImageTest {
    private SurveyImage surveyImage;
    private BufferedImage image;


    @BeforeEach
    void setup() {
        image = mock(BufferedImage.class);
        surveyImage = new SurveyImage(
                "TestTitle",
                image,
                1,
                2,
                3
        );
    }

    @Test
    void testGetImage() {
        assertEquals(image, surveyImage.image());
    }

    @Test
    void testGetTitle() {
        assertEquals("TestTitle", surveyImage.title());
    }

    @Test
    void testGetSituationNumber() {
        assertEquals(2, surveyImage.situationNumber());
    }

    @Test
    void testGetBlockNumber() {
        assertEquals(1, surveyImage.blockNumber());
    }

    @Test
    void setChoiceOptionNumberTest() {
        assertEquals(3, surveyImage.choiceOptionNumber());
    }

    @Test
    void testAddAndGetAdditionalField() {
        surveyImage.addAdditionalField(4);
        assertEquals(List.of(4), surveyImage.additionalFields());
    }
}