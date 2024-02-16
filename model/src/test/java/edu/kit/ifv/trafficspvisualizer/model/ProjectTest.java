package edu.kit.ifv.trafficspvisualizer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectTest {
    static final int SITUATION_COUNT = 10;
    static Project project;
    static Path testPath;
    static ExportSettings exportSettings;
    static IconManager iconManager;
    static DataObject dataObject;
    static List<AbstractAttribute> attributes;
    static File testNGD;

    @BeforeEach
    void setup() throws IOException {
        testPath = Paths.get("test");
        exportSettings = mock(ExportSettings.class);
        iconManager = mock(IconManager.class);
        dataObject = mock(DataObject.class);
        when(dataObject.getChoiceNames(0)).thenReturn(Set.of("testChoice1", "testChoice2"));
        when(dataObject.getSituationCount()).thenReturn(SITUATION_COUNT);
        AbstractAttribute testAttribute = mock(AbstractAttribute.class);
        when(testAttribute.hasValues()).thenReturn(true);
        attributes = List.of(testAttribute);
        testNGD = new File(
            Objects.requireNonNull(ProjectTest.class.getResource("/test.ngd")).getFile()
        );


        project = new Project(
            "test",
            testPath,
            dataObject,
            attributes,
            new ArrayList<>(),
            exportSettings,
            null,
            testNGD
            );
    }

    @Test
    void testConstructor() {
        assertEquals("test", project.getName());
        assertEquals(testPath, project.getProjectPath());
        assertEquals(dataObject, project.getDataObject());
        assertEquals(attributes, project.getAbstractAttributes());
        assertEquals(
            Set.of(new ChoiceOption("testChoice1"), new ChoiceOption("testChoice2")),
            new HashSet<>((project.getChoiceOptions()))
        );
        assertEquals(exportSettings, project.getExportSettings());
        assertNotNull(project.getIconManager());
        assertTrue(project.getCacheDirectory().getFileName().toString().contains(project.getName()));
        assertNotNull(project.getCacheDirectory().resolve(testNGD.toPath()));
    }

    @Test
    void testCurrentPreview() {
        int current = project.getCurrentPreviewSituation();
        project.incrementPreview();
        assertEquals(current + 1, project.getCurrentPreviewSituation());
        project.decrementPreview();
        assertEquals(current, project.getCurrentPreviewSituation());
        project.decrementPreview();
        assertEquals(project.getDataObject().getSituationCount() - 1, project.getCurrentPreviewSituation());
        project.incrementPreview();
        assertEquals(current, project.getCurrentPreviewSituation());
    }

    @Test
    void testGetAndSetExportSettings() {
        ExportSettings oldSettings = project.getExportSettings();
        ExportSettings newSettings = mock(ExportSettings.class);
        project.setExportSettings(newSettings);
        assertNotEquals(oldSettings, project.getExportSettings());
        assertEquals(newSettings, project.getExportSettings());
    }

    @Test
    void testAttributes() {
        project.removeAbstractAttribute(0);
        assertTrue(project.getAbstractAttributes().isEmpty());
        Attribute attribute = new Attribute(mock(Icon.class));
        SeparatorLine separatorLine = new SeparatorLine();

        project.addAbstractAttribute(attribute);
        project.addAbstractAttribute(separatorLine);

        assertEquals(List.of(attribute), project.getAttributes());
        assertEquals(attribute, project.getAbstractAttributes().getFirst());

        project.swapAttributeDown(0);
        assertEquals(attribute, project.getAbstractAttributes().get(1));
        project.swapAttributeUp(1);
        assertEquals(attribute, project.getAbstractAttributes().getFirst());
    }

    @Test
    void testChoiceOptions() {
        ChoiceOption first = project.getChoiceOptions().getFirst();
        project.swapChoiceOptionDown(0);
        assertEquals(first, project.getChoiceOptions().get(1));
        project.swapChoiceOptionUp(1);
        assertEquals(first, project.getChoiceOptions().getFirst());

        assertFalse(project.swapChoiceOptionUp(-1));
    }
}