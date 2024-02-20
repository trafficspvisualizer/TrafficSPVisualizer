package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.IconSelectionStage;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IconSelectionControllerTest {

    private IconSelectionController iconSelectionController;
    private ControllerFacade mockControllerFacade;
    private ViewFacade mockViewFacade;
    private IconSelectionStage mockIconSelectionStage;
    private IconDisplayingController mockParentController;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }
    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            mockControllerFacade = mock(ControllerFacade.class);
            mockViewFacade = mock(ViewFacade.class);
            mockIconSelectionStage = mock(IconSelectionStage.class);
            mockParentController = mock(IconDisplayingController.class);

            when(mockControllerFacade.getViewFacade()).thenReturn(mockViewFacade);
            when(mockViewFacade.getIconSelectionStage()).thenReturn(mockIconSelectionStage);

            iconSelectionController = new IconSelectionController(mockControllerFacade, mockParentController, 0);
        });
    }

    @Test
    void testActionOnSelectButton() {
        Platform.runLater(() -> {
            Icon mockIcon = mock(Icon.class);
            int iconIdentifier = 0;
            when(mockIconSelectionStage.getSelectedIconIdentifier()).thenReturn(iconIdentifier);
            when(mockControllerFacade.getProject()).thenReturn(mock(Project.class));
            when(mockControllerFacade.getProject().getIconManager().getIcons()).thenReturn(new HashMap<>());
            when(mockControllerFacade.getProject().getIconManager().getIcons().containsKey(anyInt())).thenReturn(true);
            when(mockControllerFacade.getProject().getIconManager().getIcons().get(anyInt())).thenReturn(mockIcon);

            try {
                Method method = iconSelectionController.getClass().getDeclaredMethod("actionOnSelectButton");
                method.setAccessible(true);
                method.invoke(iconSelectionController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject().getIconManager().getIcons()).get(iconIdentifier);
            verify(mockParentController).updateIcon(mockIcon, anyInt());
            verify(mockIconSelectionStage).close();
            verify(mockViewFacade).setIconSelectionStage(null);
            verify(mockControllerFacade).deleteIconSelectionController();
        });
    }

    @Test
    void testActionOnSelectButtonNull() {
        Platform.runLater(() -> {
            int iconIdentifier = 0;
            when(mockIconSelectionStage.getSelectedIconIdentifier()).thenReturn(iconIdentifier);
            when(mockControllerFacade.getProject()).thenReturn(mock(Project.class));
            when(mockControllerFacade.getProject().getIconManager().getIcons()).thenReturn(new HashMap<>());
            when(mockControllerFacade.getProject().getIconManager().getIcons().containsKey(anyInt())).thenReturn(true);
            when(mockControllerFacade.getProject().getIconManager().getIcons().get(anyInt())).thenReturn(null);

            try {
                Method method = iconSelectionController.getClass().getDeclaredMethod("actionOnSelectButton");
                method.setAccessible(true);
                method.invoke(iconSelectionController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getIconSelectionStage()).showSelectIconErrorAlert();
        });
    }

    @Test
    void testActionOnAddIconButton() {
        Platform.runLater(() -> {
            File selectedFile = new File("test");
            when(mockIconSelectionStage.showFileChooserDialog()).thenReturn(selectedFile);

            try {
                Method method = iconSelectionController.getClass().getMethod("actionOnAddIconButton");
                method.setAccessible(true);
                method.invoke(iconSelectionController);
            } catch (Exception e) {
                fail();
            }

            verify(mockIconSelectionStage, times(1)).updateStage();
        });
    }

    @Test
    void testActionOnAddIconButtonNull() {
        Platform.runLater(() -> {
            when(mockIconSelectionStage.showFileChooserDialog()).thenReturn(null);

            try {
                Method method = iconSelectionController.getClass().getDeclaredMethod("actionOnAddIconButton");
                method.setAccessible(true);
                method.invoke(iconSelectionController);
            } catch (Exception e) {
                fail();
            }


            verify(mockIconSelectionStage, never()).updateStage();
        });
    }

    @Test
    void testActionOnAddIconButtonIOException() {
        Platform.runLater(() -> {
            File selectedFile = new File("testIconFile");
            when(mockIconSelectionStage.showFileChooserDialog()).thenReturn(selectedFile);


            try {
                doThrow(IOException.class).when(mockControllerFacade.getProject().getIconManager()).createIcon(ArgumentMatchers.any(Path.class));
            } catch (IOException e) {
                fail();
            }

            try {
                Method method = iconSelectionController.getClass().getDeclaredMethod("actionOnAddIconButton");
                method.setAccessible(true);
                method.invoke(iconSelectionController);
            } catch (Exception e) {
                fail();
            }
            verify(mockIconSelectionStage, never()).updateStage();
            verify(mockControllerFacade.getViewFacade().getIconSelectionStage()).showAddIconErrorAlert();
        });
    }



}