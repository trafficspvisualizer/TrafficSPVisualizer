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

class ExportSettingsControllerTest {

    private ControllerFacade controllerFacadeMock;
    private ViewFacade viewFacadeMock;
    private ExportSettingsController exportSettingsController;

    private ExportSettingsStage exportSettingsStageMock;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }
    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            controllerFacadeMock = mock(ControllerFacade.class);
            viewFacadeMock = mock(ViewFacade.class);
            exportSettingsController = new ExportSettingsController(controllerFacadeMock);
            exportSettingsStageMock = mock(ExportSettingsStage.class);

            when(controllerFacadeMock.getViewFacade()).thenReturn(viewFacadeMock);
            when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);
        });
    }

    @Test
    void testActionOnExportFolderButton() {
        Platform.runLater(() -> {
        ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
        when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);

        File selectedDirectory = new File("testDirectory");
        when(exportSettingsStageMock.showDirectoryChooserDialog()).thenReturn(selectedDirectory);

        try {
            Method method = exportSettingsController.getClass().getMethod("actionOnExportFolderButton");
            method.setAccessible(true);
            method.invoke(exportSettingsController);
        } catch (Exception e) {
            fail();
        }

        verify(exportSettingsStageMock).setExportDirectory(selectedDirectory);
    });
    }
    @Test
    void testActionOnExportFolderButtonNull() {
        Platform.runLater(() -> {
            ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
            when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);

            when(exportSettingsStageMock.showDirectoryChooserDialog()).thenReturn(null);

            try {
                Method method = exportSettingsController.getClass().getMethod("actionOnExportFolderButton");
                method.setAccessible(true);
                method.invoke(exportSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(exportSettingsStageMock, never()).setExportDirectory(null);
        });
    }

    @Test
    void testActionOnSaveButtonValidInput() {
        Platform.runLater(() -> {
            ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
            when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);

            when(exportSettingsStageMock.getHeightString()).thenReturn("100");
            when(exportSettingsStageMock.getWidthString()).thenReturn("200");
            when(exportSettingsStageMock.getExportDirectory()).thenReturn(new File("testDirectory"));
            when(exportSettingsStageMock.getExportType()).thenReturn(ExportType.CHOICE_OPTION);
            when(exportSettingsStageMock.getHtmlVariableName()).thenReturn("testHtmlVariable");

            try {
                Method method = exportSettingsController.getClass().getMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(exportSettingsController);
            } catch (Exception e) {
                fail();
            }


            ExportSettings expectedExportSettings = new ExportSettings(100, 200, new File("testDirectory").toPath(),
                                        FileFormat.PNG, ExportType.CHOICE_OPTION, "testHtmlVariable");
            verify(controllerFacadeMock.getProject()).setExportSettings(expectedExportSettings);
        });
    }
    @Test
    void testActionOnSaveButtonInvalidEmptyInput() {
        Platform.runLater(() -> {
            when(exportSettingsStageMock.getHeightString()).thenReturn("");
            when(exportSettingsStageMock.getWidthString()).thenReturn("");
            when(exportSettingsStageMock.getExportDirectory()).thenReturn(null);
            when(exportSettingsStageMock.getExportType()).thenReturn(null);
            when(exportSettingsStageMock.getHtmlVariableName()).thenReturn("testHtmlVariable");

            try {
                Method method = exportSettingsController.getClass().getMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(exportSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(controllerFacadeMock.getViewFacade().getExportSettingsStage()).showSaveErrorAlert();
        });
    }
    @Test
    void testActionOnSaveButtonInvalidEmptyNoInteger() {
        Platform.runLater(() -> {
            ExportSettingsStage exportSettingsStageMock = mock(ExportSettingsStage.class);
            when(controllerFacadeMock.getViewFacade().getExportSettingsStage()).thenReturn(exportSettingsStageMock);

            when(exportSettingsStageMock.getHeightString()).thenReturn("test");
            when(exportSettingsStageMock.getWidthString()).thenReturn("test");
            when(exportSettingsStageMock.getExportDirectory()).thenReturn(new File("testDirectory"));
            when(exportSettingsStageMock.getExportType()).thenReturn(ExportType.CHOICE_OPTION);
            when(exportSettingsStageMock.getHtmlVariableName()).thenReturn("testHtmlVariable");

            try {
                Method method = exportSettingsController.getClass().getMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(exportSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(controllerFacadeMock.getViewFacade().getExportSettingsStage()).showSaveErrorAlert();
        });
    }
}