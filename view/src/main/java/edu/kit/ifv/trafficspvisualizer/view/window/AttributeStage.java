package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.settings.AbstractAttribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@link AttributeStage} inherits from {@link Stage} and is a sub-window of the application
 * which can be opened from the {@link MainApplicationWindow}
 * and on which the user can see all separator lines and attributes.
 *
 * @version 1.0
 */
public class AttributeStage extends Stage {

    private final ViewFacade viewFacade;


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


    /**
     * Creates the basic structure of the {@link AttributeStage}.
     *
     * @param viewFacade The {@link ViewFacade} through which this class can access
     *                   the {@link Project} and the {@link LanguageStrategy}.
     */
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
        attributeGridPane.add(activeText, 0, 0);
        attributeGridPane.add(nameText, 1, 0);
        attributeGridPane.add(iconText, 2, 0);
        attributeGridPane.add(prefixText, 3, 0);
        attributeGridPane.add(suffixText, 4, 0);
        attributeGridPane.add(numberOfDecimalPlacesText, 5, 0);
        attributeGridPane.add(permanentlyVisibleText, 6, 0);
        attributeGridPane.add(sizingPane, 7, 0);

        attributeScrollPane = new ScrollPane(attributeGridPane);


        addAttributeButton = new Button(languageStrategy.getAttributeAddAttributeButtonText());

        addSeparatorLineButton = new Button(languageStrategy.getAttributeAddSeparatorLineButtonText());

        closeButton = new Button(languageStrategy.getAttributeCloseButtonText());

        closeAndAddGridPane = new GridPane();
        closeAndAddGridPane.add(addAttributeButton, 0, 0);
        closeAndAddGridPane.add(addSeparatorLineButton, 1, 0);
        closeAndAddGridPane.add(closeButton, 0, 1, 2, 1);

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
        Styler.centerCenterSmallBoldFontTextInGridPane(activeText);

        Styler.centerCenterSmallBoldFontTextInGridPane(nameText);

        Styler.centerCenterSmallBoldFontTextInGridPane(iconText);

        Styler.centerCenterSmallBoldFontTextInGridPane(prefixText);

        Styler.centerCenterSmallBoldFontTextInGridPane(suffixText);

        Styler.centerCenterSmallBoldFontTextInGridPane(numberOfDecimalPlacesText);

        Styler.centerCenterSmallBoldFontTextInGridPane(permanentlyVisibleText);

        GridPane.setHgrow(sizingPane, Priority.ALWAYS);

        Styler.midHVGabMidPaddingGridPane(attributeGridPane);
        attributeGridPane.prefWidthProperty().bind(scene.widthProperty().subtract(17));


        attributeScrollPane.prefHeightProperty().bind(
                scene.heightProperty().subtract(closeAndAddGridPane.heightProperty()));

        Styler.leftCenterSmallFontButtonInGridPane(addAttributeButton);

        Styler.leftCenterSmallFontButtonInGridPane(addSeparatorLineButton);

        Styler.leftCenterSmallFontButtonInGridPane(closeButton);

        Styler.midHVGabMidPaddingGridPane(closeAndAddGridPane);


        Styler.midStage(this);
    }


    // update- and add-methods

    /**
     * Updates the displayed attributes/separator lines.
     */
    public void updateStage() {
        attributeActiveCheckBoxList.clear();
        upSwitchAttributeButtonList.clear();
        downSwitchAttributeButtonList.clear();
        attributeSettingsButtonList.clear();
        attributeRemoveButtonList.clear();
        attributeGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) >= 1);


        int currentRowIndex = 0;
        for (AbstractAttribute abstractAttribute : viewFacade.getProject().getAbstractAttributes()) {
            currentRowIndex += 2;

            CheckBox attributeActiveCheckBox = new CheckBox();
            attributeActiveCheckBox.setSelected(abstractAttribute.isActive());

            Styler.centerCenterInGridPane(attributeActiveCheckBox);
            attributeActiveCheckBox.setFont(FontLibrary.getSmallFont());

            attributeActiveCheckBoxList.add(attributeActiveCheckBox);
            attributeGridPane.add(attributeActiveCheckBox, 0, currentRowIndex);


            ImageView upSwitchAttributeButtonImageView =
                    new ImageView(ImageLibrary.getAttributeUpSwitchAttributeButtonImage());

            Styler.smallImageView(upSwitchAttributeButtonImageView);

            Button upSwitchAttributeButton = new Button();
            upSwitchAttributeButton.setGraphic(upSwitchAttributeButtonImageView);

            Styler.centerCenterInGridPane(upSwitchAttributeButton);

            upSwitchAttributeButtonList.add(upSwitchAttributeButton);
            attributeGridPane.add(upSwitchAttributeButton, 8, currentRowIndex);


            ImageView downSwitchAttributeButtonImageView =
                    new ImageView(ImageLibrary.getAttributeDownSwitchAttributeButtonImage());

            Styler.smallImageView(downSwitchAttributeButtonImageView);

            Button downSwitchAttributeButton = new Button();
            downSwitchAttributeButton.setGraphic(downSwitchAttributeButtonImageView);

            Styler.centerCenterInGridPane(downSwitchAttributeButton);

            downSwitchAttributeButtonList.add(downSwitchAttributeButton);
            attributeGridPane.add(downSwitchAttributeButton, 9, currentRowIndex);


            ImageView attributeSettingsButtonImageView =
                    new ImageView(ImageLibrary.getAttributeAttributeSettingsButtonImage());

            Styler.midImageView(attributeSettingsButtonImageView);

            Button attributeSettingsButton = new Button();
            attributeSettingsButton.setGraphic(attributeSettingsButtonImageView);

            Styler.centerCenterInGridPane(attributeSettingsButton);

            attributeSettingsButtonList.add(attributeSettingsButton);
            attributeGridPane.add(attributeSettingsButton, 10, currentRowIndex);


            ImageView attributeRemoveButtonImageView =
                    new ImageView(ImageLibrary.getAttributeAttributeRemoveButtonImage());

            Styler.midImageView(attributeRemoveButtonImageView);

            Button attributeRemoveButton = new Button();
            attributeRemoveButton.setGraphic(attributeRemoveButtonImageView);

            Styler.centerCenterInGridPane(attributeRemoveButton);

            attributeRemoveButtonList.add(attributeRemoveButton);
            attributeGridPane.add(attributeRemoveButton, 11, currentRowIndex);


            if (abstractAttribute.hasValues()) {
                addAttribute((Attribute) abstractAttribute, currentRowIndex);
            } else {
                Text separatorLineText = new Text(viewFacade.getLanguageStrategy().getAttributeSeparatorLineText());

                Styler.centerCenterMidFontTextInGridPane(separatorLineText);

                attributeGridPane.add(separatorLineText, 1, currentRowIndex, 6, 1);


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

        Styler.centerCenterSmallFontTextInGridPane(attributeNameText);

        attributeGridPane.add(attributeNameText, 1, rowIndex);


        Icon attributeIcon = attribute.getIcon();
        ImageView attributeIconImageView =
                new ImageView(SwingFXUtils.toFXImage(attributeIcon.toBufferedImage(), null));

        Styler.centerCenterInGridPane(attributeIconImageView);
        Styler.midImageView(attributeIconImageView);

        attributeGridPane.add(attributeIconImageView, 2, rowIndex);


        Text attributePrefixText = new Text(attribute.getPrefix());

        Styler.centerCenterSmallFontTextInGridPane(attributePrefixText);

        attributeGridPane.add(attributePrefixText, 3, rowIndex);


        Text attributeSuffixText = new Text(attribute.getSuffix());

        Styler.centerCenterSmallFontTextInGridPane(attributeSuffixText);

        attributeGridPane.add(attributeSuffixText, 4, rowIndex);


        Text attributeNumberOfDecimalPlacesText = new Text(String.valueOf(attribute.getDecimalPlaces()));

        Styler.centerCenterSmallFontTextInGridPane(attributeNumberOfDecimalPlacesText);

        attributeGridPane.add(attributeNumberOfDecimalPlacesText, 5, rowIndex);


        CheckBox attributePermanentlyVisibleCheckBox = new CheckBox();
        attributePermanentlyVisibleCheckBox.setSelected(attribute.isPermanentlyVisible());
        attributePermanentlyVisibleCheckBox.setDisable(true);

        Styler.centerCenterInGridPane(attributePermanentlyVisibleCheckBox);

        attributeGridPane.add(attributePermanentlyVisibleCheckBox, 6, rowIndex);
    }


    // show-methods

    /**
     * Shows a confirmation alert that asks whether the user is aware that the selected attribute
     * will be removed.
     *
     * @return Optional button type of the button selected by the user.
     */
    public Optional<ButtonType> showRemoveAttributeProjectConfirmationAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(languageStrategy.getRemoveAttributeConfirmationAlertTitle());
        alert.setHeaderText(languageStrategy.getRemoveAttributeConfirmationAlertHeaderText());
        alert.setContentText(languageStrategy.getRemoveAttributeConfirmationAlertContentText());

        return alert.showAndWait();
    }

    // getters-method

    /**
     * Gets the close button.
     *
     * @return The close button.
     */
    public Button getCloseButton() {
        return closeButton;
    }

    /**
     * Gets the add attribute button.
     *
     * @return The add attribute button.
     */
    public Button getAddAttributeButton() {
        return addAttributeButton;
    }

    /**
     * Gets the add separator line button.
     *
     * @return The add separator line button.
     */
    public Button getAddSeparatorLineButton() {
        return addSeparatorLineButton;
    }

    /**
     * Gets a list of all attribute settings buttons.
     *
     * @return A list of all attribute settings buttons.
     */
    public List<Button> getAttributeSettingsButtonList() {
        return List.copyOf(attributeSettingsButtonList);
    }

    /**
     * Gets a list of all down switch attribute buttons.
     *
     * @return A list of all down switch attribute buttons.
     */
    public List<Button> getDownSwitchAttributeButtonList() {
        return List.copyOf(downSwitchAttributeButtonList);
    }

    /**
     * Gets a list of all up switch attribute buttons.
     *
     * @return A list of all up switch attribute buttons.
     */
    public List<Button> getUpSwitchAttributeButtonList() {
        return List.copyOf(upSwitchAttributeButtonList);
    }

    /**
     * Gets a list of all attribute remove buttons.
     *
     * @return A list of all attribute remove buttons.
     */
    public List<Button> getAttributeRemoveButtonList() {
        return List.copyOf(attributeRemoveButtonList);
    }

    /**
     * Gets a list of all attribute active check box's.
     *
     * @return A list of all attribute active check box's.
     */
    public List<CheckBox> getAttributeActiveCheckBoxList() {
        return List.copyOf(attributeActiveCheckBoxList);
    }
}
