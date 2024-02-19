package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.model.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeStage;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ButtonType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AttributeControllerTest {
    private AttributeController attributeController;
    private ControllerFacade mockControllerFacade;
    private Project mockProject;
    private ViewFacade mockViewFacade;
    private AttributeStage mockAttributeStage;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
        mockControllerFacade = mock(ControllerFacade.class);
        mockProject = mock(Project.class);
        mockViewFacade = mock(ViewFacade.class);
        mockAttributeStage = mock(AttributeStage.class);

        when(mockControllerFacade.getProject()).thenReturn(mockProject);
        when(mockControllerFacade.getViewFacade()).thenReturn(mockViewFacade);
        when(mockViewFacade.getAttributeStage()).thenReturn(mockAttributeStage);

        attributeController = new AttributeController(mockControllerFacade);
        });
    }

    @Test
    void testActionOnRemoveButtonConfirmed() {
        Platform.runLater(() -> {
        int indexToRemove = 0;
        AbstractAttribute mockAttribute = mock(AbstractAttribute.class);

        when(mockAttributeStage.showRemoveAttributeProjectConfirmationAlert())
                .thenReturn(Optional.of(ButtonType.OK));

        when(mockProject.getAbstractAttributes().get(indexToRemove)).thenReturn(mockAttribute);

        try {
            Method method = attributeController.getClass().getMethod("actionOnRemoveButton", int.class);
            method.setAccessible(true);
            method.invoke(attributeController, indexToRemove);
        } catch (Exception e) {
            fail();
        }

        verify(mockProject).removeAbstractAttribute(indexToRemove);
        verify(attributeController).update();
        });
    }

    @Test
    void testActionOnRemoveButtonCancelled() {
        Platform.runLater(() -> {
        int indexToRemove = 0;

        when(mockAttributeStage.showRemoveAttributeProjectConfirmationAlert())
                .thenReturn(Optional.of(ButtonType.CANCEL));

        try {
            Method method = attributeController.getClass().getMethod("actionOnRemoveButton", int.class);
            method.setAccessible(true);
            method.invoke(attributeController, indexToRemove);
        } catch (Exception e) {
            fail();
        }

        verify(mockProject, never()).removeAbstractAttribute(anyInt());
        verify(attributeController, never()).update();
        });
    }
}