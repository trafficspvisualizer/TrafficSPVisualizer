package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.LineType;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttributeStage extends Stage {

    private ViewFacade viewFacade;


    private Text activeText;

    private Text nameText;

    private Text iconText;

    private Text prefixText;

    private Text suffixText;

    private Text numberOfDecimalPlacesText;

    private Text permanentlyVisibleText;

    private Pane sizingPane;

    private final List<CheckBox> attributeActiveCheckBoxList;

    private final List<Button> attributeSettingsButtonList;

    private final List<Button> upSwitchAttributeButtonList;

    private final List<Button> downSwitchAttributeButtonList;

    private final List<Button> attributeRemoveButtonList;

    private GridPane attributeGridPane;

    private ScrollPane attributeScrollPane;


    private Button addAttributeButton;

    private Button addSeparatorLineButton;

    private Button closeButton;

    private GridPane closeAndAddGridPane;

    private BorderPane bodyBorderPane;

    private Scene scene;



    public AttributeStage(ViewFacade viewFacade) {
        attributeActiveCheckBoxList = new ArrayList<>();
        attributeSettingsButtonList = new ArrayList<>();
        upSwitchAttributeButtonList = new ArrayList<>();
        downSwitchAttributeButtonList = new ArrayList<>();
        attributeRemoveButtonList = new ArrayList<>();
        this.viewFacade = viewFacade;

        buildStage();
        styleStage();
        updateStage();

        show();
    }

    // build-methods
    private void buildStage() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        activeText = new Text(languageStrategy.getAttributeActiveText());

        nameText = new Text(languageStrategy.getAttributeNameText());

        iconText = new Text(languageStrategy.getAttributeIconText());

        prefixText = new Text(languageStrategy.getAttributePrefixText());

        suffixText = new Text(languageStrategy.getAttributeSuffixText());

        numberOfDecimalPlacesText =
                new Text(languageStrategy.getAttributeNumberOfDecimalPlacesText());

        permanentlyVisibleText = new Text(languageStrategy.getAttributePermanentlyVisibleText());

        sizingPane = new Pane();

        attributeGridPane = new GridPane();
        attributeGridPane.add(activeText,0,0);
        attributeGridPane.add(nameText,1,0);
        attributeGridPane.add(iconText,2,0);
        attributeGridPane.add(prefixText,3,0);
        attributeGridPane.add(suffixText,4,0);
        attributeGridPane.add(numberOfDecimalPlacesText,5,0);
        attributeGridPane.add(permanentlyVisibleText,6,0);
        attributeGridPane.add(sizingPane,7,0);

        attributeScrollPane = new ScrollPane(attributeGridPane);


        addAttributeButton = new Button(languageStrategy.getAttributeAddAttributeButtonText());

        addSeparatorLineButton = new Button(languageStrategy.getAttributeAddSeparatorLineButtonText());

        closeButton = new Button(languageStrategy.getAttributeCloseButtonText());

        closeAndAddGridPane = new GridPane();
        closeAndAddGridPane.add(addAttributeButton,0,0);
        closeAndAddGridPane.add(addSeparatorLineButton,1,0);
        closeAndAddGridPane.add(closeButton,0,1,2,1);

        bodyBorderPane = new BorderPane();
        bodyBorderPane.setTop(attributeScrollPane);
        bodyBorderPane.setBottom(closeAndAddGridPane);

        scene = new Scene(bodyBorderPane);

        setTitle(languageStrategy.getAttributeTitle());
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
        getIcons().add(ImageLibrary.getApplicationIcon());

    }

    // style-methods
    private void styleStage() {
        // activeText
        GridPane.setHalignment(activeText, HPos.CENTER);
        GridPane.setValignment(activeText, VPos.CENTER);
        activeText.setFont(FontLibrary.getSmallBoldFont());

        // nameText
        GridPane.setHalignment(nameText, HPos.CENTER);
        GridPane.setValignment(nameText, VPos.CENTER);
        nameText.setFont(FontLibrary.getSmallBoldFont());

        // iconText
        GridPane.setHalignment(iconText, HPos.CENTER);
        GridPane.setValignment(iconText, VPos.CENTER);
        iconText.setFont(FontLibrary.getSmallBoldFont());

        // prefixText
        GridPane.setHalignment(prefixText, HPos.CENTER);
        GridPane.setValignment(prefixText, VPos.CENTER);
        prefixText.setFont(FontLibrary.getSmallBoldFont());

        // suffixText
        GridPane.setHalignment(suffixText, HPos.CENTER);
        GridPane.setValignment(suffixText, VPos.CENTER);
        suffixText.setFont(FontLibrary.getSmallBoldFont());

        // numberOfDecimalPlacesText
        GridPane.setHalignment(numberOfDecimalPlacesText, HPos.CENTER);
        GridPane.setValignment(numberOfDecimalPlacesText, VPos.CENTER);
        numberOfDecimalPlacesText.setFont(FontLibrary.getSmallBoldFont());

        // permanentlyVisibleText
        GridPane.setHalignment(permanentlyVisibleText, HPos.CENTER);
        GridPane.setValignment(permanentlyVisibleText, VPos.CENTER);
        permanentlyVisibleText.setFont(FontLibrary.getSmallBoldFont());

        // sizingPane
        GridPane.setHgrow(sizingPane, Priority.ALWAYS);

        // attributeGridPane
        BorderPane.setAlignment(attributeGridPane, Pos.TOP_LEFT);
        attributeGridPane.setPadding(new Insets(15));
        attributeGridPane.setHgap(15);
        attributeGridPane.setVgap(15);

        // TODO: Fix fitToHeight not working
        // attributeScrollPane
        // attributeScrollPane.setFitToHeight(true);

        // addAttributeButton
        GridPane.setHalignment(addAttributeButton, HPos.LEFT);
        GridPane.setValignment(addAttributeButton, VPos.CENTER);
        addAttributeButton.setFont(FontLibrary.getSmallFont());

        // addSeparatorLineButton
        GridPane.setHalignment(addSeparatorLineButton, HPos.LEFT);
        GridPane.setValignment(addSeparatorLineButton, VPos.CENTER);
        addSeparatorLineButton.setFont(FontLibrary.getSmallFont());

        // closeButton
        GridPane.setHalignment(closeButton, HPos.LEFT);
        GridPane.setValignment(closeButton, VPos.CENTER);
        closeButton.setFont(FontLibrary.getSmallFont());

        // closeAndAddGridPane
        BorderPane.setAlignment(closeAndAddGridPane, Pos.BOTTOM_LEFT);
        closeAndAddGridPane.setPadding(new Insets(15));
        closeAndAddGridPane.setHgap(15);
        closeAndAddGridPane.setVgap(15);

        // bodyBorderPane

        // scene

        // stage
        setMinWidth(960);
        setMinHeight(540);
        setWidth(960);
        setHeight(540);
    }



    // update- and add-methods
    public void updateStage() {
        attributeGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) >= 1);
        upSwitchAttributeButtonList.clear();
        downSwitchAttributeButtonList.clear();
        attributeSettingsButtonList.clear();
        attributeRemoveButtonList.clear();
        attributeActiveCheckBoxList.clear();

        int currentRowIndex = 0;
        for (AbstractAttribute abstractAttribute : viewFacade.getProject().getAttributes()) {
            currentRowIndex += 2;

            CheckBox attributeActiveCheckBox = new CheckBox();
            attributeActiveCheckBox.setSelected(abstractAttribute.isActive());

            GridPane.setHalignment(attributeActiveCheckBox, HPos.CENTER);
            GridPane.setValignment(attributeActiveCheckBox, VPos.CENTER);
            attributeActiveCheckBox.setFont(FontLibrary.getSmallFont());

            attributeActiveCheckBoxList.add(attributeActiveCheckBox);
            attributeGridPane.add(attributeActiveCheckBox, 0, currentRowIndex);



            ImageView upSwitchAttributeButtonImageView =
                    new ImageView(ImageLibrary.getAttributeUpSwitchAttributeButtonImage());

            upSwitchAttributeButtonImageView.setFitWidth(15);
            upSwitchAttributeButtonImageView.setFitHeight(15);
            upSwitchAttributeButtonImageView.setPreserveRatio(true);

            Button upSwitchAttributeButton = new Button();
            upSwitchAttributeButton.setGraphic(upSwitchAttributeButtonImageView);

            GridPane.setHalignment(upSwitchAttributeButton, HPos.CENTER);
            GridPane.setValignment(upSwitchAttributeButton, VPos.CENTER);

            upSwitchAttributeButtonList.add(upSwitchAttributeButton);
            attributeGridPane.add(upSwitchAttributeButton, 8, currentRowIndex);



            ImageView downSwitchAttributeButtonImageView =
                    new ImageView(ImageLibrary.getAttributeDownSwitchAttributeButtonImage());

            downSwitchAttributeButtonImageView.setFitWidth(15);
            downSwitchAttributeButtonImageView.setFitHeight(15);
            downSwitchAttributeButtonImageView.setPreserveRatio(true);

            Button downSwitchAttributeButton = new Button();
            downSwitchAttributeButton.setGraphic(downSwitchAttributeButtonImageView);

            GridPane.setHalignment(downSwitchAttributeButton, HPos.CENTER);
            GridPane.setValignment(downSwitchAttributeButton, VPos.CENTER);

            downSwitchAttributeButtonList.add(downSwitchAttributeButton);
            attributeGridPane.add(downSwitchAttributeButton, 9, currentRowIndex);



            ImageView attributeSettingsButtonImageView =
                    new ImageView(ImageLibrary.getAttributeAttributeSettingsButtonImage());

            attributeSettingsButtonImageView.setFitWidth(25);
            attributeSettingsButtonImageView.setFitHeight(25);
            attributeSettingsButtonImageView.setPreserveRatio(true);

            Button attributeSettingsButton = new Button();
            attributeSettingsButton.setGraphic(attributeSettingsButtonImageView);

            GridPane.setHalignment(attributeSettingsButton, HPos.CENTER);
            GridPane.setValignment(attributeSettingsButton, VPos.CENTER);

            attributeSettingsButtonList.add(attributeSettingsButton);
            attributeGridPane.add(attributeSettingsButton, 10, currentRowIndex);



            ImageView attributeRemoveButtonImageView =
                    new ImageView(ImageLibrary.getAttributeAttributeRemoveButtonImage());

            attributeRemoveButtonImageView.setFitWidth(25);
            attributeRemoveButtonImageView.setFitHeight(25);
            attributeRemoveButtonImageView.setPreserveRatio(true);

            Button attributeRemoveButton = new Button();
            attributeRemoveButton.setGraphic(attributeRemoveButtonImageView);

            GridPane.setHalignment(attributeRemoveButton, HPos.CENTER);
            GridPane.setValignment(attributeRemoveButton, VPos.CENTER);

            attributeRemoveButtonList.add(attributeRemoveButton);
            attributeGridPane.add(attributeRemoveButton, 11, currentRowIndex);



            if (abstractAttribute instanceof Attribute attribute) {
                addAttribute(attribute, currentRowIndex);
            } else {
                Text separatorLineText = new Text(viewFacade.getLanguageStrategy().getAttributeSeparatorLineText());

                GridPane.setHalignment(separatorLineText, HPos.CENTER);
                GridPane.setValignment(separatorLineText, VPos.CENTER);
                separatorLineText.setFont(FontLibrary.getMidFont());

                attributeGridPane.add(separatorLineText, 1, currentRowIndex, 6,1);



                attributeSettingsButton.setDisable(true);
            }
        }

        if (!viewFacade.getProject().getAttributes().isEmpty()) {
            upSwitchAttributeButtonList.getFirst().setDisable(true);
            downSwitchAttributeButtonList.getLast().setDisable(true);
        }

    }

    private void addAttribute(Attribute attribute, int rowIndex) {

        Text attributeNameText = new Text(attribute.getName());

        GridPane.setHalignment(attributeNameText, HPos.CENTER);
        GridPane.setValignment(attributeNameText, VPos.CENTER);
        attributeNameText.setFont(FontLibrary.getSmallFont());

        attributeGridPane.add(attributeNameText, 1, rowIndex);



        // TODO: Convert and add image
        ImageView attributeIconImageView = new ImageView();

        GridPane.setHalignment(attributeIconImageView, HPos.CENTER);
        GridPane.setValignment(attributeIconImageView, VPos.CENTER);
        attributeIconImageView.setFitWidth(25);
        attributeIconImageView.setFitHeight(25);
        attributeIconImageView.setPreserveRatio(true);

        attributeGridPane.add(attributeIconImageView, 2, rowIndex);



        Text attributePrefixText = new Text(attribute.getPrefix());

        GridPane.setHalignment(attributePrefixText, HPos.CENTER);
        GridPane.setValignment(attributePrefixText, VPos.CENTER);
        attributePrefixText.setFont(FontLibrary.getSmallFont());

        attributeGridPane.add(attributePrefixText, 3, rowIndex);



        Text attributeSuffixText = new Text(attribute.getSuffix());

        GridPane.setHalignment(attributeSuffixText, HPos.CENTER);
        GridPane.setValignment(attributeSuffixText, VPos.CENTER);
        attributeSuffixText.setFont(FontLibrary.getSmallFont());

        attributeGridPane.add(attributeSuffixText, 4, rowIndex);



        Text attributeNumberOfDecimalPlacesText = new Text(String.valueOf(attribute.getDecimalPlaces()));

        GridPane.setHalignment(attributeNumberOfDecimalPlacesText, HPos.CENTER);
        GridPane.setValignment(attributeNumberOfDecimalPlacesText, VPos.CENTER);
        attributeNumberOfDecimalPlacesText.setFont(FontLibrary.getSmallFont());

        attributeGridPane.add(attributeNumberOfDecimalPlacesText, 5, rowIndex);



        CheckBox attributePermanentlyVisibleCheckBox = new CheckBox();
        attributePermanentlyVisibleCheckBox.setSelected(attribute.isPermanentlyVisible());
        attributePermanentlyVisibleCheckBox.setDisable(true);

        GridPane.setHalignment(attributePermanentlyVisibleCheckBox, HPos.CENTER);
        GridPane.setValignment(attributePermanentlyVisibleCheckBox, VPos.CENTER);

        attributeGridPane.add(attributePermanentlyVisibleCheckBox, 6, rowIndex);
    }


    // show-methods
    public Optional<ButtonType> showRemoveAttributeProjectConfirmationAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(languageStrategy.getRemoveAttributeConfirmationAlertTitle());
        alert.setHeaderText(languageStrategy.getRemoveAttributeConfirmationAlertHeaderText());
        alert.setContentText(languageStrategy.getRemoveAttributeConfirmationAlertContentText());

        return alert.showAndWait();
    }

    // getters-method


    public Button getCloseButton() {
        return closeButton;
    }

    public Button getAddAttributeButton() {
        return addAttributeButton;
    }

    public Button getAddSeparatorLineButton() {
        return addSeparatorLineButton;
    }

    public List<Button> getAttributeSettingsButtonList() {
        return List.copyOf(attributeSettingsButtonList);
    }

    public List<Button> getDownSwitchAttributeButtonList() {
        return List.copyOf(downSwitchAttributeButtonList);
    }

    public List<Button> getUpSwitchAttributeButtonList() {
        return List.copyOf(upSwitchAttributeButtonList);
    }

    public List<Button> getAttributeRemoveButtonList() {
        return List.copyOf(attributeRemoveButtonList);
    }

    public List<CheckBox> getAttributeActiveCheckBoxList() {
        return List.copyOf(attributeActiveCheckBoxList);
    }
}
