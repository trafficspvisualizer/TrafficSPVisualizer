package edu.kit.ifv.trafficspvisualizer.view.javafx;

import javafx.scene.Node;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class ListFlowPaneSingleSelectionModel extends SingleSelectionModel<Node> {
    private static final DropShadow SELECTION_EFFECT = new DropShadow(20, Color.DARKTURQUOISE);

    private final ListFlowPane boundedListFlowPane;

    public ListFlowPaneSingleSelectionModel(ListFlowPane boundedListFlowPane) {
        this.boundedListFlowPane = boundedListFlowPane;
    }

    @Override
    protected Node getModelItem(int i) {
        return boundedListFlowPane.getChildren().get(i);
    }

    @Override
    protected int getItemCount() {
        return boundedListFlowPane.getChildren().size();
    }

    @Override
    public void select(int i) {
        resetCurrentEffect();

        super.select(i);

        getModelItem(i).setEffect(SELECTION_EFFECT);
    }

    @Override
    public void select(Node node) {
        resetCurrentEffect();

        super.select(node);

        node.setEffect(SELECTION_EFFECT);

    }

    private void resetCurrentEffect() {
        if (getSelectedItem() != null) {
            getSelectedItem().setEffect(null);
        }
    }
}
