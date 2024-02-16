package edu.kit.ifv.trafficspvisualizer.view.javafx;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.layout.FlowPane;

public class ListFlowPane extends FlowPane {

    private final SingleSelectionModel<Node> singleSelectionModel;

    public ListFlowPane() {
        singleSelectionModel = new ListFlowPaneSingleSelectionModel(this);

        getChildren().addListener((ListChangeListener<Node>) change -> {
            if (change.next()) {
                for (Node node : change.getAddedSubList()) {
                    node.setOnMouseClicked(event -> {
                        singleSelectionModel.select(node);
                    });
                }
            }
        });
    }

    public SingleSelectionModel<Node> getSingleSelectionModel() {
        return singleSelectionModel;
    }
}
