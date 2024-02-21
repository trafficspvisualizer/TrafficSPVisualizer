package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StandardImageGeneratorTest {
    @Test
    public void testCreateChoiceOption() {
        // Create test data
        ChoiceOption choiceOption = new ChoiceOption("TestName");
        choiceOption.setColor(Color.BLACK);
        DataObject dataObject = mock(DataObject.class);
        List<AbstractAttribute> attributes = new ArrayList<>();
        // Populate attributes list with test data
        int height = 270;
        int width = 1920;
        double min = 0.0;
        double max = 100.0;
        int situationIndex = 1;

        // Create instance of class containing the method
        StandardImageGenerator standardImageGenerator = new StandardImageGenerator();

        try {
            // Call the method under test
            BufferedImage result = standardImageGenerator.createChoiceOption(choiceOption, dataObject, attributes, height, width, max, situationIndex);
            assertNotNull(result);
            assertEquals(height, result.getHeight());
            assertEquals(width, result.getWidth());
        } catch (InvalidDataKeyException e) {
            System.out.println();
        }
        // Perform assertions on the result

        // Add more assertions based on your method's behavior and expected output
    }

}