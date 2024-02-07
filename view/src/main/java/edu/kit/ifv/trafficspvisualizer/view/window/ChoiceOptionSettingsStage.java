package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChoiceOptionSettingsStage extends Stage {

    private ViewFacade viewFacade;

    private int choiceOptionIndex;

    private Text titleText;

    private TextField titleTextView;

    private Text colorText;

    private ColorPicker colorPicker;

    private GridPane titleAndColorGridPane;


    private Text attributesText;

    private Text attributesNameText;

    private Text attributesColumnsText;

    private final List<List<CheckBox>> attributesColumnsCheckBoxList;

    private GridPane attributesGridPane;

    private ScrollPane attributeScrollPane;


    private Text routeSectionsText;

    private Text routeSectionsNumberText;

    private Text routeSectionsIconText;

    private Text routeSectionsLineTypeText;

    private Text routeSectionsColumnText;

    private final List<Button> routeSectionIconButtonList;

    private final List<ChoiceBox<String>> routeSectionLineTypeChoiceBoxList;

    private final List<ChoiceBox<String>> routeSectionColumnChoiceBoxList;

    private final List<Button> routeSectionRemoveButtonList;

    private GridPane routeSectionsGridPane;

    private ScrollPane routeSectionScrollPane;


    private Button addRouteSectionButton;

    private Button closeButton;

    private GridPane closeAndAddGridPane;


    private BorderPane bodyBorderPane;

    private Scene scene;



    public ChoiceOptionSettingsStage(ViewFacade viewFacade, int choiceOptionIndex) {
        attributesColumnsCheckBoxList = new ArrayList<>();
        routeSectionIconButtonList = new ArrayList<>();
        routeSectionLineTypeChoiceBoxList = new ArrayList<>();
        routeSectionColumnChoiceBoxList = new ArrayList<>();
        routeSectionRemoveButtonList = new ArrayList<>();
        this.viewFacade = viewFacade;
        this.choiceOptionIndex = choiceOptionIndex;


        show();
    }

    // update-methods
    private void updateStage() {

    }

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
