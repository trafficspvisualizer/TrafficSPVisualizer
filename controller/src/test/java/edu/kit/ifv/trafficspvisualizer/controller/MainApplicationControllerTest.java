package edu.kit.ifv.trafficspvisualizer.controller;


import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportSettings;
import edu.kit.ifv.trafficspvisualizer.model.settings.ExportType;
import edu.kit.ifv.trafficspvisualizer.model.settings.FileFormat;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectSaver;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.Event;
import javafx.scene.control.ButtonType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MainApplicationControllerTest {

    private MainApplicationController mockMainApplicationController;
    private ControllerFacade mockControllerFacade;
    private MainApplicationWindow mockMainApplicationWindow;

    private Project mockProject;

    private ViewFacade mockViewFacade;
    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            mockControllerFacade = mock(ControllerFacade.class);
            mockMainApplicationWindow = mock(MainApplicationWindow.class);

            when(mockControllerFacade.getViewFacade().getMainApplicationWindow()).thenReturn(mockMainApplicationWindow);
            mockMainApplicationController = new MainApplicationController(mockControllerFacade);

            mockProject = mock(Project.class);
            mockViewFacade = mock(ViewFacade.class);

        });
    }


    @Test
    void testActionOnLoadProjectMenuItem() {
        Platform.runLater(() -> {
            File selectedFile = new File("testFile");

            when(mockMainApplicationWindow.showDirectoryChooserDialog()).thenReturn(selectedFile);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLoadProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade).setProject(mockProject);
            verify(mockViewFacade).setProject(mockProject);

            verify(mockMainApplicationController).updateChoiceOptions();
            verify(mockMainApplicationController).updatePreview();
        });
    }

    @Test
    void testActionOnLoadProjectMenuItemNull() {
        Platform.runLater(() -> {
            when(mockMainApplicationWindow.showDirectoryChooserDialog()).thenReturn(null);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLoadProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade, never()).setProject(mockProject);
            verify(mockViewFacade, never()).setProject(mockProject);
            verify(mockMainApplicationController, never()).updateChoiceOptions();
            verify(mockMainApplicationController, never()).updatePreview();
        });
    }

    @Test
    void testActionOnLoadProjectMenuItemInvalidDirectory() {
        Platform.runLater(() -> {
            // not a valid directory
            File selectedFile = new File("testFile");
            when(mockMainApplicationWindow.showDirectoryChooserDialog()).thenReturn(selectedFile);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLoadProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getMainApplicationWindow()).showPreviewErrorAlert();
        });
    }

    @Test
    void testActionOnSaveProjectMenuItem() {
        Platform.runLater(() -> {
            Project mockProject = mock(Project.class);
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            when(mockProject.getProjectPath()).thenReturn(new File("test").toPath());
            StandardProjectSaver mockStandardProjectSaver = mock(StandardProjectSaver.class);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnSaveProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            try {
                verify(mockStandardProjectSaver).saveProject(mockProject, mockProject.getProjectPath());
            } catch (IOException e) {
                fail();
            }
        });
    }

    @Test
    void testActionOnSaveProjectMenuItemNull() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(null);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnSaveProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow).showNoProjectErrorAlert();
        });
    }

    @Test
    void testActionOnSaveProjectMenuItemIllegalArgumentException() {
        Platform.runLater(() -> {
            Project mockProject = mock(Project.class);
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            //return path as null
            when(mockProject.getProjectPath()).thenReturn(null);
            StandardProjectSaver mockStandardProjectSaver = mock(StandardProjectSaver.class);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnSaveProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow).showSaveProjectErrorAlert();
        });
    }

    @Test
    void testActionOnExportButton() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            ExportSettings mockExportSettings = mock(ExportSettings.class);
            when(mockProject.getExportSettings()).thenReturn(mockExportSettings);
            when(mockExportSettings.getExportPath()).thenReturn(Path.of("test"));
            when(mockExportSettings.getExportType()).thenReturn(ExportType.CHOICE_OPTION);
            when(mockExportSettings.getFileFormat()).thenReturn(FileFormat.PNG);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnExportButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow, never()).showNoProjectErrorAlert();
        });
    }

    @Test
    void testActionOnExportButtonProjectNull() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(null);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnExportButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow).showNoProjectErrorAlert();
        });
    }

    @Test
    void testActionOnExportButtonExportSettingsNotFullyConfigured() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            ExportSettings mockExportSettings = mock(ExportSettings.class);
            when(mockProject.getExportSettings()).thenReturn(mockExportSettings);
            when(mockExportSettings.getExportPath()).thenReturn(null);
            when(mockExportSettings.getExportType()).thenReturn(null);
            when(mockExportSettings.getFileFormat()).thenReturn(null);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnExportButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }
            verify(mockMainApplicationWindow).showExportErrorAlert();
        });
    }

    @Test
    void testActionOnExportButtonException() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            ExportSettings mockExportSettings = mock(ExportSettings.class);
            when(mockProject.getExportSettings()).thenReturn(mockExportSettings);
            when(mockExportSettings.getExportPath()).thenReturn(Path.of("test///:/test/test/test/")); //invalid
            when(mockExportSettings.getExportType()).thenReturn(ExportType.CHOICE_OPTION);
            when(mockExportSettings.getFileFormat()).thenReturn(FileFormat.PNG);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnExportButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }
            verify(mockMainApplicationWindow).showExportErrorAlert();
        });
    }

    @Test
    void testActionOnExportSettingsButton() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnExportSettingsButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade).createExportSettingsController();
        });
    }
    @Test
    void testActionOnExportSettingsButtonNull() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(null);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnExportSettingsButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getMainApplicationWindow()).showNoProjectErrorAlert();
            });
    }

    @Test
    void testActionOnAttributesButton() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnAttributesButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade).createAttributeController();
        });
    }
    @Test
    void testActionOnAttributesButtonNull() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(null);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnAttributesButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getMainApplicationWindow()).showNoProjectErrorAlert();
        });
    }

    @Test
    void testActionOnRightSwitchPreviewButton() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnRightSwitchPreviewButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject()).incrementPreview();
            verify(mockMainApplicationController).updatePreview();
        });
    }
    @Test
    void testActionOnRightSwitchPreviewButtonNull() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(null);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnRightSwitchPreviewButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject(), never()).incrementPreview();
            verify(mockMainApplicationController, never()).updatePreview();
        });
    }

    @Test
    void testActionOnLeftSwitchPreviewButton() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLeftSwitchPreviewButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject()).decrementPreview();
            verify(mockMainApplicationController).updatePreview();
        });
    }
    @Test
    void testActionOnLeftSwitchPreviewButtonNull() {
        Platform.runLater(() -> {
            when(mockControllerFacade.getProject()).thenReturn(null);
            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLeftSwitchPreviewButton");
                method.setAccessible(true);
                method.invoke(mockMainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject(), never()).decrementPreview();
            verify(mockMainApplicationController, never()).updatePreview();
        });
    }

    @Test
    void testActionOnCloseRequestCloseConfirmed() {
        Platform.runLater(() -> {
            when(mockMainApplicationWindow.showCloseProjectConfirmationAlert()).thenReturn(java.util.Optional.of(ButtonType.OK));
            Event mockEvent = mock(Event.class);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnCloseRequest", Event.class);
                method.setAccessible(true);
                method.invoke(mockMainApplicationController, mockEvent);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow).close();
        });
    }

    @Test
    void testActionOnCloseRequestCloseCancelled() {
        Platform.runLater(() -> {
            when(mockMainApplicationWindow.showCloseProjectConfirmationAlert()).thenReturn(java.util.Optional.of(ButtonType.CANCEL));
            Event mockEvent = mock(Event.class);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnCloseRequest", Event.class);
                method.setAccessible(true);
                method.invoke(mockMainApplicationController, mockEvent);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow, never()).close();
        });
    }
}
