package edu.kit.ifv.trafficspvisualizer.view.window;

import edu.kit.ifv.trafficspvisualizer.model.settings.Attribute;
import edu.kit.ifv.trafficspvisualizer.model.settings.ChoiceOption;
import edu.kit.ifv.trafficspvisualizer.model.icon.Icon;
import edu.kit.ifv.trafficspvisualizer.model.settings.LineType;
import edu.kit.ifv.trafficspvisualizer.model.settings.Project;
import edu.kit.ifv.trafficspvisualizer.model.settings.RouteSection;
import edu.kit.ifv.trafficspvisualizer.view.ViewFacade;
import edu.kit.ifv.trafficspvisualizer.view.data.font.FontLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.image.ImageLibrary;
import edu.kit.ifv.trafficspvisualizer.view.data.language.LanguageStrategy;
import edu.kit.ifv.trafficspvisualizer.view.style.Styler;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The {@link ChoiceOptionSettingsStage} inherits from {@link Stage} and is a sub-window of the application
 * which can be opened from the {@link MainApplicationWindow}
 * and on which the user can edit all possible settings for a choice option.
 *
 * @version 1.0
 */
public class ChoiceOptionSettingsStage extends Stage {

    private final ViewFacade viewFacade;

    private final int choiceOptionIndex;


    private Text titleText;

    private TextField titleTextField;

    private Text colorText;

    private ColorPicker colorPicker;

    private GridPane titleAndColorGridPane;


    private Text attributesText;

    private Text attributesNameText;

    private Text attributesValueNamesText;

    private final List<List<CheckBox>> attributesValueNamesCheckBoxList;

    private GridPane attributesGridPane;

    private ScrollPane attributesScrollPane;


    private Text routeSectionsText;

    private Text routeSectionsNumberText;

    private Text routeSectionsIconText;

    private Text routeSectionsLineTypeText;

    private Text routeSectionsValueNameText;

    private final List<Button> routeSectionIconButtonList;

    private final List<ChoiceBox<String>> routeSectionLineTypeChoiceBoxList;

    private final List<ChoiceBox<String>> routeSectionValueNameChoiceBoxList;

    private final List<Button> routeSectionRemoveButtonList;

    private GridPane routeSectionsGridPane;

    private ScrollPane routeSectionsScrollPane;


    private Button addRouteSectionButton;

    private Button closeButton;

    private GridPane closeAndAddGridPane;


    private BorderPane bodyBorderPane;

    private Scene scene;


    /**
     * Creates the basic structure of the {@link ChoiceOptionSettingsStage}.
     *
     * @param viewFacade        The {@link ViewFacade} through which this class can access
     *                          the {@link Project} and the {@link LanguageStrategy}.
     * @param choiceOptionIndex The choice option index used to get selected choice option from model.
     */
    public ChoiceOptionSettingsStage(ViewFacade viewFacade, int choiceOptionIndex) {
        attributesValueNamesCheckBoxList = new ArrayList<>();
        routeSectionIconButtonList = new ArrayList<>();
        routeSectionLineTypeChoiceBoxList = new ArrayList<>();
        routeSectionValueNameChoiceBoxList = new ArrayList<>();
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
        titleAndColorGridPane.add(titleText, 0, 0);
        titleAndColorGridPane.add(titleTextField, 1, 0);
        titleAndColorGridPane.add(colorText, 2, 0);
        titleAndColorGridPane.add(colorPicker, 3, 0);
    }

    private void buildAttributesScrollPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        attributesText = new Text(languageStrategy.getChoiceOptionSettingsAttributesText());

        attributesNameText = new Text(languageStrategy.getChoiceOptionSettingsAttributesNameText());

        attributesValueNamesText = new Text(languageStrategy.getChoiceOptionSettingsAttributesValueNamesText());

        attributesGridPane = new GridPane();
        attributesGridPane.add(attributesText, 0, 0, 2, 1);
        attributesGridPane.add(attributesNameText, 0, 2);
        attributesGridPane.add(attributesValueNamesText, 1, 2);

        attributesScrollPane = new ScrollPane(attributesGridPane);
    }

    private void buildRouteSectionsScrollPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        routeSectionsText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsText());

        routeSectionsNumberText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsNumberText());

        routeSectionsIconText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsIconText());

        routeSectionsLineTypeText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsLineTypeText());

        routeSectionsValueNameText = new Text(languageStrategy.getChoiceOptionSettingsRouteSectionsValueNameText());

        routeSectionsGridPane = new GridPane();
        routeSectionsGridPane.add(routeSectionsText, 0, 0, 5, 1);
        routeSectionsGridPane.add(routeSectionsNumberText, 0, 2);
        routeSectionsGridPane.add(routeSectionsIconText, 1, 2);
        routeSectionsGridPane.add(routeSectionsLineTypeText, 2, 2);
        routeSectionsGridPane.add(routeSectionsValueNameText, 3, 2);

        routeSectionsScrollPane = new ScrollPane(routeSectionsGridPane);
    }

    private void buildCloseAndAddGridPane() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        addRouteSectionButton = new Button(languageStrategy.getChoiceOptionSettingsAddRouteSectionButtonText());

        closeButton = new Button(languageStrategy.getChoiceOptionSettingsCloseButtonText());

        closeAndAddGridPane = new GridPane();
        closeAndAddGridPane.add(addRouteSectionButton, 0, 0);
        closeAndAddGridPane.add(closeButton, 0, 1);
    }


    // style-methods
    private void styleStage() {
        styleTitleAndColorGridPane();
        styleAttributeScrollPane();
        styleRouteSectionScrollPane();
        styleCloseAndAddGridPane();

        Styler.midStage(this);
    }

    private void styleTitleAndColorGridPane() {
        Styler.leftCenterMidFontTextInGridPane(titleText);

        Styler.leftCenterHGrowSmallFontTextFieldInGridPane(titleTextField);

        Styler.leftCenterMidFontTextInGridPane(colorText);

        Styler.leftCenterInGridPane(colorPicker);

        Styler.midHVGabMidPaddingGridPane(titleAndColorGridPane);
    }

    private void styleAttributeScrollPane() {
        Styler.leftCenterMidBoldFontTextInGridPane(attributesText);

        Styler.centerCenterSmallBoldFontTextInGridPane(attributesNameText);

        Styler.centerCenterSmallBoldFontTextInGridPane(attributesValueNamesText);

        Styler.midHVGabMidPaddingGridPane(attributesGridPane);

        attributesScrollPane.setFitToHeight(true);
    }

    private void styleRouteSectionScrollPane() {
        Styler.leftCenterMidBoldFontTextInGridPane(routeSectionsText);

        Styler.centerCenterSmallBoldFontTextInGridPane(routeSectionsNumberText);

        Styler.centerCenterSmallBoldFontTextInGridPane(routeSectionsIconText);

        Styler.centerCenterSmallBoldFontTextInGridPane(routeSectionsLineTypeText);

        Styler.centerCenterSmallBoldFontTextInGridPane(routeSectionsValueNameText);

        Styler.midHVGabMidPaddingGridPane(routeSectionsGridPane);

        routeSectionsScrollPane.setFitToHeight(true);

    }

    private void styleCloseAndAddGridPane() {
        Styler.leftCenterSmallFontButtonInGridPane(addRouteSectionButton);

        Styler.leftCenterSmallFontButtonInGridPane(closeButton);

        Styler.midHVGabMidPaddingGridPane(closeAndAddGridPane);
    }


    // update-methods
    private void updateStage() {
        updateTitleAndColorGridPane();
        updateAttributes();
        updateRouteSections();

        setTitle(viewFacade.getLanguageStrategy().getChoiceOptionSettingsTitleFormat().formatted(
                viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex).getName()
        ));
    }

    private void updateTitleAndColorGridPane() {
        ChoiceOption choiceOption = viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex);

        titleTextField.setText(choiceOption.getTitle());

        colorPicker.setValue(choiceOption.getColor());
    }

    /**
     * Updates the displayed attributes.
     */
    private void updateAttributes() {
        attributesGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) >= 3);
        attributesValueNamesCheckBoxList.clear();


        int currentRowIndex = 2;
        for (Attribute attribute : viewFacade.getProject().getAttributes()) {
            currentRowIndex += 2;

            addAttribute(attribute, currentRowIndex);
        }

    }

    /**
     * Updates the displayed route sections.
     */
    public void updateRouteSections() {
        routeSectionsGridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) >= 3);
        routeSectionIconButtonList.clear();
        routeSectionLineTypeChoiceBoxList.clear();
        routeSectionValueNameChoiceBoxList.clear();
        routeSectionRemoveButtonList.clear();

        int currentRowIndex = 2;
        for (int routeSectionIndex = 0;
             routeSectionIndex <
                     viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex).getRouteSections().size();
             routeSectionIndex++) {
            currentRowIndex += 2;

            addRouteSection(routeSectionIndex, currentRowIndex);
        }
    }

    /**
     * Updates the displayed texts of all attribute value names menu buttons.
     */
    public void updateAttributeValueNamesMenuButtonTexts() {
        for(Node node : attributesGridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == 1
                    && GridPane.getRowIndex(node) >= 4
                    && GridPane.getRowIndex(node) % 2 == 0) {

                MenuButton button = (MenuButton) node;
                ChoiceOption choiceOption = viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex);
                button.setText(getAttributeValueNamesMenuButtonText((Attribute) button.getUserData(), choiceOption));
            }
        }
    }

    private void addAttribute(Attribute attribute, int rowIndex) {
        ChoiceOption choiceOption = viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex);


        Text attributeNameText = new Text(attribute.getName());

        Styler.centerCenterSmallFontTextInGridPane(attributeNameText);

        attributesGridPane.add(attributeNameText, 0, rowIndex);

        MenuButton attributeValueNamesMenuButton = new MenuButton(
                getAttributeValueNamesMenuButtonText(attribute, choiceOption)
        );

        attributeValueNamesMenuButton.setUserData(attribute);

        // ensure underscores are portrayed correctly
        attributeValueNamesMenuButton.setMnemonicParsing(false);

        List<CheckBox> valueNameCheckBoxList = new ArrayList<>();
        for (String valueName :
                viewFacade.getProject().getDataObject().getValueNames(0, choiceOption.getName())) {
            CheckBox valueNameCheckBox = new CheckBox(valueName);
            valueNameCheckBox.setSelected(attribute.getMapping(choiceOption).contains(valueName));

            valueNameCheckBox.setFont(FontLibrary.getSmallFont());

            // ensure underscores are portrayed correctly
            valueNameCheckBox.setMnemonicParsing(false);

            valueNameCheckBoxList.add(valueNameCheckBox);


            CustomMenuItem valueNameCustomMenuItem = new CustomMenuItem(valueNameCheckBox, false);

            attributeValueNamesMenuButton.getItems().add(valueNameCustomMenuItem);
        }

        Styler.centerCenterInGridPane(attributeValueNamesMenuButton);
        attributeValueNamesMenuButton.setFont(FontLibrary.getSmallFont());

        attributesValueNamesCheckBoxList.add(valueNameCheckBoxList);
        attributesGridPane.add(attributeValueNamesMenuButton, 1, rowIndex);
    }

    private void addRouteSection(int routeSectionIndex, int rowIndex) {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();
        ChoiceOption choiceOption = viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex);
        RouteSection routeSection = viewFacade.getProject().getChoiceOptions().get(choiceOptionIndex)
                .getRouteSections().get(routeSectionIndex);


        Text routeSectionNumberText = new Text(String.valueOf(routeSectionIndex + 1));

        Styler.centerCenterSmallFontTextInGridPane(routeSectionNumberText);

        routeSectionsGridPane.add(routeSectionNumberText, 0, rowIndex);


        Icon routeSectionIcon = routeSection.getIcon();
        ImageView routeSectionIconButtonImageView =
                new ImageView(SwingFXUtils.toFXImage(routeSectionIcon.toBufferedImage(), null));

        Styler.midImageView(routeSectionIconButtonImageView);

        Button routeSectionIconButton = new Button();
        routeSectionIconButton.setGraphic(routeSectionIconButtonImageView);

        Styler.centerCenterInGridPane(routeSectionIconButton);

        routeSectionIconButtonList.add(routeSectionIconButton);
        routeSectionsGridPane.add(routeSectionIconButton, 1, rowIndex);


        ChoiceBox<String> routeSectionLineTypeChoiceBox = new ChoiceBox<>();
        for (LineType lineType : LineType.values()) {
            routeSectionLineTypeChoiceBox.getItems().add(languageStrategy.getLineTypeText(lineType));
        }
        routeSectionLineTypeChoiceBox.setValue(languageStrategy.getLineTypeText(routeSection.getLineType()));

        Styler.centerCenterInGridPane(routeSectionLineTypeChoiceBox);

        routeSectionLineTypeChoiceBoxList.add(routeSectionLineTypeChoiceBox);
        routeSectionsGridPane.add(routeSectionLineTypeChoiceBox, 2, rowIndex);


        ChoiceBox<String> routeSectionValueNameChoiceBox = new ChoiceBox<>();
        for (String valueName :
                viewFacade.getProject().getDataObject().getValueNames(0, choiceOption.getName())) {
            routeSectionValueNameChoiceBox.getItems().add(valueName);
        }
        routeSectionValueNameChoiceBox.setValue(routeSection.getChoiceDataKey());

        Styler.centerCenterInGridPane(routeSectionValueNameChoiceBox);

        routeSectionValueNameChoiceBoxList.add(routeSectionValueNameChoiceBox);
        routeSectionsGridPane.add(routeSectionValueNameChoiceBox, 3, rowIndex);


        ImageView routeSectionRemoveButtonImageView =
                new ImageView(ImageLibrary.getChoiceOptionSettingsRouteSectionRemoveButtonImage());

        Styler.midImageView(routeSectionRemoveButtonImageView);

        Button routeSectionRemoveButton = new Button();
        routeSectionRemoveButton.setGraphic(routeSectionRemoveButtonImageView);

        Styler.centerCenterInGridPane(routeSectionRemoveButton);

        routeSectionRemoveButtonList.add(routeSectionRemoveButton);
        routeSectionsGridPane.add(routeSectionRemoveButton, 4, rowIndex);
    }


    // show-methods

    /**
     * Shows a confirmation alert that asks whether the user is aware that the selected route section
     * will be removed.
     *
     * @return Optional button type of the button selected by the user.
     */
    public Optional<ButtonType> showRemoveRouteSectionConfirmationAlert() {
        LanguageStrategy languageStrategy = viewFacade.getLanguageStrategy();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(languageStrategy.getRemoveRouteSectionConfirmationAlertTitle());
        alert.setHeaderText(languageStrategy.getRemoveRouteSectionConfirmationAlertHeaderText());
        alert.setContentText(languageStrategy.getRemoveRouteSectionConfirmationAlertContentText());

        return alert.showAndWait();
    }

    // getter-methods
    // components

    /**
     * Gets the close button.
     *
     * @return The close button.
     */
    public Button getCloseButton() {
        return closeButton;
    }

    /**
     * Gets a list of all route section remove buttons.
     *
     * @return A list of all route section remove buttons.
     */
    public List<Button> getRouteSectionRemoveButtonList() {
        return routeSectionRemoveButtonList;
    }

    /**
     * Gets the add route section button.
     *
     * @return The add route section button.
     */
    public Button getAddRouteSectionButton() {
        return addRouteSectionButton;
    }

    /**
     * Gets a list of all route section icon buttons.
     *
     * @return A list of all route section icon buttons.
     */
    public List<Button> getRouteSectionIconButtonList() {
        return routeSectionIconButtonList;
    }

    /**
     * Gets the title text field.
     *
     * @return The title text field.
     */
    public TextField getTitleTextField() {
        return titleTextField;
    }

    // Checkbox Lists

    /**
     * Gets a list of all route section line type choice box's.
     *
     * @return A list of all route section line type choice box's.
     */
    public List<ChoiceBox<String>> getRouteSectionLineTypeChoiceBoxList() {
        return routeSectionLineTypeChoiceBoxList;
    }

    /**
     * Gets a list of all route section value name choice box's.
     *
     * @return A list of all route section value name choice box's.
     */
    public List<ChoiceBox<String>> getRouteSectionValueNameChoiceBoxList() {
        return routeSectionValueNameChoiceBoxList;
    }

    /**
     * Gets a list of all attribute value name lists.
     *
     * @return A list of all attribute value name lists.
     */
    public List<List<CheckBox>> getAttributesValueNamesCheckBoxList() {
        return attributesValueNamesCheckBoxList;
    }

    /**
     * Gets the color picker.
     *
     * @return The color picker.
     */
    public ColorPicker getColorPicker() {
        return colorPicker;
    }

    // values

    /**
     * Gets the title {@code Sting}.
     *
     * @return The title {@code Sting}.
     */
    public String getTitleString() {
        return titleTextField.getText();
    }

    /**
     * Gets the selected color.
     *
     * @return The selected color.
     */
    public Color getSelectedColor() {
        return colorPicker.getValue();
    }

    /**
     * Gets the selected route section line type.
     *
     * @param routeSectionIndex Index of the route section for which the line type is required.
     * @return The selected line type.
     */
    public LineType getRouteSectionLineTypeChoiceBoxSelection(int routeSectionIndex) {
        for (LineType lineType : LineType.values()) {
            if (routeSectionLineTypeChoiceBoxList.get(routeSectionIndex).getSelectionModel().getSelectedItem()
                    .equals(viewFacade.getLanguageStrategy().getLineTypeText(lineType))) {
                return lineType;
            }
        }

        // default value, method should never reach this point
        return LineType.SOLID;
    }

    /**
     * Gets the selected route section value name.
     *
     * @param routeSectionIndex Index of the route section for which the value name is required.
     * @return The selected value name.
     */
    public String getRouteSectionValueNameSelection(int routeSectionIndex) {
        return routeSectionValueNameChoiceBoxList.get(routeSectionIndex).getSelectionModel().getSelectedItem();
    }

    /**
     * Gets the selected attribute value names.
     *
     * @param attributeIndex Index of the attribute for which the value names are required.
     * @return List of the selected value names.
     */
    public List<String> getAttributeValueNamesSelection(int attributeIndex) {
        List<String> attributeValues = new ArrayList<>();
        for (CheckBox checkBox : attributesValueNamesCheckBoxList.get(attributeIndex)) {
            if (checkBox.isSelected()) {
                attributeValues.add(checkBox.getText());
            }
        }
        return attributeValues;
    }

    private String getAttributeValueNamesMenuButtonText(Attribute attribute, ChoiceOption choiceOption) {
        if (attribute.getMapping(choiceOption).isEmpty()) {

            // show standard text
            return viewFacade.getLanguageStrategy().getChoiceOptionSettingsAttributeValueNamesMenuButtonText();

        } else if (attribute.getMapping(choiceOption).size() == 1) {

            // show name of only mapping
            return attribute.getMapping(choiceOption).getFirst();

        } else {

            // show first letters of first mapping and number of other mappings
            final String format = "%.6s...+%d";
            return String.format(format, attribute.getMapping(choiceOption).getFirst(),
                    attribute.getMapping(choiceOption).size() - 1);
        }
    }
}
