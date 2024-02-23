package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ChoiceOptionGeneratorTest {
    private ChoiceOptionGenerator choiceOptionGenerator;
    private StandardImageGenerator standardImageGenerator;
    private DataObject dataObject;
    private List<ChoiceOption> choiceOptions;
    private List<AbstractAttribute> attributeList;
    private int numberOfSituations;
    private int numberOfChoiceOptions;
    private int choiceOptionHeight;
    private int choiceOptionWidth;

    @BeforeEach
    public void setUp() {
        choiceOptionGenerator = new ChoiceOptionGenerator();
        standardImageGenerator = mock(StandardImageGenerator.class);
        dataObject = mock(DataObject.class);
        choiceOptions = new ArrayList<>();
        attributeList = new ArrayList<>();
        numberOfSituations = 3; // Set appropriate values
        numberOfChoiceOptions = 2; // Set appropriate values
        choiceOptionHeight = 100; // Set appropriate values
        choiceOptionWidth = 100; // Set appropriate values
    }


    /**void createImage() {
        Project project = new Project();
        project.setChoiceOptions(choiceOptions);

        // Mock necessary method calls
        when(dataObject.getBlockNumber(anyInt())).thenReturn(1); // Mock dataObject method
        when(standardImageGenerator.createChoiceOption(any(), any(), any(), anyInt(), anyInt(), anyDouble(), anyInt()))
                .thenReturn(new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB)); // Mock standardImageGenerator method
        when(standardImageGenerator.calculateLongestRouteSection(anyInt())).thenReturn(1.0); // Mock standardImageGenerator method

        ChoiceOptionImage[] result = yourClass.createImage(project);

        // Assertions
        assertNotNull(result);
        assertEquals(numberOfSituations * numberOfChoiceOptions, result.length);
        // Add more assertions based on your requirements
    }*/
}