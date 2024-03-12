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
        assertEquals(project.getChoiceOptions().get(0).getName(), "oev_fuss");
        assertEquals(project.getChoiceOptions().get(0).getTitle(), "Walking + Public Transit");
        assertEquals(project.getChoiceOptions().get(0).getColor().toString(), "0xff9966ff");

        RouteSection routeSection00 = project.getChoiceOptions().get(0).getRouteSections().get(0);
        RouteSection routeSection01 = project.getChoiceOptions().get(0).getRouteSections().get(1);

        assertEquals(routeSection00.getIcon().getIdentifier(), 12);
        assertEquals(routeSection00.getLineType(), LineType.DASHED);
        assertEquals(routeSection00.getChoiceDataKey(), "zugang_oevfuss");

        assertEquals(routeSection01.getIcon().getIdentifier(), 10);
        assertEquals(routeSection01.getLineType(), LineType.SOLID);
        assertEquals(routeSection01.getChoiceDataKey(), "fz_oev");

        // 1
        assertEquals(project.getChoiceOptions().get(1).getName(), "rad");
        assertEquals(project.getChoiceOptions().get(1).getTitle(), "Bike");
        assertEquals(project.getChoiceOptions().get(1).getColor().toString(), "0x80b380ff");
        RouteSection routeSection10 = project.getChoiceOptions().get(1).getRouteSections().get(0);

        assertEquals(routeSection10.getIcon().getIdentifier(), 1);
        assertEquals(routeSection10.getLineType(), LineType.SOLID);
        assertEquals(routeSection10.getChoiceDataKey(), "fz_rad");

        // 2
        assertEquals(project.getChoiceOptions().get(2).getName(), "car");
        assertEquals(project.getChoiceOptions().get(2).getTitle(), "Car");
        assertEquals(project.getChoiceOptions().get(2).getColor().toString(), "0xffc107ff");

        RouteSection routeSection20 = project.getChoiceOptions().get(2).getRouteSections().get(0);
        RouteSection routeSection21 = project.getChoiceOptions().get(2).getRouteSections().get(1);
        RouteSection routeSection22 = project.getChoiceOptions().get(2).getRouteSections().get(2);

        assertEquals(routeSection20.getIcon().getIdentifier(), 12);
        assertEquals(routeSection20.getLineType(), LineType.DASHED);
        assertEquals(routeSection20.getChoiceDataKey(), "zugang");

        assertEquals(routeSection21.getIcon().getIdentifier(), 3);
        assertEquals(routeSection21.getLineType(), LineType.SOLID);
        assertEquals(routeSection21.getChoiceDataKey(), "fz_miv");

        assertEquals(routeSection22.getIcon().getIdentifier(), 12);
        assertEquals(routeSection22.getLineType(), LineType.DASHED);
        assertEquals(routeSection22.getChoiceDataKey(), "abgang");

        // 3
        assertEquals(project.getChoiceOptions().get(3).getName(), "fuss");
        assertEquals(project.getChoiceOptions().get(3).getTitle(), "Walking");
        assertEquals(project.getChoiceOptions().get(3).getColor().toString(), "0xfa47faff");

        RouteSection routeSection30 = project.getChoiceOptions().get(3).getRouteSections().get(0);

        assertEquals(routeSection30.getIcon().getIdentifier(), 12);
        assertEquals(routeSection30.getLineType(), LineType.SOLID);
        assertEquals(routeSection30.getChoiceDataKey(), "fz_fuss");

        // 4
        assertEquals(project.getChoiceOptions().get(4).getName(), "shuttle_od");
        assertEquals(project.getChoiceOptions().get(4).getTitle(), "Shuttle");
        assertEquals(project.getChoiceOptions().get(4).getColor().toString(), "0xd81b60ff");

        RouteSection routeSection40 = project.getChoiceOptions().get(4).getRouteSections().get(0);

        assertEquals(routeSection40.getIcon().getIdentifier(), 2);
        assertEquals(routeSection40.getLineType(), LineType.SOLID);
        assertEquals(routeSection40.getChoiceDataKey(), "fz_oev");

        // 5
        assertEquals(project.getChoiceOptions().get(5).getName(), "shuttle_tb");
        assertEquals(project.getChoiceOptions().get(5).getTitle(), "Walking + Shuttle");
        assertEquals(project.getChoiceOptions().get(5).getColor().toString(), "0x1e88e5ff");

        RouteSection routeSection50 = project.getChoiceOptions().get(5).getRouteSections().get(0);
        RouteSection routeSection51 = project.getChoiceOptions().get(5).getRouteSections().get(1);

        assertEquals(routeSection50.getIcon().getIdentifier(), 12);
        assertEquals(routeSection50.getLineType(), LineType.DASHED);
        assertEquals(routeSection50.getChoiceDataKey(), "zugang_oevfuss");

        assertEquals(routeSection51.getIcon().getIdentifier(), 2);
        assertEquals(routeSection51.getLineType(), LineType.SOLID);
        assertEquals(routeSection51.getChoiceDataKey(), "fz_oev");


        // Attributes
        // 0
        assertInstanceOf(Attribute.class, project.getAbstractAttributes().get(0));
        Attribute attribute0 = (Attribute) project.getAbstractAttributes().get(0);
        assertEquals(attribute0.getDecimalPlaces(), 2);
        assertEquals(attribute0.getPrefix(), "");
        assertEquals(attribute0.getName(), "Cost");
        assertEquals(attribute0.getIcon().getIdentifier(), 6);
        assertEquals(attribute0.getSuffix(), " $");
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(5)), List.of("cost_oev"));
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(2)), List.of("cost_car"));
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(4)), List.of("cost_oev"));
        assertEquals(attribute0.getChoiceOptionMappings().get(project.getChoiceOptions().get(0)), List.of("cost_oev"));
        assertTrue(attribute0.isPermanentlyVisible());
        assertTrue(project.getAbstractAttributes().get(0).isActive());

        // 1 - separator line
        assertInstanceOf(SeparatorLine.class, project.getAbstractAttributes().get(1));
        assertFalse(project.getAbstractAttributes().get(1).isActive());

        // 2
        assertInstanceOf(Attribute.class, project.getAbstractAttributes().get(2));
        Attribute attribute2 = (Attribute) project.getAbstractAttributes().get(2);
        assertEquals(attribute2.getDecimalPlaces(), 0);
        assertEquals(attribute2.getPrefix(), "every ");
        assertEquals(attribute2.getName(), "Frequency");
        assertEquals(attribute2.getIcon().getIdentifier(), 5);
        assertEquals(attribute2.getSuffix(), " min");
        assertEquals(attribute2.getChoiceOptionMappings().get(project.getChoiceOptions().get(0)), List.of("freq_oev"));
        assertEquals(attribute2.getChoiceOptionMappings().get(project.getChoiceOptions().get(5)), List.of("freq_oev"));
        assertFalse(attribute2.isPermanentlyVisible());
        assertFalse(project.getAbstractAttributes().get(2).isActive());

        // 3
        assertInstanceOf(Attribute.class, project.getAbstractAttributes().get(3));
        Attribute attribute3 = (Attribute) project.getAbstractAttributes().get(3);
        assertEquals(attribute3.getDecimalPlaces(), 0);
        assertEquals(attribute3.getPrefix(), "");
        assertEquals(attribute3.getName(), "Waiting Time");
        assertEquals(attribute3.getIcon().getIdentifier(), 7);
        assertEquals(attribute3.getSuffix(), " min");
        assertEquals(attribute3.getChoiceOptionMappings().get(project.getChoiceOptions().get(4)), List.of("warten"));
        assertFalse(attribute3.isPermanentlyVisible());
        assertTrue(project.getAbstractAttributes().get(3).isActive());

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