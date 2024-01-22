package edu.kit.ifv.trafficspvisualizer.launcher;

import edu.kit.ifv.trafficspvisualizer.controller.ControllerFacade;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import javafx.application.Application;
import javafx.stage.Stage;

public class TrafficSPVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewFacade viewFacade = new ViewFacade(primaryStage, null);
        new ControllerFacade(viewFacade, null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
