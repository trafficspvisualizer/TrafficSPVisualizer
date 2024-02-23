package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.window.AttributeSettingsStage;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


class AttributeSettingsControllerTest {

    private ControllerFacade mockControllerFacade;
    private AttributeSettingsStage mockAttributeSettingsStage;
    private AttributeSettingsController attributeSettingsController;
    private AttributeController mockAttributeController;
    private ViewFacade mockViewFacade;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }

    @BeforeEach
    void setup() {
        Platform.runLater(() -> {
            mockControllerFacade = mock(ControllerFacade.class);
            mockAttributeSettingsStage = mock(AttributeSettingsStage.class);
            mockViewFacade = mock(ViewFacade.class);
            mockAttributeController = mock(AttributeController.class);
            when(mockControllerFacade.getViewFacade()).thenReturn(mockViewFacade);
            when(mockControllerFacade.getViewFacade().getAttributeSettingsStage()).thenReturn(mockAttributeSettingsStage);
            attributeSettingsController = new AttributeSettingsController(mockControllerFacade, 0, false);
            when(mockControllerFacade.getAttributeController()).thenReturn(mockAttributeController);
        });
    }

    @Test
    void testActionOnSaveButtonValidData() {

        Platform.runLater(() -> {
            when(mockAttributeSettingsStage.getName()).thenReturn("TestName");
            when(mockAttributeSettingsStage.getIconId()).thenReturn(0);
            when(mockAttributeSettingsStage.getPrefix()).thenReturn("TestPrefix");
            when(mockAttributeSettingsStage.getSuffix()).thenReturn("TestSuffix");
            when(mockAttributeSettingsStage.isPermanentlyVisible()).thenReturn(true);
            when(mockAttributeSettingsStage.getNumberOfDecimalPlaces()).thenReturn("2");

            try {
                Method method = AttributeSettingsController.class.getDeclaredMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(attributeSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(mockAttributeController).update();
            verify(mockAttributeSettingsStage).close();
            verify(mockViewFacade).setAttributeSettingsStage(null);
            verify(mockControllerFacade).deleteAttributeSettingsController();
        });
    }

    @Test
    void testActionOnSaveButtonDecimalPlacesNotANumber() {

        Platform.runLater(() -> {
            when(mockAttributeSettingsStage.getName()).thenReturn("TestName");
            when(mockAttributeSettingsStage.getIconId()).thenReturn(0);
            when(mockAttributeSettingsStage.getPrefix()).thenReturn("TestPrefix");
            when(mockAttributeSettingsStage.getSuffix()).thenReturn("TestSuffix");
            when(mockAttributeSettingsStage.isPermanentlyVisible()).thenReturn(true);
            when(mockAttributeSettingsStage.getNumberOfDecimalPlaces()).thenReturn("test");

            try {
                Method method = AttributeSettingsController.class.getDeclaredMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(attributeSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getAttributeSettingsStage()).showSaveErrorAlert();

            verify(mockAttributeController, never()).update();
            verify(mockAttributeSettingsStage, never()).close();
            verify(mockViewFacade, never()).setAttributeSettingsStage(null);
            verify(mockControllerFacade, never()).deleteAttributeSettingsController();
        });
    }

    @Test
    void testActionOnSaveButtonDecimalPlacesLessThanZero() {

        Platform.runLater(() -> {
            when(mockAttributeSettingsStage.getName()).thenReturn("TestName");
            when(mockAttributeSettingsStage.getIconId()).thenReturn(0);
            when(mockAttributeSettingsStage.getPrefix()).thenReturn("TestPrefix");
            when(mockAttributeSettingsStage.getSuffix()).thenReturn("TestSuffix");
            when(mockAttributeSettingsStage.isPermanentlyVisible()).thenReturn(true);
            when(mockAttributeSettingsStage.getNumberOfDecimalPlaces()).thenReturn("-1");

            try {
                Method method = AttributeSettingsController.class.getDeclaredMethod("actionOnSaveButton");
                method.setAccessible(true);
                method.invoke(attributeSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getAttributeSettingsStage()).showSaveErrorAlert();

            verify(mockAttributeController, never()).update();
            verify(mockAttributeSettingsStage, never()).close();
            verify(mockViewFacade, never()).setAttributeSettingsStage(null);
            verify(mockControllerFacade, never()).deleteAttributeSettingsController();
        });
    }

    @Test
    void testActionOnCancelButtonNotWorkingOnNewAttribute() {
        Platform.runLater(() -> {
            try {
                Method method = AttributeSettingsController.class.getDeclaredMethod("actionOnCancelButton");
                method.setAccessible(true);
                method.invoke(attributeSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(mockAttributeSettingsStage).close();
            verify(mockViewFacade).setAttributeSettingsStage(null);
            verify(mockControllerFacade).deleteAttributeSettingsController();
        });
    }

    @Test
    void testActionOnCancelButtonWorkingOnNewAttribute() {
        Platform.runLater(() -> {

            int abstractAttributeIndex = 0;
            attributeSettingsController = new AttributeSettingsController(mockControllerFacade,
                    abstractAttributeIndex, true);

            try {
                Method method = AttributeSettingsController.class.getDeclaredMethod("actionOnCancelButton");
                method.setAccessible(true);
                method.invoke(attributeSettingsController);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getViewFacade().getProject()).removeAbstractAttribute(abstractAttributeIndex);
            verify(mockAttributeSettingsStage).close();
            verify(mockViewFacade).setAttributeSettingsStage(null);
            verify(mockControllerFacade).deleteAttributeSettingsController();
        });
    }
}