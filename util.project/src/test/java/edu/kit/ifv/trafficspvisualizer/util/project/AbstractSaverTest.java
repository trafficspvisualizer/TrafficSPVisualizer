package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.settings.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import javafx.scene.paint.Color;
import org.json.JSONObject;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractSaverTest {
    @Test
    void testCreateJsonProject() throws IOException, ParseException {
        // creating new project - choice option order is not deterministic due to HashMap
        Parser parser = new NGDParser();
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("example.ngd")).getPath());
        DataObject dataObject = parser.parse(ngdFile);
        Path projectPath = Files.createTempDirectory("AbstractSaverTestProjectFolder");
        Comparator<ChoiceOption> comparator = Comparator.comparing(ChoiceOption::getName);
        Project temp = new Project("test", projectPath, dataObject, ngdFile);

        // creating project with same values as before but with alphabetically sorted choice options
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
        // "bicycle" is first in alphabetical list
        attribute.setMapping(project.getChoiceOptions().getFirst(), List.of("time"));

        separatorLine.setActive(false);

        project.addAbstractAttribute(attribute);
        project.addAbstractAttribute(separatorLine);

        // editing choice options - "bicycle" is first in alphabetical list
        project.getChoiceOptions().getFirst().setTitle("ChoiceOptionTestTitle");
        project.getChoiceOptions().getFirst().setColor(Color.BLUE);
        project.swapChoiceOptionDown(0);
        project.getChoiceOptions().getFirst().addRouteSection(
                new RouteSection(project.getIconManager().getDefaultIcon(), "time", LineType.SOLID));

        for (int i = 0; i < project.getChoiceOptions().size(); i++) {
            project.getChoiceOptions().get(i).setColor(new Color(0.01 * i, 0.01 * i, 0.01 * i, 0.01 * i));
        }

        // export Settings
        Path exportPath = Files.createTempDirectory("AbstractSaverTestExportFolder");
        project.setExportSettings(new ExportSettings(
                100, 200, exportPath, FileFormat.PNG, ExportType.CHOICE_OPTION, "testVar")
        );

        // save
        AbstractSaver saver = new StandardProjectSaver();
        JSONObject jsonObject = saver.createJsonProject(
                project.getName(), project.getAbstractAttributes(), project.getExportSettings(), project.getChoiceOptions()
        );

        final String expectedJsonString = "{\"exportSettings\":{\"imageWidth\":200,\"exportType\":\"" +
                "ChoiceOption\",\"HtmlVariable\":\"testVar\",\"imageHeight\":100,\"fileFormat\":\"PNG\"}," +
                "\"ChoiceOptions\":[{\"ChoiceOption\":{\"routeSections\":[{\"lineType\":\"solid\",\"icon\":0," +
                "\"choiceDataKey\":\"time\"}],\"color\":\"0x00000000\",\"name\":\"car\",\"title\":\"car\"}}," +
                "{\"ChoiceOption\":{\"routeSections\":[],\"color\":\"0x03030303\",\"name\":\"bicycle\"," +
                "\"title\":\"ChoiceOptionTestTitle\"}},{\"ChoiceOption\":{\"routeSections\":[]," +
                "\"color\":\"0x05050505\",\"name\":\"shuttle\",\"title\":\"shuttle\"}}," +
                "{\"ChoiceOption\":{\"routeSections\":[],\"color\":\"0x08080808\",\"name\":\"shuttleOnDemand\"," +
                "\"title\":\"shuttleOnDemand\"}},{\"ChoiceOption\":{\"routeSections\":[],\"color\":\"0x0a0a0a0a\"," +
                "\"name\":\"tram\",\"title\":\"tram\"}},{\"ChoiceOption\":{\"routeSections\":[]," +
                "\"color\":\"0x0d0d0d0d\",\"name\":\"walking\",\"title\":\"walking\"}}],\"name\":\"test\"," +
                "\"attributes\":[{\"Attribute\":{\"Active\":true,\"decimalPlaces\":2,\"prefix\":\"testPrefix\"," +
                "\"name\":\"TestAttribute\",\"icon\":0,\"suffix\":\"testSuffix\",\"choiceOptionMappings\":[{\"List\":" +
                "[\"time\"],\"ChoiceOption\":{\"routeSections\":[],\"color\":\"0x03030303\",\"name\":\"bicycle\"," +
                "\"title\":\"ChoiceOptionTestTitle\"}}],\"permanentlyVisible\":true}},{\"LineSeparator\":false}]}";


        assertEquals(expectedJsonString, jsonObject.toString());
    }
}