package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.data.SituationData;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ImageCollectionGeneratorTest {

    @Test
    void getImageCollectionGenerator() {
    }

    @Test
    void testGetImageCollectionGeneratorForSituation() {
        ImageCollectionGenerator result = ImageCollectionGenerator.getImageCollectionGenerator(ExportType.SITUATION);// Assuming ImageCollection is the class containing getImageCollectionGenerator method
        assertInstanceOf(SituationGenerator.class, result);
    }

    @Test
    void testGetImageCollectionGeneratorForChoiceOption() {
        ImageCollectionGenerator result = ImageCollectionGenerator.getImageCollectionGenerator(ExportType.CHOICE_OPTION);// Assuming ImageCollection is the class containing getImageCollectionGenerator method
        assertInstanceOf(ChoiceOptionGenerator.class, result);
    }

    @Test
    void calculateLengthOfRouteSection() {
        try {
            ChoiceOption choiceOption = Mockito.mock(ChoiceOption.class);
            SituationData[] situations = new SituationData[1];
            DataObject dataObject = mock(DataObject.class);
            RouteSection routeSection1 = Mockito.mock(RouteSection.class);
            RouteSection routeSection2 = Mockito.mock(RouteSection.class);

            // Stubbing behavior for choiceOption and routeSections
            Mockito.when(choiceOption.getName()).thenReturn("OptionName");
            Mockito.when(choiceOption.getRouteSections()).thenReturn(List.of(routeSection1, routeSection2));
            Mockito.when(routeSection1.getChoiceDataKey()).thenReturn("Key1");
            Mockito.when(routeSection2.getChoiceDataKey()).thenReturn("Key2");


            // Stubbing dataObject values
            Mockito.when(dataObject.getValue(Mockito.anyInt(), Mockito.eq("OptionName"), Mockito.eq("Key1"))).thenReturn(5.0);
            Mockito.when(dataObject.getValue(Mockito.anyInt(), Mockito.eq("OptionName"), Mockito.eq("Key2"))).thenReturn(3.0);
            // Instantiate the object containing calculateLengthOfRouteSection method
            ImageCollectionGenerator imageCollectionGenerator =
                    ImageCollectionGenerator.getImageCollectionGenerator(ExportType.CHOICE_OPTION);

            //Project project = new Project("TestProject", null, new DataObject(new SituationData[2]), new List<AbstractAttribute>, new List<ChoiceOption>,
            //imageCollectionGenerator.setUpImageCreation(project);

            // Call the method
            double result = imageCollectionGenerator.calculateLengthOfRouteSection(choiceOption, 1); // Assuming situationNumber is 1

            // Assert the result
            assertEquals(8.0, result);
        } catch (InvalidDataKeyException e) {
            fail();
        }
    }

    @Test
    void calculateLongestRouteSection() {

    }
}