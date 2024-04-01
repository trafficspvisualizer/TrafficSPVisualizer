package edu.kit.ifv.trafficspvisualizer.util.project;

import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class StandardProjectLoaderTest {
    @Test
    void testLoadProjectValidDirectory() throws IOException, ParseException {

        Project project = new StandardProjectLoader().loadProject(
                new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("TestProject")).getPath())
        );

        assertNotNull(project);

        // Name
        assertEquals(project.getName(), "TestProject");

        // Choice Options
        // 0
        assertEquals(project.getChoiceOptions().get(0).getName(), "car");
        assertEquals(project.getChoiceOptions().get(0).getTitle(), "Car");
        assertEquals(project.getChoiceOptions().get(0).getColor().toString(), "0x331a80ff");

        RouteSection routeSection00 = project.getChoiceOptions().get(0).getRouteSections().get(0);
        RouteSection routeSection01 = project.getChoiceOptions().get(0).getRouteSections().get(1);
        RouteSection routeSection02 = project.getChoiceOptions().get(0).getRouteSections().get(2);

        assertEquals(routeSection00.getIcon().getIdentifier(), 12);
        assertEquals(routeSection00.getLineType(), LineType.SOLID);
        assertEquals(routeSection00.getChoiceDataKey(), "access");

        assertEquals(routeSection01.getIcon().getIdentifier(), 3);
        assertEquals(routeSection01.getLineType(), LineType.SOLID);
        assertEquals(routeSection01.getChoiceDataKey(), "time");

        assertEquals(routeSection02.getIcon().getIdentifier(), 12);
        assertEquals(routeSection02.getLineType(), LineType.SOLID);
        assertEquals(routeSection02.getChoiceDataKey(), "leaving");

        // 1
        assertEquals(project.getChoiceOptions().get(1).getName(), "shuttleOnDemand");
        assertEquals(project.getChoiceOptions().get(1).getTitle(), "Shuttle On Demand");
        assertEquals(project.getChoiceOptions().get(1).getColor().toString(), "0x804d80ff");

        RouteSection routeSection10 = project.getChoiceOptions().get(1).getRouteSections().get(0);
        RouteSection routeSection11 = project.getChoiceOptions().get(1).getRouteSections().get(1);

        assertEquals(routeSection10.getIcon().getIdentifier(), 7);
        assertEquals(routeSection10.getLineType(), LineType.DASHED);
        assertEquals(routeSection10.getChoiceDataKey(), "waiting");

        assertEquals(routeSection11.getIcon().getIdentifier(), 2);
        assertEquals(routeSection11.getLineType(), LineType.SOLID);
        assertEquals(routeSection11.getChoiceDataKey(), "time");

        // 2
        assertEquals(project.getChoiceOptions().get(2).getName(), "shuttle");
        assertEquals(project.getChoiceOptions().get(2).getTitle(), "Shuttle");
        assertEquals(project.getChoiceOptions().get(2).getColor().toString(), "0xcccc33ff");

        RouteSection routeSection20 = project.getChoiceOptions().get(2).getRouteSections().get(0);
        RouteSection routeSection21 = project.getChoiceOptions().get(2).getRouteSections().get(1);

        assertEquals(routeSection20.getIcon().getIdentifier(), 12);
        assertEquals(routeSection20.getLineType(), LineType.DASHED);
        assertEquals(routeSection20.getChoiceDataKey(), "access");

        assertEquals(routeSection21.getIcon().getIdentifier(), 2);
        assertEquals(routeSection21.getLineType(), LineType.SOLID);
        assertEquals(routeSection21.getChoiceDataKey(), "time");

        // 3
        assertEquals(project.getChoiceOptions().get(3).getName(), "tram");
        assertEquals(project.getChoiceOptions().get(3).getTitle(), "Tram");
        assertEquals(project.getChoiceOptions().get(3).getColor().toString(), "0x4d8080ff");

        RouteSection routeSection30 = project.getChoiceOptions().get(3).getRouteSections().get(0);
        RouteSection routeSection31 = project.getChoiceOptions().get(3).getRouteSections().get(1);

        assertEquals(routeSection30.getIcon().getIdentifier(), 12);
        assertEquals(routeSection30.getLineType(), LineType.DASHED);
        assertEquals(routeSection30.getChoiceDataKey(), "access");

        assertEquals(routeSection31.getIcon().getIdentifier(), 11);
        assertEquals(routeSection31.getLineType(), LineType.SOLID);
        assertEquals(routeSection31.getChoiceDataKey(), "time");

        // 4
        assertEquals(project.getChoiceOptions().get(4).getName(), "bicycle");
        assertEquals(project.getChoiceOptions().get(4).getTitle(), "Bicycle");
        assertEquals(project.getChoiceOptions().get(4).getColor().toString(), "0xd81b60ff");

        RouteSection routeSection40 = project.getChoiceOptions().get(4).getRouteSections().get(0);

        assertEquals(routeSection40.getIcon().getIdentifier(), 1);
        assertEquals(routeSection40.getLineType(), LineType.SOLID);
        assertEquals(routeSection40.getChoiceDataKey(), "time");

        // 5
        assertEquals(project.getChoiceOptions().get(5).getName(), "walking");
        assertEquals(project.getChoiceOptions().get(5).getTitle(), "Walking");
        assertEquals(project.getChoiceOptions().get(5).getColor().toString(), "0x1e88e5ff");

        RouteSection routeSection50 = project.getChoiceOptions().get(5).getRouteSections().get(0);

        assertEquals(routeSection50.getIcon().getIdentifier(), 12);
        assertEquals(routeSection50.getLineType(), LineType.SOLID);
        assertEquals(routeSection50.getChoiceDataKey(), "time");

        // Attributes
        // 0
        assertInstanceOf(Attribute.class, project.getAbstractAttributes().get(0));
        Attribute attribute0 = (Attribute) project.getAbstractAttributes().get(0);
        assertEquals(attribute0.getDecimalPlaces(), 2);
        assertEquals(attribute0.getPrefix(), "");
        assertEquals(attribute0.getName(), "Cost");
        assertEquals(attribute0.getIcon().getIdentifier(), 6);
        assertEquals(attribute0.getSuffix(), "\u20ac");
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(0)), List.of("cost"));
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(1)), List.of("cost"));
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(2)), List.of("cost"));
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(3)), List.of("cost"));
        assertTrue(attribute0.isPermanentlyVisible());
        assertTrue(project.getAbstractAttributes().get(0).isActive());

        // 1 - separator line
        assertInstanceOf(SeparatorLine.class, project.getAbstractAttributes().get(1));
        assertTrue(project.getAbstractAttributes().get(1).isActive());

        // 2
        assertInstanceOf(Attribute.class, project.getAbstractAttributes().get(2));
        Attribute attribute2 = (Attribute) project.getAbstractAttributes().get(2);
        assertEquals(attribute2.getDecimalPlaces(), 0);
        assertEquals(attribute2.getPrefix(), "every ");
        assertEquals(attribute2.getName(), "Frequency");
        assertEquals(attribute2.getIcon().getIdentifier(), 5);
        assertEquals(attribute2.getSuffix(), " min");
        assertEquals(attribute2.getChoiceOptionMappings().get(project.getChoiceOptions().get(2)), List.of("frequency"));
        assertEquals(attribute2.getChoiceOptionMappings().get(project.getChoiceOptions().get(3)), List.of("frequency"));
        assertFalse(attribute2.isPermanentlyVisible());
        assertFalse(project.getAbstractAttributes().get(2).isActive());

        // Export Settings
        assertEquals(project.getExportSettings().getImageWidth(), 2000);
        assertEquals(project.getExportSettings().getExportType(), ExportType.SITUATION);
        assertEquals(project.getExportSettings().getHtmlVariableName(), "variable");
        assertEquals(project.getExportSettings().getImageHeight(), 400);
        assertEquals(project.getExportSettings().getFileFormat(), FileFormat.PNG);
        assertNull(project.getExportSettings().getExportPath());
    }

    @Test
    void testLoadProjectInvalidDirectory() {
        try {
            new StandardProjectLoader().loadProject(
                    new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("InvalidProject")).getPath())
            );
        } catch (IOException | ParseException e) {
            assertTrue(true);
            return;
        }

        fail();
    }
}