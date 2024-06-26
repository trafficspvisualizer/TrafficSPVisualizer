package edu.kit.ifv.trafficspvisualizer.model.settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExportSettingsTest {
    private ExportSettings exportSettings;
    private final int testHeight = 203;
    private final int testWidth = 5001;
    private final Path testPath = Path.of("path/test");
    private final FileFormat testFormat = FileFormat.PNG;
    private final ExportType testType = ExportType.HTML;

    @BeforeEach
    void setup() {
        exportSettings = new ExportSettings(testHeight, testWidth, testPath, testFormat, testType, "");
    }

    @Test
    void testDefaultValues() {
        ExportSettings settings = new ExportSettings(Path.of("test"));
        assertEquals(270, settings.getImageHeight());
        assertEquals(1920, settings.getImageWidth());
        assertEquals(ExportType.CHOICE_OPTION, settings.getExportType());
        assertEquals(FileFormat.PNG, settings.getFileFormat());
    }

    @Test
    void testGetAndSetImageHeight() {
        assertEquals(testHeight, exportSettings.getImageHeight());
        int newHeight = 1000;
        exportSettings.setImageHeight(newHeight);
        assertEquals(newHeight, exportSettings.getImageHeight());
    }

    @Test
    void testGetAndSetImageWidth() {
        assertEquals(testWidth, exportSettings.getImageWidth());
        int newWidth = 1203;
        exportSettings.setImageWidth(newWidth);
        assertEquals(newWidth, exportSettings.getImageWidth());
    }

    @Test
    void testGetAndSetExportPath() {
        assertEquals(testPath, exportSettings.getExportPath());
        Path path = Path.of("new/path");
        exportSettings.setExportPath(path);
        assertEquals(path, exportSettings.getExportPath());
    }

    @Test
    void testGetAndSetFileFormat() {
        assertEquals(FileFormat.PNG, exportSettings.getFileFormat());
        FileFormat newFormat = mock();
        exportSettings.setFileFormat(newFormat);
        assertNotEquals(FileFormat.PNG, exportSettings.getFileFormat());
    }

    @Test
    void testGetAndSetExportType() {
        assertEquals(ExportType.HTML, exportSettings.getExportType());
        ExportType newType = ExportType.SITUATION;
        exportSettings.setExportType(newType);
        assertEquals(newType, exportSettings.getExportType());
    }
}