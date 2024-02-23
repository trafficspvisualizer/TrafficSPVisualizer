package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
        RouteSection routeSection = mock(RouteSection.class);
        choiceOption.addRouteSection(routeSection);
        DataObject dataObject = mock(DataObject.class);
        try {
            when(dataObject.getValue(1, "TestName", null)).thenReturn(1.0);
        } catch (InvalidDataKeyException e) {
            fail();
        }
        Icon icon = mock(Icon.class);
        when(routeSection.getIcon()).thenReturn(icon);
        when(icon.toBufferedImage(54, 54)).thenReturn(new BufferedImage(54, 54, BufferedImage.TYPE_INT_RGB));
        List<AbstractAttribute> attributes = new ArrayList<>();
        Attribute testAttribute1 = new Attribute(null);
        attributes.add(testAttribute1);
        Icon icon2 = mock(Icon.class);
        Attribute testAttribute2 = new Attribute(icon2);
        testAttribute2.setPermanentlyVisible(true);
        Mockito.when(testAttribute2.getIcon().toBufferedImage(67, 112)).thenReturn(new BufferedImage(67, 112, BufferedImage.TYPE_INT_RGB));
        attributes.add(testAttribute2);
        Attribute testAttribute3 = new Attribute(icon2);
        testAttribute3.setPermanentlyVisible(true);
        testAttribute3.setPrefix("TestStringLongerThanTwo");
        Mockito.when(testAttribute3.getIcon().toBufferedImage(54, 112)).thenReturn(new BufferedImage(67, 112, BufferedImage.TYPE_INT_RGB));
        attributes.add(testAttribute3);
        SeparatorLine testSeparatorLine = new SeparatorLine();
        attributes.add(testSeparatorLine);

        // Populate attributes list with test data
        int height = 270;
        int width = 1920;
        double max = 100.0;
        int situationIndex = 1;

        StandardImageGenerator standardImageGenerator = new StandardImageGenerator();

        try {
            BufferedImage result = standardImageGenerator.createChoiceOption(choiceOption, dataObject, attributes, height, width, max, situationIndex);
            assertNotNull(result);
            assertEquals(height, result.getHeight());
            assertEquals(width, result.getWidth());
        } catch (InvalidDataKeyException e) {
            fail();
        }
    }

}