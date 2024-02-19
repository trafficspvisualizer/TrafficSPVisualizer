package edu.kit.ifv.trafficspvisualizer.controller;


import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectLoader;
import edu.kit.ifv.trafficspvisualizer.util.project.StandardProjectSaver;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.MainApplicationWindow;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MainApplicationControllerTest {

    private MainApplicationController mainApplicationController;
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
            mainApplicationController = new MainApplicationController(mockControllerFacade);

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
                method.invoke(mainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade).setProject(mockProject);
            verify(mockViewFacade).setProject(mockProject);

            verify(mainApplicationController).updateChoiceOptions();
            verify(mainApplicationController).updatePreview();
        });
    }

    @Test
    void testActionOnLoadProjectMenuItemNull() {
        Platform.runLater(() -> {


            when(mockMainApplicationWindow.showDirectoryChooserDialog()).thenReturn(null);


            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLoadProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mainApplicationController);
            } catch (Exception e) {
                fail();
            }


            verify(mockControllerFacade, never()).setProject(mockProject);
            verify(mockViewFacade, never()).setProject(mockProject);


            verify(mainApplicationController, never()).updateChoiceOptions();
            verify(mainApplicationController, never()).updatePreview();
        });
    }

    @Test
    void testActionOnLoadProjectMenuItemParseException() {
        Platform.runLater(() -> {

            File selectedFile = new File("testFile");


            when(mockMainApplicationWindow.showDirectoryChooserDialog()).thenReturn(selectedFile);

            StandardProjectLoader mockStandardProjectLoader = mock(StandardProjectLoader.class);
            try {
                when(mockStandardProjectLoader.loadProject(any(File.class))).thenThrow(new ParseException("", 1));
            } catch (Exception e) {
                fail();
            }


            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLoadProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getMainApplicationWindow()).showPreviewErrorAlert();
        });
    }

    @Test
    void testActionOnLoadProjectMenuItemIOException() {
        Platform.runLater(() -> {

            File selectedFile = new File("testFile");


            when(mockMainApplicationWindow.showDirectoryChooserDialog()).thenReturn(selectedFile);

            StandardProjectLoader mockStandardProjectLoader = mock(StandardProjectLoader.class);
            try {
                when(mockStandardProjectLoader.loadProject(any(File.class))).thenThrow(new IOException());
            } catch (Exception e) {
                fail();
            }


            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnLoadProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mainApplicationController);
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
                method.invoke(mainApplicationController);
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
                method.invoke(mainApplicationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockMainApplicationWindow).showNoProjectErrorAlert();
        });
    }

    @Test
    void testActionOnSaveProjectMenuItemIOException() {
        Platform.runLater(() -> {
            Project mockProject = mock(Project.class);
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            when(mockProject.getProjectPath()).thenReturn(new File("test").toPath());
            StandardProjectSaver mockStandardProjectSaver = mock(StandardProjectSaver.class);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnSaveProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mainApplicationController);
            } catch (Exception e) {
                fail();
            }

            try {
                doThrow(new IOException()).when(mockStandardProjectSaver).saveProject(any(), any());
            } catch (IOException e) {
                fail();
            }

            verify(mockMainApplicationWindow).showSaveProjectErrorAlert();
        });
    }
    @Test
    void testActionOnSaveProjectMenuItemParseException() {
        Platform.runLater(() -> {
            Project mockProject = mock(Project.class);
            when(mockControllerFacade.getProject()).thenReturn(mockProject);
            when(mockProject.getProjectPath()).thenReturn(new File("test").toPath());
            StandardProjectSaver mockStandardProjectSaver = mock(StandardProjectSaver.class);

            try {
                Method method = MainApplicationController.class.getDeclaredMethod("actionOnSaveProjectMenuItem");
                method.setAccessible(true);
                method.invoke(mainApplicationController);
            } catch (Exception e) {
                fail();
            }


            try {
                doThrow(new ParseException("", 0)).when(mockStandardProjectSaver).saveProject(any(), any());
            } catch (IOException e) {
                fail();
            }

            verify(mockMainApplicationWindow).showSaveProjectErrorAlert();
        });
    }

}
