package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SituationGeneratorTest {
    private static final int CHOICE_OPTION_WIDTH = 1920;
    private static final int CHOICE_OPTION_HEIGHT = 270;
    private SituationGenerator situationGenerator;
    private Project project;
    private int numberOfSituations;
    private int numberOfChoiceOptions;


    @BeforeEach
    void setUp() throws IOException, ParseException {
        situationGenerator = new SituationGenerator();
        numberOfSituations = 16;
        numberOfChoiceOptions = 6;
        Path projectFolderParentDirectory = Files.createTempDirectory("StandardImageGeneratorTest");
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = new NGDParser().parse(ngdFile);
        project = new Project("Test", projectFolderParentDirectory, dataObject, ngdFile);
    }

    @Test
    void createImage() throws InvalidDataKeyException {
        ChoiceOptionImage[] result = situationGenerator.createImage(project);

        assertNotNull(result);
        assertEquals(numberOfSituations, result.length);
        BufferedImage testImage = result[0].image();
        assertEquals(CHOICE_OPTION_HEIGHT * numberOfChoiceOptions, testImage.getHeight());
        assertEquals(CHOICE_OPTION_WIDTH , testImage.getWidth());
    }

    @Test
    void createPreviewImage() throws InvalidDataKeyException {
        BufferedImage result = situationGenerator.createPreviewImage(project);

        assertNotNull(result);
        assertEquals(CHOICE_OPTION_WIDTH, result.getWidth());
        assertEquals(CHOICE_OPTION_HEIGHT * numberOfChoiceOptions, result.getHeight());
    }
}