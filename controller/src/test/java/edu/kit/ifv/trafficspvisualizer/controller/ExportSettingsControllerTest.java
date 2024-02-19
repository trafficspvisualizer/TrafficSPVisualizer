package edu.kit.ifv.trafficspvisualizer.controller;


import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.FileFormat;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.ExportSettingsStage;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class ExportSettingsControllerTest {

    private ControllerFacade controllerFacadeMock;
    private ViewFacade viewFacadeMock;
    private ExportSettingsController exportSettingsController;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }
    @BeforeEach
    public void setUp() {
        Platform.runLater(() -> {
            controllerFacadeMock = mock(ControllerFacade.class);
            viewFacadeMock = mock(ViewFacade.class);

            when(controllerFacadeMock.getViewFacade()).thenReturn(viewFacadeMock);

            exportSettingsController = new ExportSettingsController(controllerFacadeMock);
        });
    }

    @Test
    public void testActionOnExportFolderButton() {
        Platform.runLater(() -> {
        // Mock the ExportSettingsStage
        ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
        when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);

        // Simulate user selecting a directory
        File selectedDirectory = new File("testDirectory");
        when(exportSettingsStageMock.showDirectoryChooserDialog()).thenReturn(selectedDirectory);

        // Perform action on export folder button
        try {
            Method method = exportSettingsController.getClass().getMethod("actionOnExportFolderButton");
            method.setAccessible(true);
            method.invoke(exportSettingsController);
        } catch (Exception e) {
            fail();
        }

        // Verify that the export directory is set correctly
        verify(exportSettingsStageMock).setExportDirectory(selectedDirectory);
    });
    }
    @Test
    public void testActionOnExportFolderButtonNull() {
        Platform.runLater(() -> {
            // Mock the ExportSettingsStage
            ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
            when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);

            when(exportSettingsStageMock.showDirectoryChooserDialog()).thenReturn(null);

            // Perform action on export folder button
            try {
                Method method = exportSettingsController.getClass().getMethod("actionOnExportFolderButton");
                method.setAccessible(true);
                method.invoke(exportSettingsController);
            } catch (Exception e) {
                fail();
            }

            // Verify that the export directory is not set
            verify(exportSettingsStageMock, times(0)).setExportDirectory(null);
        });
    }

    @Test
    public void testActionOnSaveButton() {
        // Mock the ExportSettingsStage
        Platform.runLater(() -> {
            ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
            when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);


        // Set up mock data
        when(exportSettingsStageMock.getHeightString()).thenReturn("100");
        when(exportSettingsStageMock.getWidthString()).thenReturn("200");
        when(exportSettingsStageMock.getExportDirectory()).thenReturn(new File("testDirectory"));
        when(exportSettingsStageMock.getExportType()).thenReturn(ExportType.CHOICE_OPTION);
        when(exportSettingsStageMock.getHtmlVariableName()).thenReturn("testHtmlVariable");

        // Perform action on save button
            try {
                Method method = exportSettingsController.getClass().getMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(exportSettingsController);
            } catch (Exception e) {
                fail();
            }


        // Verify that export settings are set correctly in the model
        ExportSettings expectedExportSettings = new ExportSettings(100, 200, new File("testDirectory").toPath(),
                FileFormat.PNG, ExportType.CHOICE_OPTION, "testHtmlVariable");
        verify(controllerFacadeMock.getProject()).setExportSettings(expectedExportSettings);
        });
    }
}