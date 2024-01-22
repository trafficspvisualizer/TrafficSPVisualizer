package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class MainApplicationWindow {

    private ViewFacade viewFacade;

    private Stage stage;

    private Scene scene;

    public MainApplicationWindow(ViewFacade viewFacade, Stage stage) {
        this.viewFacade = viewFacade;
        this.stage = stage;
        buildStage();
        styleStage();
    }


    // setter-methods
    public void setPreviewImage(Image previewImage) {

    }



    // update-methods
    private void buildStage() {

    }

    private void styleStage() {

    }

    public void updateCurrentPreviewSituation() {

    }

    public void updateChoiceOptions() {

    }




    // show-methods
    public File showFileChooserDialog() {

        return null;
    }

    public Optional<ButtonType> showCloseProjectConformationAlert() {
        return Optional.empty();
    }

    public void showExportErrorAlert() {

    }

    public void showNoProjectErrorAlert() {

    }

    public void showLoadProjectErrorAlert() {

    }

    public void saveLoadProjectErrorAlert() {

    }


}
