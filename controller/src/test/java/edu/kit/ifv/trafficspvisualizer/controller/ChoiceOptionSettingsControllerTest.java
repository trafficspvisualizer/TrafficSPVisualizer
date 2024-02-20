package edu.kit.ifv.trafficspvisualizer.controller;

import edu.kit.ifv.trafficspvisualizer.view.window.ChoiceOptionSettingsStage;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ButtonType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ChoiceOptionSettingsControllerTest {

    private ChoiceOptionSettingsController choiceOptionSettingsController;
    private ControllerFacade mockControllerFacade;
    private ChoiceOptionSettingsStage mockChoiceOptionSettingsStage;

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }

    @BeforeEach
    void setUp() {
        Platform.runLater(() -> {
            mockControllerFacade = mock(ControllerFacade.class);
            mockChoiceOptionSettingsStage = mock(ChoiceOptionSettingsStage.class);
            when(mockControllerFacade.getViewFacade().getChoiceOptionSettingsStage()).thenReturn(mockChoiceOptionSettingsStage);
            ChoiceOptionSettingsController choiceOptionSettingsController = new ChoiceOptionSettingsController(0, mockControllerFacade);
        });
    }
    @Test
    void testActionOnCloseRequestCloseConfirmed() {
        Platform.runLater(() -> {
            when(mockChoiceOptionSettingsStage.showRemoveRouteSectionConfirmationAlert()).thenReturn(java.util.Optional.of(ButtonType.OK));

            try {
                Method method = ChoiceOptionSettingsController.class.getDeclaredMethod("actionOnRouteSectionRemoveButton", int.class);
                method.setAccessible(true);
                method.invoke(choiceOptionSettingsController, 0);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject().getChoiceOptions().getFirst()).removeRouteSection(0);
        });
    }

    @Test
    void testActionOnCloseRequestCloseCancelled() {
        Platform.runLater(() -> {
            when(mockChoiceOptionSettingsStage.showRemoveRouteSectionConfirmationAlert()).thenReturn(java.util.Optional.of(ButtonType.CANCEL));

            try {
                Method method = ChoiceOptionSettingsController.class.getDeclaredMethod("actionOnRouteSectionRemoveButton", int.class);
                method.setAccessible(true);
                method.invoke(choiceOptionSettingsController, 0);
            } catch (Exception e) {
                fail();
            }

            verify(mockControllerFacade.getProject().getChoiceOptions().getFirst(), never()).removeRouteSection(0);
        });
    }
}