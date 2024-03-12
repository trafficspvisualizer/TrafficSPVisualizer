package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.FileFormat;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.model.settings.SeparatorLine;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class SaverAndLoaderTest {

    @Test
    void testSaveAndLoadProject() throws IOException, ParseException {

        // creating new project - choice option order is not deterministic due to HashMap
        Parser parser = new NGDParser();
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = parser.parse(ngdFile);
        Path projectPath = Files.createTempDirectory("SaverAndLoaderTestFolder");
        Comparator<ChoiceOption> comparator = Comparator.comparing(ChoiceOption::getName);
        Project temp = new Project("Test", projectPath, dataObject, ngdFile);

        // creating project with same values as before but with alphabetically sorted choice options
        List<ChoiceOption> choiceOptions = temp.getChoiceOptions().stream().sorted(comparator).collect(Collectors.toList());
        Project preSave = new Project(temp.getName(), temp.getProjectPath(), temp.getDataObject(),
                temp.getAbstractAttributes(), choiceOptions,
                temp.getExportSettings(), null, ngdFile);


        // Configuring project
        // Choice Option
        preSave.getChoiceOptions().getFirst().setTitle("CO1");
        preSave.getChoiceOptions().getFirst().setColor(Color.BLUE);

        // Route Sections
        preSave.getChoiceOptions().getFirst().addRouteSection(
                new RouteSection(preSave.getIconManager().getDefaultIcon(), "fz_miv", LineType.DASHED)
        );

        preSave.getChoiceOptions().getFirst().addRouteSection(
                new RouteSection(preSave.getIconManager().getDefaultIcon(), "cost_car", LineType.SOLID)
        );

        // Attributes

        preSave.addAbstractAttribute(
                new Attribute("A1", preSave.getIconManager().getDefaultIcon(), "", "testSuffix", false, 0)
        );

        preSave.addAbstractAttribute(new SeparatorLine());

        preSave.addAbstractAttribute(
                new Attribute("A2", preSave.getIconManager().getDefaultIcon(), "testPrefix", "", true, 2)
        );

        // Export Settings
        preSave.setExportSettings(
                new ExportSettings(100, 200, Files.createTempDirectory("test"), FileFormat.PNG, ExportType.CHOICE_OPTION, "testHtmlVar")
        );


        // Save and Load
        StandardProjectSaver standardProjectSaver = new StandardProjectSaver();
        standardProjectSaver.saveProject(preSave, preSave.getProjectPath());

        StandardProjectLoader standardProjectLoader = new StandardProjectLoader();
        Project postLoad = standardProjectLoader.loadProject(preSave.getProjectPath().resolve(preSave.getName()).toFile());

        // Comparison

        // Choice Option
        assertEquals(preSave.getChoiceOptions().getFirst().getColor(), postLoad.getChoiceOptions().getFirst().getColor());
        assertEquals(preSave.getChoiceOptions().getFirst().getTitle(), postLoad.getChoiceOptions().getFirst().getTitle());
        assertEquals(preSave.getChoiceOptions().getFirst().getName(), postLoad.getChoiceOptions().getFirst().getName());
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().size(), postLoad.getChoiceOptions().getFirst().getRouteSections().size());

        //Route Sections
        // 0
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().getFirst().getChoiceDataKey(),
                postLoad.getChoiceOptions().getFirst().getRouteSections().getFirst().getChoiceDataKey());
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().getFirst().getLineType(),
                postLoad.getChoiceOptions().getFirst().getRouteSections().getFirst().getLineType());
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().getFirst().getIcon().getIdentifier(),
                postLoad.getChoiceOptions().getFirst().getRouteSections().getFirst().getIcon().getIdentifier());
        // 1
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().get(1).getChoiceDataKey(),
                postLoad.getChoiceOptions().getFirst().getRouteSections().get(1).getChoiceDataKey());
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().get(1).getLineType(),
                postLoad.getChoiceOptions().getFirst().getRouteSections().get(1).getLineType());
        assertEquals(preSave.getChoiceOptions().getFirst().getRouteSections().get(1).getIcon().getIdentifier(),
                postLoad.getChoiceOptions().getFirst().getRouteSections().get(1).getIcon().getIdentifier());

        //Attributes
        assertEquals(preSave.getAbstractAttributes().size(), postLoad.getAbstractAttributes().size());

        // 0
        Attribute preSaveAttribute0 = (Attribute) preSave.getAbstractAttributes().getFirst();
        Attribute postLoadAttribute0 = (Attribute) postLoad.getAbstractAttributes().getFirst();
        assertEquals(preSaveAttribute0.getName(), postLoadAttribute0.getName());
        assertEquals(preSaveAttribute0.getPrefix(), postLoadAttribute0.getPrefix());
        assertEquals(preSaveAttribute0.getSuffix(), postLoadAttribute0.getSuffix());
        assertEquals(preSaveAttribute0.getIcon().getIdentifier(), postLoadAttribute0.getIcon().getIdentifier());
        assertEquals(preSaveAttribute0.isPermanentlyVisible(), postLoadAttribute0.isPermanentlyVisible());
        assertEquals(preSaveAttribute0.getDecimalPlaces(), postLoadAttribute0.getDecimalPlaces());

        // 1
        SeparatorLine preSaveSeparatorLine = (SeparatorLine) preSave.getAbstractAttributes().get(1);
        SeparatorLine postLoadSeparatorLine = (SeparatorLine) postLoad.getAbstractAttributes().get(1);
        assertInstanceOf(SeparatorLine.class, preSaveSeparatorLine);
        assertInstanceOf(SeparatorLine.class, postLoadSeparatorLine);
        assertEquals(preSaveSeparatorLine.hasValues(), postLoadSeparatorLine.hasValues());

        // 2
        Attribute preSaveAttribute2 = (Attribute) preSave.getAbstractAttributes().getFirst();
        Attribute postLoadAttribute2 = (Attribute) postLoad.getAbstractAttributes().getFirst();
        assertEquals(preSaveAttribute2.getName(), postLoadAttribute2.getName());
        assertEquals(preSaveAttribute2.getPrefix(), postLoadAttribute2.getPrefix());
        assertEquals(preSaveAttribute2.getSuffix(), postLoadAttribute2.getSuffix());
        assertEquals(preSaveAttribute2.getIcon().getIdentifier(), postLoadAttribute2.getIcon().getIdentifier());
        assertEquals(preSaveAttribute2.isPermanentlyVisible(), postLoadAttribute2.isPermanentlyVisible());
        assertEquals(preSaveAttribute2.getDecimalPlaces(), postLoadAttribute2.getDecimalPlaces());

        // Export Settings
        assertEquals(preSave.getExportSettings().getImageHeight(), postLoad.getExportSettings().getImageHeight());
        assertEquals(preSave.getExportSettings().getImageWidth(), postLoad.getExportSettings().getImageWidth());
        assertEquals(preSave.getExportSettings().getFileFormat(), postLoad.getExportSettings().getFileFormat());
        assertEquals(preSave.getExportSettings().getExportType(), postLoad.getExportSettings().getExportType());
        assertEquals(preSave.getExportSettings().getHtmlVariableName(), postLoad.getExportSettings().getHtmlVariableName());
        assertNull(postLoad.getExportSettings().getExportPath());
    }
}
