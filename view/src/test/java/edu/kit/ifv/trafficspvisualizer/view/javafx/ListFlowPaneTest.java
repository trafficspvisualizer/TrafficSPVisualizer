package edu.kit.ifv.trafficspvisualizer.view.javafx;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.SingleSelectionModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the {@link ListFlowPane} & {@link ListFlowPaneSingleSelectionModel} classes.
 *
 * @version 1.0
 */
public class ListFlowPaneTest {

    @BeforeAll
    static void setUpClass() {
        new JFXPanel();
    }


    @Test
    public void testSingleSelectionModel() throws InterruptedException {
        final boolean[] selectionChanged = {false};

        Platform.runLater(() -> {
            ListFlowPane listFlowPane = new ListFlowPane();
            Button button1 = new Button();
            Button button2 = new Button();

            listFlowPane.getChildren().addAll(button1, button2);

            SingleSelectionModel<Node> model = listFlowPane.getSingleSelectionModel();
            model.selectedItemProperty().addListener((obs, oldVal, newVal) -> selectionChanged[0] = true);

            button1.fireEvent(new javafx.scene.input.MouseEvent(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
                    0, 0, 0, 0, javafx.scene.input.MouseButton.PRIMARY, 1,
                    true, true, true, true,
                    true, true, true, true, true, true, null));

        });

        TimeUnit.MILLISECONDS.sleep(500);
        assertTrue(selectionChanged[0]);
    }
}
