package edu.kit.ifv.trafficspvisualizer.view.javafx;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.FlowPane;

/**
 * Custom JavaFX {@link FlowPane} with a single selection model.
 *
 * @version 1.0
 */
public class ListFlowPane extends FlowPane {

    private final SingleSelectionModel<Node> singleSelectionModel;

    /**
     * Creates the {@link ListFlowPane} by adding and linking a single selection model.
     */
    public ListFlowPane() {
        singleSelectionModel = new ListFlowPaneSingleSelectionModel(this);

        getChildren().addListener((ListChangeListener<Node>) change -> {
            if (change.next()) {
                for (Node node : change.getAddedSubList()) {
                    node.setOnMouseClicked(event -> singleSelectionModel.select(node));
                }
            }
        });
    }

    /**
     * Gets the {@link SingleSelectionModel}.
     *
     * @return The {@link SingleSelectionModel}.
     */
    public SingleSelectionModel<Node> getSingleSelectionModel() {
        return singleSelectionModel;
    }
}
