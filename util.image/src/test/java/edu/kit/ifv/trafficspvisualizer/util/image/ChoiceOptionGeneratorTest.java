package edu.kit.ifv.trafficspvisualizer.util.image;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ChoiceOptionGeneratorTest {

    @Test
    public void testCreateImage() throws InvalidDataKeyException, IOException, ParseException {
        ChoiceOptionGenerator choiceOptionGenerator = new ChoiceOptionGenerator();
        int numberOfSituations = 16;
        int numberOfChoiceOptions = 6;

        Path projectFolderParentDirectory = Files.createTempDirectory("StandardImageGeneratorTest");
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = new NGDParser().parse(ngdFile);
        Project project = new Project("Test", projectFolderParentDirectory, dataObject, ngdFile);

        SurveyImage[] result = choiceOptionGenerator.createImage(project);

        assertNotNull(result);
        assertEquals(numberOfChoiceOptions * numberOfSituations, result.length);
    }
}