package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChoiceOptionSettingsStage extends Stage {

    private ViewFacade viewFacade;

    private int choiceOptionIndex;



    private Text titleText;

    private TextField titleTextField;

    private Text colorText;

    private ColorPicker colorPicker;

    private GridPane titleAndColorGridPane;


    private Text attributesText;

    private Text attributesNameText;

    private Text attributesColumnsText;

    private final List<List<CheckBox>> attributesColumnsCheckBoxList;

    private GridPane attributesGridPane;

    private ScrollPane attributesScrollPane;


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

    private ScrollPane routeSectionsScrollPane;


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
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {
        buildTitleAndColorGridPane();
        buildAttributesScrollPane();
        buildRouteSectionsScrollPane();
        buildCloseAndAddGridPane();

        bodyBorderPane = new BorderPane(routeSectionsScrollPane);
        bodyBorderPane.setTop(titleAndColorGridPane);
        bodyBorderPane.setLeft(attributesScrollPane);
        bodyBorderPane.setBottom(closeAndAddGridPane);

        scene = new Scene(bodyBorderPane);

        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }

    private void buildTitleAndColorGridPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        titleText = new Text(languageStrategy.getChoiceOptionSettingsTitleText());

        titleTextField = new TextField();

        colorText = new Text(languageStrategy.getChoiceOptionSettingsColorText());

        colorPicker = new ColorPicker();

        titleAndColorGridPane = new GridPane();
        titleAndColorGridPane.add(titleText,0,0);
        titleAndColorGridPane.add(titleTextField,1,0);
        titleAndColorGridPane.add(colorText,2,0);
        titleAndColorGridPane.add(colorPicker,3,0);
    }

    private void buildAttributesScrollPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        attributesText = new Text(languageStrategy.getChoiceOptionSettingsAttributesText());

        attributesNameText = new Text(languageStrategy.getChoiceOptionSettingsAttributesNameText());

        attributesColumnsText = new Text(languageStrategy.getChoiceOptionSettingsAttributesColumnsText());

        attributesGridPane = new GridPane();
        attributesGridPane.add(attributesText,0,0,2,1);
        attributesGridPane.add(attributesNameText, 0, 2);
        attributesGridPane.add(attributesColumnsText,1,2);

        attributesScrollPane = new ScrollPane(attributesGridPane);
    }

    private void buildRouteSectionsScrollPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        routeSectionsText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsText());

        routeSectionsNumberText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsNumberText());

        routeSectionsIconText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsIconText());

        routeSectionsLineTypeText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsLineTypeText());

        routeSectionsColumnText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsColumnText());

        routeSectionsGridPane = new GridPane();
        routeSectionsGridPane.add(routeSectionsText,0,0,5,1);
        routeSectionsGridPane.add(routeSectionsNumberText, 0,2);
        routeSectionsGridPane.add(routeSectionsIconText, 1,2);
        routeSectionsGridPane.add(routeSectionsLineTypeText,2,2);
        routeSectionsGridPane.add(routeSectionsColumnText,3,2);

        routeSectionsScrollPane = new ScrollPane(routeSectionsGridPane);
    }

    private void buildCloseAndAddGridPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();;

        addRouteSectionButton = new Button(languageStrategy.getChoiceOptionSettingsAddRouteSectionButtonText());

        closeButton = new Button(languageStrategy.getChoiceOptionSettingsCloseButtonText());

        closeAndAddGridPane = new GridPane();
        closeAndAddGridPane.add(addRouteSectionButton, 0,0);
        closeAndAddGridPane.add(closeButton,0,1);
    }


    // style-methods
    private void styleStage() {
        styleTitleAndColorGridPane();
        styleAttributeScrollPane();
        styleRouteSectionScrollPane();
        styleCloseAndAddGridPane();

        // bodyBorderPane

        // scene

        // stage
        setMinWidth(960);
        setMinHeight(540);
        setWidth(960);
        setHeight(540);
    }

    private void styleTitleAndColorGridPane() {
        // titleText
        GridPane.setHalignment(titleText, HPos.LEFT);
        GridPane.setValignment(titleText, VPos.CENTER);
        titleText.setFont(FontLibrary.getMidFont());

        // titleTextField
        GridPane.setHalignment(titleTextField, HPos.LEFT);
        GridPane.setValignment(titleTextField, VPos.CENTER);
        GridPane.setHgrow(titleTextField, Priority.ALWAYS);
        titleTextField.setFont(FontLibrary.getSmallFont());

        // colorText
        GridPane.setHalignment(colorText, HPos.CENTER);
        GridPane.setValignment(colorText, VPos.CENTER);
        colorText.setFont(FontLibrary.getMidFont());

        // colorPicker
        GridPane.setHalignment(colorText, HPos.LEFT);
        GridPane.setValignment(colorText, VPos.CENTER);

        // titleAndColorGridPane
        titleAndColorGridPane.setPadding(new Insets(15));
        titleAndColorGridPane.setHgap(15);
        titleAndColorGridPane.setVgap(15);
    }
    private void styleAttributeScrollPane() {
        // attributesText
        GridPane.setHalignment(attributesText, HPos.LEFT);
        GridPane.setValignment(attributesText, VPos.CENTER);
        attributesText.setFont(FontLibrary.getMidBoldFont());

        // attributesNameText
        GridPane.setHalignment(attributesNameText, HPos.CENTER);
        GridPane.setValignment(attributesNameText, VPos.CENTER);
        attributesNameText.setFont(FontLibrary.getSmallBoldFont());

        // attributesColumnsText
        GridPane.setHalignment(attributesColumnsText, HPos.CENTER);
        GridPane.setValignment(attributesColumnsText, VPos.CENTER);
        attributesColumnsText.setFont(FontLibrary.getSmallBoldFont());

        // attributesGridPane
        attributesGridPane.setPadding(new Insets(15));
        attributesGridPane.setHgap(15);
        attributesGridPane.setVgap(15);

        // attributesScrollPane
        attributesScrollPane.setFitToHeight(true);
    }
    private void styleRouteSectionScrollPane() {
        // routeSectionsText
        GridPane.setHalignment(routeSectionsText, HPos.LEFT);
        GridPane.setValignment(routeSectionsText, VPos.CENTER);
        routeSectionsText.setFont(FontLibrary.getMidBoldFont());

        // routeSectionsNumberText
        GridPane.setHalignment(routeSectionsNumberText, HPos.CENTER);
        GridPane.setValignment(routeSectionsNumberText, VPos.CENTER);
        routeSectionsNumberText.setFont(FontLibrary.getSmallBoldFont());

        // routeSectionsIconText
        GridPane.setHalignment(routeSectionsIconText, HPos.CENTER);
        GridPane.setValignment(routeSectionsIconText, VPos.CENTER);
        routeSectionsIconText.setFont(FontLibrary.getSmallBoldFont());

        // routeSectionsLineTypeText
        GridPane.setHalignment(routeSectionsLineTypeText, HPos.CENTER);
        GridPane.setValignment(routeSectionsLineTypeText, VPos.CENTER);
        routeSectionsLineTypeText.setFont(FontLibrary.getSmallBoldFont());

        // routeSectionsColumnText
        GridPane.setHalignment(routeSectionsColumnText, HPos.CENTER);
        GridPane.setValignment(routeSectionsColumnText, VPos.CENTER);
        routeSectionsColumnText.setFont(FontLibrary.getSmallBoldFont());

        // routeSectionsGridPane
        routeSectionsGridPane.setPadding(new Insets(15));
        routeSectionsGridPane.setHgap(15);
        routeSectionsGridPane.setVgap(15);

        // routeSectionsScrollPane
        routeSectionsScrollPane.setFitToHeight(true);

    }
    private void styleCloseAndAddGridPane() {
        // addRouteSectionButton
        GridPane.setHalignment(addRouteSectionButton, HPos.LEFT);
        GridPane.setValignment(addRouteSectionButton, VPos.CENTER);
        addRouteSectionButton.setFont(FontLibrary.getSmallFont());

        // closeButton
        GridPane.setHalignment(closeButton, HPos.LEFT);
        GridPane.setValignment(closeButton, VPos.CENTER);
        closeButton.setFont(FontLibrary.getSmallFont());

        // closeAndAddGridPane
        closeAndAddGridPane.setPadding(new Insets(15));
        closeAndAddGridPane.setHgap(15);
        closeAndAddGridPane.setVgap(15);
    }


    // update-methods
    private void updateStage() {

    }

    private void updateTitleAndColorGridPane() {

    }

    public void updateAttributeScrollPane() {

    }

    public void updateRouteSectionScrollPane() {

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
