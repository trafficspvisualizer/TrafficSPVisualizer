package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.Attribute;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AttributeSettingsStage extends Stage {

    private ViewFacade viewFacade;

    private int attributeIndex;

    private Text nameText;

    private TextField nameTextField;

    private Text iconText;

    private ImageView iconButtonImageView;

    private Button iconButton;

    private Text prefixText;

    private TextField prefixTextField;

    private Text suffixText;

    private TextField suffixTextField;

    private Text numberOfDecimalPlacesText;

    private TextField numberOfDecimalPlacesTextField;

    private Text permanentlyVisibleText;

    private CheckBox permanentlyVisibleCheckBox;

    private GridPane configGridPane;


    private Button saveButton;

    private Button cancelButton;

    private GridPane saveAndCancelGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;






    public AttributeSettingsStage(ViewFacade viewFacade, int attributeIndex) {
        this.viewFacade = viewFacade;
        this.attributeIndex = attributeIndex;
        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        nameText = new Text(languageStrategy.getAttributeSettingsNameText());

        nameTextField = new TextField();

        iconText = new Text(languageStrategy.getAttributeSettingsIconText());

        iconButtonImageView = new ImageView();
        iconButton = new Button();
        iconButton.setGraphic(iconButtonImageView);

        prefixText = new Text(languageStrategy.getAttributeSettingsPrefixText());

        prefixTextField = new TextField();

        suffixText = new Text(languageStrategy.getAttributeSettingsSuffixText());

        suffixTextField = new TextField();

        numberOfDecimalPlacesText = new Text(languageStrategy.getAttributeSettingsNumberOfDecimalPlacesText());

        numberOfDecimalPlacesTextField = new TextField();

        permanentlyVisibleText = new Text(languageStrategy.getAttributeSettingsPermanentlyVisibleText());

        permanentlyVisibleCheckBox = new CheckBox();

        configGridPane = new GridPane();
        configGridPane.add(nameText,0,0);
        configGridPane.add(nameTextField,1,0);
        configGridPane.add(iconText,0,1);
        configGridPane.add(iconButton,1,1);
        configGridPane.add(prefixText,0,2);
        configGridPane.add(prefixTextField,1,2);
        configGridPane.add(suffixText,0,3);
        configGridPane.add(suffixTextField,1,3);
        configGridPane.add(numberOfDecimalPlacesText,0,4);
        configGridPane.add(numberOfDecimalPlacesTextField,1,4);
        configGridPane.add(permanentlyVisibleText,0,5);
        configGridPane.add(permanentlyVisibleCheckBox,1,5);


        saveButton = new Button(languageStrategy.getAttributeSettingsSaveButtonText());

        cancelButton = new Button(languageStrategy.getAttributeSettingsCancelButtonText());

        saveAndCancelGridPane = new GridPane();
        saveAndCancelGridPane.add(saveButton, 0,0);
        saveAndCancelGridPane.add(cancelButton,1,0);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(configGridPane);
        bodyBorderPane.setBottom(saveAndCancelGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getAttributeSettingsTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());
    }

    // style-methods
    private void styleStage() {
        // nameText
        GridPane.setHalignment(nameText, HPos.LEFT);
        GridPane.setValignment(nameText, VPos.CENTER);
        nameText.setFont(FontLibrary.getMidFont());

        // nameTextField
        GridPane.setHalignment(nameTextField, HPos.LEFT);
        GridPane.setValignment(nameTextField, VPos.CENTER);
        GridPane.setHgrow(nameTextField, Priority.ALWAYS);
        nameTextField.setFont(FontLibrary.getSmallFont());

        // iconText
        GridPane.setHalignment(iconText, HPos.LEFT);
        GridPane.setValignment(iconText, VPos.CENTER);
        iconText.setFont(FontLibrary.getMidFont());


        // iconButtonImageView
        iconButtonImageView.setFitWidth(50);
        iconButtonImageView.setFitHeight(50);
        iconButtonImageView.setPreserveRatio(true);

        // iconButton
        GridPane.setHalignment(iconButton, HPos.LEFT);
        GridPane.setValignment(iconButton, VPos.CENTER);

        // prefixText
        GridPane.setHalignment(prefixText, HPos.LEFT);
        GridPane.setValignment(prefixText, VPos.CENTER);
        prefixText.setFont(FontLibrary.getMidFont());

        // prefixTextField
        GridPane.setHalignment(prefixTextField, HPos.LEFT);
        GridPane.setValignment(prefixTextField, VPos.CENTER);
        GridPane.setHgrow(prefixTextField, Priority.ALWAYS);
        prefixTextField.setFont(FontLibrary.getSmallFont());

        // suffixText
        GridPane.setHalignment(suffixText, HPos.LEFT);
        GridPane.setValignment(suffixText, VPos.CENTER);
        suffixText.setFont(FontLibrary.getMidFont());

        // suffixTextField
        GridPane.setHalignment(suffixTextField, HPos.LEFT);
        GridPane.setValignment(suffixTextField, VPos.CENTER);
        GridPane.setHgrow(suffixTextField, Priority.ALWAYS);
        suffixTextField.setFont(FontLibrary.getSmallFont());

        // numberOfDecimalPlacesText
        GridPane.setHalignment(numberOfDecimalPlacesText, HPos.LEFT);
        GridPane.setValignment(numberOfDecimalPlacesText, VPos.CENTER);
        numberOfDecimalPlacesText.setFont(FontLibrary.getMidFont());

        // numberOfDecimalPlacesTextField
        GridPane.setHalignment(numberOfDecimalPlacesTextField, HPos.LEFT);
        GridPane.setValignment(numberOfDecimalPlacesTextField, VPos.CENTER);
        GridPane.setHgrow(numberOfDecimalPlacesTextField, Priority.ALWAYS);
        numberOfDecimalPlacesTextField.setFont(FontLibrary.getSmallFont());

        // alwaysVisibleText
        GridPane.setHalignment(permanentlyVisibleText, HPos.LEFT);
        GridPane.setValignment(permanentlyVisibleText, VPos.CENTER);
        permanentlyVisibleText.setFont(FontLibrary.getMidFont());

        // alwaysVisibleCheckBox
        GridPane.setHalignment(permanentlyVisibleCheckBox, HPos.LEFT);
        GridPane.setValignment(permanentlyVisibleCheckBox, VPos.CENTER);

        // configGridPane
        BorderPane.setAlignment(configGridPane, Pos.TOP_LEFT);
        configGridPane.setPadding(new Insets(15));
        configGridPane.setHgap(15);
        configGridPane.setVgap(15);

        // saveButton
        GridPane.setHalignment(saveButton, HPos.LEFT);
        GridPane.setValignment(saveButton, VPos.CENTER);
        saveButton.setFont(FontLibrary.getSmallFont());

        // cancelButton
        GridPane.setHalignment(cancelButton, HPos.LEFT);
        GridPane.setValignment(cancelButton, VPos.CENTER);
        cancelButton.setFont(FontLibrary.getSmallFont());

        // saveAndCancelGridPane
        BorderPane.setAlignment(saveAndCancelGridPane, Pos.BOTTOM_LEFT);
        saveAndCancelGridPane.setPadding(new Insets(15));
        saveAndCancelGridPane.setHgap(15);
        saveAndCancelGridPane.setVgap(15);

        // bodyBorderPane

        // scene

        // stage
        setMinWidth(960);
        setMinHeight(540);
        setWidth(960);
        setHeight(540);
    }

    // update-methods
    private void updateStage() {
        Attribute attribute = (Attribute) viewFacade.getProject().getAttributes().get(attributeIndex);

        nameTextField.setText(attribute.getName());

        //TODO: iconButtonImageView.setImage();

        prefixTextField.setText(attribute.getPrefix());

        suffixTextField.setText(attribute.getSuffix());

        numberOfDecimalPlacesTextField.setText(String.valueOf(attribute.getDecimalPlaces()));

        permanentlyVisibleCheckBox.setSelected(attribute.isPermanentlyVisible());


    }



    // setter-methods
    public void setIcon(int iconId) {
        //TODO: iconButtonImageView.setImage(); & iconButtonImageView.userData();
    }

    // show-methods
    public void showSaveErrorAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(languageStrategy.getSaveAttributeSettingsErrorAlertTitle());
        alert.setHeaderText(languageStrategy.getSaveAttributeSettingsErrorAlertHeaderText());
        alert.setContentText(languageStrategy.getSaveAttributeSettingsErrorAlertContentText());

        alert.showAndWait();
    }

}
