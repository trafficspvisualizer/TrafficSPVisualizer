package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class ChoiceOptionSettingsStage extends Stage {

    private ViewFacade viewFacade;



    public ChoiceOptionSettingsStage(ViewFacade viewFacade, int coIndex) {
        this.viewFacade = viewFacade;

    }

    // update-methods
    public void updateRouteSections() {

    }

    public void updateAttributes() {

    }

    // show-methods
    public Optional<ButtonType> showRemoveRouteSectionConfirmationAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(languageStrategy.getRemoveRouteSectionConfirmationAlertTitle());
        alert.setHeaderText(languageStrategy.getRemoveRouteSectionConfirmationAlertHeaderText());
        alert.setContentText(languageStrategy.getRemoveRouteSectionConfirmationAlertContentText());

        return alert.showAndWait();
    }
}
