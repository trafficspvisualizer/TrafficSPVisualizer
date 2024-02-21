package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.model.settings.*;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.util.parse.Parser;
import javafx.scene.paint.Color;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class AbstractSaverTest {

    @Test
    void testCreateJsonProject() throws IOException, ParseException {
        Parser parser = new NGDParser();
        File ngdFile = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("Beispiel_ngene.ngd")).getPath());
        DataObject dataObject = parser.parse(ngdFile);
        Path projectPath = Files.createTempDirectory("AbstractSaverTestProjectFolder");
        Project project = new Project("test", projectPath, dataObject, ngdFile);

        // adding abstract attributes
        Attribute attribute = new Attribute(project.getIconManager().getDefaultIcon());
        SeparatorLine separatorLine = new SeparatorLine();

        attribute.setName("TestAttribute");
        attribute.setPrefix("testPrefix");
        attribute.setSuffix("testSuffix");
        attribute.setDecimalPlaces(2);
        attribute.setPermanentlyVisible(true);
        attribute.setMapping(project.getChoiceOptions().getFirst(), List.of("fz_fuss"));

        separatorLine.setActive(false);

        project.addAbstractAttribute(attribute);
        project.addAbstractAttribute(separatorLine);

        // editing choice options
        project.getChoiceOptions().getFirst().setTitle("ChoiceOptionTestTitle");
        project.getChoiceOptions().getFirst().setColor(Color.BLUE);
        project.swapChoiceOptionDown(0);
        //assuming first is "fuss"
        project.getChoiceOptions().getFirst().addRouteSection(
                new RouteSection(project.getIconManager().getDefaultIcon(), "fz_fuss", LineType.DASHED));

        // export Settings
        Path exportPath = Files.createTempDirectory("AbstractSaverTestExportFolder");
        project.setExportSettings(new ExportSettings(
                100, 200, exportPath, FileFormat.PNG, ExportType.CHOICE_OPTION, "testVar")
        );

        AbstractSaver saver = new StandardProjectSaver();
        JSONObject jsonObject = saver.createJsonProject(
                project.getName(), project.getAbstractAttributes(), project.getExportSettings(), project.getChoiceOptions()
        );

        // test abstract attributes
        JSONArray attributesJsonArray = jsonObject.getJSONArray(JsonKeys.KEY_ATTRIBUTES.getKey());

        JSONObject attributeJsonObject = attributesJsonArray.getJSONObject(0).getJSONObject(JsonKeys.KEY_ATTRIBUTE.getKey());
        assertEquals("TestAttribute", attributeJsonObject.getString(JsonKeys.KEY_NAME.getKey()));
        assertEquals("testPrefix", attributeJsonObject.getString(JsonKeys.KEY_PREFIX.getKey()));
        assertEquals("testSuffix", attributeJsonObject.getString(JsonKeys.KEY_SUFFIX.getKey()));
        assertEquals(2, attributeJsonObject.getInt(JsonKeys.KEY_DECIMAL_PLACES.getKey()));
        assertTrue(attributeJsonObject.getBoolean(JsonKeys.KEY_PERMANENTLY_VISIBLE.getKey()));

        //TODO: finish assertions

    }
}