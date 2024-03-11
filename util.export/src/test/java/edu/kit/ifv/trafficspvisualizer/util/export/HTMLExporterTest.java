package edu.kit.ifv.trafficspvisualizer.util.export;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.data.InvalidDataKeyException;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.FileFormat;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;
import edu.kit.ifv.trafficspvisualizer.util.image.*;


import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HTMLExporterTest {

    @Test
    void testExportWithGeneratedSurveyImages() throws IOException, ParseException, InvalidDataKeyException {

        Project project = setupProject();

        ImageCollectionGenerator imageCollectionGenerator = new ChoiceOptionGenerator();

        SurveyImage[] imageArray =  imageCollectionGenerator.createImage(project);

        Exporter htmlExporter = new HTMLExporter();

        File exportFolderParent = project.getExportSettings().getExportPath().toFile();

        htmlExporter.export(imageArray, exportFolderParent,
                project.getName(), project.getExportSettings().getHtmlVariableName());


        assertEquals(Objects.requireNonNull(exportFolderParent.listFiles()).length, 1);
        assertTrue(Files.exists(exportFolderParent.toPath().resolve(project.getName() + "_export")));

        File exportFolder = exportFolderParent.toPath().resolve(project.getName() + "_export").toFile();

        // check situation directories count
        assertEquals(project.getDataObject().getSituationCount(), Objects.requireNonNull(exportFolder.listFiles()).length);

        // check naming of directories
        for (int i = 0; i < Objects.requireNonNull(exportFolder.listFiles()).length; i++) {
            boolean found = false;
            for (int j = 0; j < Objects.requireNonNull(exportFolder.listFiles()).length; j++) {
                if (Objects.requireNonNull(exportFolder.listFiles())[i].getName().equals(String.valueOf(j + 1))) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }

        // check file count, should be equal to choice option count + 1 (HTML file)
        for (File file : Objects.requireNonNull(exportFolder.listFiles())) {
            assertEquals(project.getChoiceOptions().size() + 1, Objects.requireNonNull(file.listFiles()).length);
        }

        // check naming scheme of images and html file
        // loops trough directories containing images
        for (int i = 0; i < Objects.requireNonNull(exportFolder.listFiles()).length; i++) {
            //loop trough images
            for (File file : Objects.requireNonNull(exportFolder.toPath().resolve(String.valueOf(i + 1)).toFile().listFiles())) {
                // loop trough possible names
                boolean foundName = false;

                for (ChoiceOption choiceOption : project.getChoiceOptions()) {
                    if (file.getName().equals(String.format("%s#c_%04d##c_%04d##c_%04d#.png", choiceOption.getTitle(), i + 1, project.getDataObject().getBlockNumber(i), project.getChoiceOptions().indexOf(choiceOption) + 1))
                                                || file.getName().equals(project.getName() + "_export_" + (i + 1) + ".html")) {
                        foundName = true;
                        break;
                    }
                }

                if (!foundName) assertEquals(file.getName(), "");
            }
        }
    }

    @Test
    void testExportWithTestSurveyImages() throws IOException {
        List<SurveyImage> imagesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                SurveyImage surveyImage = new SurveyImage(
                        "test" + i,
                        new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB),
                        i * 10,
                        i,
                        j);
                imagesList.add(surveyImage);
            }
        }

        testExport(imagesList);
    }

    @Test
    void testExportWithMocks() throws IOException {
        List<SurveyImage> imagesList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                SurveyImage surveyImage = mock(SurveyImage.class);
                when(surveyImage.title()).thenReturn("test" + i);
                when(surveyImage.image()).thenReturn(new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB));
                when(surveyImage.blockNumber()).thenReturn(10 * i);
                when(surveyImage.situationNumber()).thenReturn(i);
                when(surveyImage.choiceOptionNumber()).thenReturn(j);

                imagesList.add(surveyImage);
            }
        }

        testExport(imagesList);
    }

    private void testExport(List<SurveyImage> imagesList) throws IOException {
        SurveyImage[] images = imagesList.toArray(new SurveyImage[0]);

        File exportFolderParent = new File(String.valueOf(Files.createTempDirectory("ExportFolder")));
        String name = "test";

        HTMLExporter htmlExporter = new HTMLExporter();
        htmlExporter.export(images, exportFolderParent, name, "");

        assertEquals(Objects.requireNonNull(exportFolderParent.listFiles()).length, 1);
        assertTrue(Files.exists(exportFolderParent.toPath().resolve(name + "_export")));

        File exportFolder = exportFolderParent.toPath().resolve(name + "_export").toFile();

        // check situation directories count
        assertEquals(Objects.requireNonNull(exportFolder.listFiles()).length, 4);

        // check naming of directories
        for (int i = 0; i < Objects.requireNonNull(exportFolder.listFiles()).length; i++) {
            boolean found = false;
            for (int j = 0; j < Objects.requireNonNull(exportFolder.listFiles()).length; j++) {
                if (Objects.requireNonNull(exportFolder.listFiles())[i].getName().equals(String.valueOf(j+1))) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }

        // check image count
        for (File file : Objects.requireNonNull(exportFolder.listFiles())) {
            assertEquals(Objects.requireNonNull(file.listFiles()).length, 6);
        }

        // check naming scheme of images and html file
        // loops trough directories containing images
        for (int i = 0; i < Objects.requireNonNull(exportFolder.listFiles()).length; i++) {
            //loop trough images
            for (File file : Objects.requireNonNull(exportFolder.toPath().resolve(String.valueOf(i + 1)).toFile().listFiles())) {
                // loop trough possible names
                boolean foundName = false;
                for (int j = 0; j < 5; j++) {
                    if (file.getName().equals(String.format("%s#c_%04d##c_%04d##c_%04d#.png",name + i, i + 1, i * 10, j + 1))
                            || file.getName().equals(name + "_export_" + (i + 1) + ".html")) {
                        foundName = true;
                        break;
                    }
                }
                if (!foundName) assertEquals(file.getName(), "");
            }
        }
    }

    private Project setupProject() throws IOException, ParseException {
        // creating new project - choice option order is not deterministic due to HashMap
        Parser parser = new NGDParser();
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = parser.parse(ngdFile);
        Path projectPath = Files.createTempDirectory("ExportIntegrationTest");

        Project temp = new Project("test", projectPath, dataObject, ngdFile);

        // creating project with same values as before but with alphabetically sorted choice options
        Comparator<ChoiceOption> comparator = Comparator.comparing(ChoiceOption::getName);
        List<ChoiceOption> choiceOptions = temp.getChoiceOptions().stream().sorted(comparator).collect(Collectors.toList());
        Project project = new Project(temp.getName(), temp.getProjectPath(), temp.getDataObject(),
                temp.getAbstractAttributes(), choiceOptions,
                temp.getExportSettings(), null, ngdFile);

        // adding abstract attributes
        Attribute attribute = new Attribute(project.getIconManager().getDefaultIcon());
        SeparatorLine separatorLine = new SeparatorLine();

        attribute.setName("TestAttribute");
        attribute.setPrefix("testPrefix");
        attribute.setSuffix("testSuffix");
        attribute.setDecimalPlaces(2);
        attribute.setPermanentlyVisible(true);
        // "car" is first in alphabetical list
        attribute.setMapping(project.getChoiceOptions().getFirst(), List.of("cost_car"));

        separatorLine.setActive(false);

        project.addAbstractAttribute(attribute);
        project.addAbstractAttribute(separatorLine);

        // editing choice options - "car" is first in alphabetical list
        project.getChoiceOptions().getFirst().setTitle("ChoiceOptionTestTitle");
        project.getChoiceOptions().getFirst().setColor(Color.BLUE);
        project.swapChoiceOptionDown(0);
        project.getChoiceOptions().getFirst().addRouteSection(
                new RouteSection(project.getIconManager().getDefaultIcon(), "zugang", LineType.DASHED));

        for (int i = 0; i < project.getChoiceOptions().size(); i++) {
            project.getChoiceOptions().get(i).setColor(new Color(0.01 * i, 0.01 * i, 0.01 * i, 0.01 * i));
        }

        // export Settings
        Path exportPath = Files.createTempDirectory("ExportIntegrationTestFolder");
        project.setExportSettings(new ExportSettings(
                100, 200, exportPath, FileFormat.PNG, ExportType.CHOICE_OPTION, "testVar")
        );

        return project;
    }
}
