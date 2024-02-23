package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ImageCollectionGeneratorTest {
    @Test
    void getImageCollectionGenerator() {
        assertInstanceOf(SituationGenerator.class, ImageCollectionGenerator.getImageCollectionGenerator(ExportType.SITUATION));
        assertInstanceOf(ChoiceOptionGenerator.class, ImageCollectionGenerator.getImageCollectionGenerator(ExportType.CHOICE_OPTION));
        assertInstanceOf(ChoiceOptionGenerator.class, ImageCollectionGenerator.getImageCollectionGenerator(ExportType.HTML));
    }
}