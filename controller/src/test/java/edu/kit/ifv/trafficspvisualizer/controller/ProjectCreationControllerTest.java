package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.data.DataObject;
import edu.kit.ifv.trafficspvisualizer.util.parse.NGDParser;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.ProjectCreationStage;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProjectCreationControllerTest {

    private ProjectCreationController projectCreationController;
    private ControllerFacade mockControllerFacade;
    private ViewFacade mockViewFacade;
    private ProjectCreationStage mockProjectCreationStage;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            mockControllerFacade = mock(ControllerFacade.class);
            mockViewFacade = mock(ViewFacade.class);
            mockProjectCreationStage = mock(ProjectCreationStage.class);

            when(mockControllerFacade.getViewFacade()).thenReturn(mockViewFacade);
            when(mockViewFacade.getProjectCreationStage()).thenReturn(mockProjectCreationStage);

            projectCreationController = new ProjectCreationController(mockControllerFacade);
        });
    }

    @Test
    void testActionOnSaveProjectDirectoryButton() {
        Platform.runLater(() -> {
            File selectedDirectory = new File("testDirectory");
            when(mockProjectCreationStage.showDirectoryChooserDialog()).thenReturn(selectedDirectory);

            try {
                Method method = projectCreationController.getClass().getMethod("projectCreationController");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockProjectCreationStage).setSaveProjectDirectory(selectedDirectory);
        });
    }

    @Test
    void testActionOnSaveProjectDirectoryButtonNull() {
        Platform.runLater(() -> {
            when(mockProjectCreationStage.showDirectoryChooserDialog()).thenReturn(null);

            try {
                Method method = projectCreationController.getClass().getMethod("projectCreationController");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockProjectCreationStage, never()).setSaveProjectDirectory(null);
        });
    }

    @Test
    void testActionOnInputDataFileButton() {
        Platform.runLater(() -> {
            File selectedFile = new File("testFile");
            when(mockProjectCreationStage.showFileChooserDialog()).thenReturn(selectedFile);

            try {
                Method method = projectCreationController.getClass().getMethod("actionOnInputDataFileButton");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockProjectCreationStage, times(1)).setInputDataFile(selectedFile);
        });
    }

    @Test
    void testActionOnInputDataFileButtonNull() {
        Platform.runLater(() -> {
            when(mockProjectCreationStage.showFileChooserDialog()).thenReturn(null);

            try {
                Method method = projectCreationController.getClass().getMethod("actionOnInputDataFileButton");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockProjectCreationStage, times(1)).setInputDataFile(null);
        });
    }

    @Test
    void testActionOnCreateNewProjectButtonValid() {
        Platform.runLater(() -> {

            String projectName = "testProject";
            File projectFolder = new File("testFolder");
            File inputFile = new File("testFile");
            Project mockProject = mock(Project.class);
            NGDParser mockParser = mock(NGDParser.class);

            when(mockViewFacade.getProjectCreationStage().getProjectName()).thenReturn(projectName);
            when(mockViewFacade.getProjectCreationStage().getSaveProjectDirectory()).thenReturn(projectFolder);
            when(mockViewFacade.getProjectCreationStage().getInputDataFile()).thenReturn(inputFile);
            when(mockControllerFacade.getMainApplicationController()).thenReturn(mock(MainApplicationController.class));
            when(mockControllerFacade.getProject()).thenReturn(mockProject);

            DataObject dataObject = new DataObject(null);
            try {
                when(mockParser.parse(inputFile)).thenReturn(dataObject);
            } catch (IOException | ParseException e) {
                fail();
            }


            try {
                Method method = projectCreationController.getClass().getMethod("actionOnCreateNewProjectButton");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }


            verify(mockControllerFacade).setProject(mockProject);
            verify(mockControllerFacade).deleteProjectCreationController();
            verify(mockControllerFacade.getMainApplicationController()).updateChoiceOptions();
            verify(mockControllerFacade.getMainApplicationController()).updatePreview();
        });
    }

    @Test
    void testActionOnCreateNewProjectButtonParseException() {
        Platform.runLater(() -> {

            String projectName = "testProject";
            File projectFolder = new File("testFolder");
            File inputFile = new File("testFile");
            Project mockProject = mock(Project.class);
            NGDParser mockParser = mock(NGDParser.class);

            when(mockViewFacade.getProjectCreationStage().getProjectName()).thenReturn(projectName);
            when(mockViewFacade.getProjectCreationStage().getSaveProjectDirectory()).thenReturn(projectFolder);
            when(mockViewFacade.getProjectCreationStage().getInputDataFile()).thenReturn(inputFile);
            when(mockControllerFacade.getMainApplicationController()).thenReturn(mock(MainApplicationController.class));
            when(mockControllerFacade.getProject()).thenReturn(mockProject);


            try {
                when(mockParser.parse(inputFile)).thenThrow(new ParseException("test", 1));
            } catch (IOException | ParseException e) {
                fail();
            }


            try {
                Method method = projectCreationController.getClass().getMethod("actionOnCreateNewProjectButton");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getProjectCreationStage()).showNewProjectErrorAlert();
        });
    }
    @Test
    void testActionOnCreateNewProjectButtonIOException() {
        Platform.runLater(() -> {

            String projectName = "testProject";
            File projectFolder = new File("testFolder");
            File inputFile = new File("testFile");
            Project mockProject = mock(Project.class);
            NGDParser mockParser = mock(NGDParser.class);

            when(mockViewFacade.getProjectCreationStage().getProjectName()).thenReturn(projectName);
            when(mockViewFacade.getProjectCreationStage().getSaveProjectDirectory()).thenReturn(projectFolder);
            when(mockViewFacade.getProjectCreationStage().getInputDataFile()).thenReturn(inputFile);
            when(mockControllerFacade.getMainApplicationController()).thenReturn(mock(MainApplicationController.class));
            when(mockControllerFacade.getProject()).thenReturn(mockProject);


            try {
                when(mockParser.parse(inputFile)).thenThrow(new IOException("test"));
            } catch (IOException | ParseException e) {
                fail();
            }


            try {
                Method method = projectCreationController.getClass().getMethod("actionOnCreateNewProjectButton");
                method.setAccessible(true);
                method.invoke(projectCreationController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getProjectCreationStage()).showNewProjectErrorAlert();
        });
    }

    @Test
    void testValidateInputValidInput() {
        Platform.runLater(() -> {


            String projectName = "TestProject";
            File projectFolder = new File("/path/to/project");
            File inputFile = new File("/path/to/inputFile");

            try {
                Method method = projectCreationController.getClass().getMethod("validateInput", String.class, File.class, File.class);
                method.setAccessible(true);
                assertTrue((boolean) method.invoke(projectCreationController, projectName, projectFolder, inputFile));
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    void testValidateInputInvalidInputName() {
        Platform.runLater(() -> {
            String projectName = " ! ? : test";
            File projectFolder = new File("test");
            File inputFile = new File("test");

            try {
                Method method = projectCreationController.getClass().getMethod("validateInput", String.class, File.class, File.class);
                method.setAccessible(true);
                assertFalse((boolean) method.invoke(projectCreationController, projectName, projectFolder, inputFile));
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    void testValidateInputInvalidInputFolderNull() {
        Platform.runLater(() -> {
            String projectName = " ! ? : test";
            File projectFolder = null;
            File inputFile = new File("test");

            try {
                Method method = projectCreationController.getClass().getMethod("validateInput", String.class, File.class, File.class);
                method.setAccessible(true);
                assertFalse((boolean) method.invoke(projectCreationController, projectName, projectFolder, inputFile));
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    void testValidateInputInvalidInputFileNull() {
        Platform.runLater(() -> {
            String projectName = "test";
            File projectFolder = new File("test");
            File inputFile = null;

            try {
                Method method = projectCreationController.getClass().getMethod("validateInput", String.class, File.class, File.class);
                method.setAccessible(true);
                assertFalse((boolean) method.invoke(projectCreationController, projectName, projectFolder, inputFile));
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    void testValidateInputInvalidInputFolderNotADirectory() {
        Platform.runLater(() -> {
            String projectName = "test";
            File projectFolder = mock(File.class);
            File inputFile = new File("test");

            when(projectFolder.isDirectory()).thenReturn(false);

            try {
                Method method = projectCreationController.getClass().getMethod("validateInput", String.class, File.class, File.class);
                method.setAccessible(true);
                assertFalse((boolean) method.invoke(projectCreationController, projectName, projectFolder, inputFile));
            } catch (Exception e) {
                fail();
            }
        });
    }

    @Test
    void testValidateInputInvalidInputFolderWithNameAlreadyExists() {

        Platform.runLater(() -> {
            String projectName = "test";
            File projectFolder = mock(File.class);
            File inputFile = new File("test");

            File mockFolder = mock(File.class);
            when(mockFolder.isDirectory()).thenReturn(true);
            when(mockFolder.getName().equals(projectName)).thenReturn(true);

            when(projectFolder.listFiles()).thenReturn(new File[]{mockFolder});

            try {
                Method method = projectCreationController.getClass().getMethod("validateInput", String.class, File.class, File.class);
                method.setAccessible(true);
                assertFalse((boolean) method.invoke(projectCreationController, projectName, projectFolder, inputFile));
            } catch (Exception e) {
                fail();
            }
        });
    }
}