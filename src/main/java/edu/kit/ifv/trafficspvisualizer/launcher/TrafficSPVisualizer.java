package edu.kit.ifv.trafficspvisualizer.launcher;

import edu.kit.ifv.trafficspvisualizer.controller.ControllerFacade;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The {@link TrafficSPVisualizer} class inherits from {@link Application}
 * and is responsible for starting the application.
 *
 * @version 1.0
 */
public class TrafficSPVisualizer extends Application {

    @Override
    public void start(Stage primaryStage) {
        ViewFacade viewFacade = new ViewFacade(primaryStage, null);
        new ControllerFacade(viewFacade, null);
    }

    /**
     * Launches the application.
     *
     * @param args Command arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
